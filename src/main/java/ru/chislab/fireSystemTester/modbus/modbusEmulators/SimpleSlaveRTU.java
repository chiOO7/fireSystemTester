package ru.chislab.fireSystemTester.modbus.modbusEmulators;


import com.intelligt.modbus.jlibmodbus.data.DataHolder;
import com.intelligt.modbus.jlibmodbus.data.SimpleDataHolderBuilder;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataValueException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.serial.SerialPortException;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlave;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlaveFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import ru.chislab.fireSystemTester.modbus.ModbusSerialPort;
import ru.chislab.fireSystemTester.enums.States;
import ru.chislab.fireSystemTester.enums.ZoneTypes;
import ru.chislab.fireSystemTester.zones.ZoneConfigurationDto;
import ru.chislab.fireSystemTester.zones.ZoneState;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleSlaveRTU {
    final static private int slaveId = 1;
    final static private int ZONE_COUNT = 10;
    final static private int INPUT_REGISTERS_TABLE_SIZE = ZONE_COUNT * 4;
    final static private int HOLDING_REGISTERS_TABLE_SIZE = ZONE_COUNT;
    final static private int ZONE_STATE_HR_OFFSET = 40000;
    final static private String PORT = "COM3";

    private final static String LOG4J_CONFIGURATION_PATH = "log4j.properties";

    public static void main(String[] argv) {

        BasicConfigurator.configure();


        PropertyConfigurator.configure(LOG4J_CONFIGURATION_PATH);
        Scanner scanner = new Scanner(System.in);

//        Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);

        try {
            ModbusSlave slave = initSlave(slaveId);
            System.out.println();

            List<ZoneConfigurationDto> configurations = new ArrayList<>();

            List<ZoneState> states = new ArrayList<>();

            for (int i = 0; i < ZONE_COUNT / 2; i++) {
                ZoneConfigurationDto configuration = new ZoneConfigurationDto();
                configuration.setModbusZoneNumber(i + 1);
                configuration.setDeviceAddress(2);
                configuration.setSignalLineNumber(i + 1);
                configuration.setModbusChapterNumber(1);
                configuration.setZoneType(ZoneTypes.SIGNAL_LINE_STATE);
                configurations.add(configuration);

                States event1 = States.values()[i];
                States event2 = States.values()[i + 1];
                List<States> events = new ArrayList<>();
                events.add(event1);
                events.add(event2);
                ZoneState state = new ZoneState();
                state.setStates(events);
                states.add(state);
            }

            for (int i = ZONE_COUNT / 2; i < ZONE_COUNT; i++) {
                ZoneConfigurationDto configuration = new ZoneConfigurationDto();
                configuration.setModbusZoneNumber(i + 1);
                configuration.setDeviceAddress(3);
                configuration.setSignalLineNumber(i + 1);
                configuration.setModbusChapterNumber(2);
                configuration.setZoneType(ZoneTypes.SIGNAL_LINE_STATE);
                configurations.add(configuration);

                States event1 = States.values()[i];
                States event2 = States.values()[i + 1];
                List<States> events = new ArrayList<>();
                events.add(event1);
                events.add(event2);
                ZoneState state = new ZoneState();
                state.setStates(events);
                states.add(state);
            }

            DataHolder dataHolder = slave.getDataHolder();

            setZoneConfigurationRegisters(configurations, dataHolder);
            System.out.println();
            setZoneStateRegisters(states, dataHolder);

            slave.listen();
            slave.shutdown();
        } catch (ModbusIOException e) {
            e.printStackTrace();
        }
    }

    private static ModbusSlave initSlave(int slaveId) {
        ModbusSlave slave = null;
        try {
            slave = ModbusSlaveFactory.createModbusSlaveRTU(ModbusSerialPort.initSerial(PORT));
        } catch (SerialPortException e) {
            throw new RuntimeException(e);
        }

        slave.setServerAddress(slaveId);
        System.out.println("Slave id : " + slave.getServerAddress());
        slave.setBroadcastEnabled(true);
        slave.setReadTimeout(1000000);
        slave.setDataHolder(new SimpleDataHolderBuilder(ZONE_STATE_HR_OFFSET + ZONE_COUNT));

        return slave;
    }

    private static void setZoneConfigurationRegisters(List<ZoneConfigurationDto> configurations, DataHolder dataHolder) {
        System.out.println("Zone configuration registers:");
        for (int i = 0; i < configurations.size(); i++) {
            try {
                dataHolder.getInputRegisters().set(i * 4, configurations.get(i).getDeviceAddress());
                dataHolder.getInputRegisters().set(i * 4 + 1, configurations.get(i).getSignalLineNumber());
                dataHolder.getInputRegisters().set(i * 4 + 2, configurations.get(i).getModbusChapterNumber());
                dataHolder.getInputRegisters().set(i * 4 + 3, configurations.get(i).getZoneType().ordinal());
                System.out.println(addSpaces(i * 4) + ": " + dataHolder.getInputRegisters().get(i * 4) + "; "
                        + addSpaces(i * 4 + 1) + ": " + dataHolder.getInputRegisters().get(i * 4 + 1) + "; "
                        + addSpaces(i * 4 + 2) + ": " + dataHolder.getInputRegisters().get(i * 4 + 2) + "; "
                        + addSpaces(i * 4 + 3) + ": " + dataHolder.getInputRegisters().get(i * 4 + 3)
                );
            } catch (IllegalDataAddressException | IllegalDataValueException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void setZoneStateRegisters(List<ZoneState> states, DataHolder dataHolder) {
        System.out.println("Zone state registers:");
        for (int i = 0; i < states.size(); i++) {
            try {
                dataHolder.getHoldingRegisters().set(i + ZONE_STATE_HR_OFFSET, getWordByEvents(states.get(i).getStates()));
                System.out.println((i + ZONE_STATE_HR_OFFSET) + ": " + dataHolder.getHoldingRegisters().get(i + ZONE_STATE_HR_OFFSET));
            } catch (IllegalDataAddressException | IllegalDataValueException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static int getWordByEvents(List<States> events) {
        if (events.get(0) == null && events.get(1) == null) return 0;
        return (events.get(0).getCode() << 8) + events.get(1).getCode();
    }

    private static String addSpaces(int n) {
        int count = 0;
        int temp = 10000 * n;
        if (n < 10) {
            count = 4;
        } else if (n < 100) {
            count = 3;
        } else if (n < 1000) {
            count = 2;
        } else if (n < 10000) {
            count = 1;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append("0");
        }
        return builder.toString() + n;
    }
}
