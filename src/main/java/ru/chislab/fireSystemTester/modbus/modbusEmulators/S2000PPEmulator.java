package ru.chislab.fireSystemTester.modbus.modbusEmulators;


import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.data.DataHolder;
import com.intelligt.modbus.jlibmodbus.data.SimpleDataHolderBuilder;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataValueException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.serial.SerialParameters;
import com.intelligt.modbus.jlibmodbus.serial.SerialPortException;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlave;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlaveFactory;
//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chislab.fireSystemTester.modbus.ModbusSerialPort;
import ru.chislab.fireSystemTester.enums.States;
import ru.chislab.fireSystemTester.enums.ZoneTypes;
import ru.chislab.fireSystemTester.zones.ZoneConfigurationDto;
import ru.chislab.fireSystemTester.zones.ZoneState;

import java.util.ArrayList;
import java.util.List;

public class S2000PPEmulator {
    final static public int ZONE_COUNT = 10;
    //    final static private int INPUT_REGISTERS_TABLE_SIZE = ZONE_COUNT * 4;
//    final static private int HOLDING_REGISTERS_TABLE_SIZE = ZONE_COUNT;
    final static private int ZONE_STATE_HR_OFFSET = 40000;
//    static private String PORT = "COM";

    private static final Logger logger = LoggerFactory.getLogger(S2000PPEmulator.class);

    //private final static String LOG4J_CONFIGURATION_PATH = "log4j.properties";

    public static void main(String[] argv) {

        final int SLAVE_ID = 1;

        if (argv.length == 0) {
            System.out.println("Input port number with start emulator 3");
            return;
        }

        if (argv[0].matches("\\D")) {
            System.out.println("Input port number with start emulator 3");
            return;
        }

        final String PORT = "COM" + argv[0];

//        BasicConfigurator.configure();
//
//        PropertyConfigurator.configure(LOG4J_CONFIGURATION_PATH);

        Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);

        try {
            ModbusSlave slave = initSlave(SLAVE_ID, PORT);
            System.out.println();

            List<ZoneConfigurationDto> configurations = new ArrayList<>();

            List<ZoneState> states = new ArrayList<>();

            configurations.addAll(initZoneConfigurations(0, ZONE_COUNT / 2, 2, 1));
            configurations.addAll(initZoneConfigurations(ZONE_COUNT / 2, ZONE_COUNT, 3, 2));

            states.addAll(initZoneStates(0, ZONE_COUNT / 2));
            states.addAll(initZoneStates(ZONE_COUNT / 2, ZONE_COUNT));

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

    public static List<ZoneConfigurationDto> initZoneConfigurations(int start, int end, int devAddr, int chaptNumb) {
        List<ZoneConfigurationDto> configurations = new ArrayList<>();
        for (int i = start; i < end; i++) {
            ZoneConfigurationDto configuration = ZoneConfigurationDto.builder()
                    .modbusZoneNumber(i + 1)
                    .deviceAddress(devAddr)
                    .signalLineNumber(i + 1)
                    .modbusChapterNumber(chaptNumb)
                    .zoneType(ZoneTypes.SIGNAL_LINE_STATE)
                    .build();

            configurations.add(configuration);
        }
        return configurations;
    }

    public static List<ZoneState> initZoneStates(int start, int end) {
        List<ZoneState> states = new ArrayList<>();
        for (int i = start; i < end; i++) {
            States event1 = States.values()[i];
            States event2 = States.values()[i + 1];
            List<States> events = new ArrayList<>();
            events.add(event1);
            events.add(event2);
            ZoneState state = new ZoneState();
            state.setStates(events);
            states.add(state);
        }
        return states;
    }


    private static ModbusSlave initSlave(int slaveId, String port) {
        ModbusSlave slave;
        try {
            slave = ModbusSlaveFactory.createModbusSlaveRTU(initPort(port));
        } catch (SerialPortException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }

        slave.setServerAddress(slaveId);
        System.out.println("Slave id : " + slave.getServerAddress());
        slave.setBroadcastEnabled(true);
        slave.setReadTimeout(1000000);
        slave.setDataHolder(new SimpleDataHolderBuilder(ZONE_STATE_HR_OFFSET + ZONE_COUNT));

        return slave;
    }

    private static SerialParameters initPort(String port) {
        return ModbusSerialPort.initSerial(port);
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
                logger.error(e.getMessage());
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
                logger.error(e.getMessage());
            }
        }
        System.out.println();
    }

    private static int getWordByEvents(List<States> events) {
        if (events.get(0) == null && events.get(1) == null) return 0;
        return (events.get(0).getCode() << 8) + events.get(1).getCode();
    }

    private static String addSpaces(int n) {
        int count = 0;
        if (n < 10) {
            count = 4;
        } else if (n < 100) {
            count = 3;
        } else if (n < 1000) {
            count = 2;
        } else if (n < 10000) {
            count = 1;
        }
        return "0".repeat(count) + n;
    }
}
