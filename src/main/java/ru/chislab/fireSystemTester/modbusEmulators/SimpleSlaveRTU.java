package ru.chislab.fireSystemTester.modbusEmulators;


import com.intelligt.modbus.jlibmodbus.data.DataHolder;
import com.intelligt.modbus.jlibmodbus.data.SimpleDataHolderBuilder;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataValueException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.serial.SerialPortException;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlave;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlaveFactory;
import ru.chislab.fireSystemTester.ModbusSerialPort;
import ru.chislab.fireSystemTester.enums.ZoneTypes;
import ru.chislab.fireSystemTester.zones.ZoneConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleSlaveRTU {
    final static private int slaveId = 1;
    final static private int ZONE_COUNT = 5;

    final static private int INPUT_REGISTERS_TABLE_SIZE = 20;

    public static void main(String[] argv) {
        Scanner scanner = new Scanner(System.in);

//        Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);

        try {
            ModbusSlave slave = initSlave(slaveId);

            List<ZoneConfiguration> configurations = new ArrayList<>();

            for (int i = 0; i < ZONE_COUNT; i++) {
                ZoneConfiguration configuration = new ZoneConfiguration();
                configuration.setModbusZoneNumber(i + 1);
                configuration.setDeviceAddress(12);
                configuration.setSignalLineNumber(i + 1);
                configuration.setModbusChapterNumber(1);
                configuration.setZoneType(ZoneTypes.SIGNAL_LINE_STATE);
                configurations.add(configuration);
            }

            DataHolder dataHolder = slave.getDataHolder();

            setZoneConfigurationRegisters(configurations, dataHolder);

            slave.listen();
            slave.shutdown();
        } catch (ModbusIOException e) {
            e.printStackTrace();
        }
    }

    private static ModbusSlave initSlave(int slaveId) {
        ModbusSlave slave = null;
        try {
            slave = ModbusSlaveFactory.createModbusSlaveRTU(ModbusSerialPort.initSerial("COM3"));
        } catch (SerialPortException e) {
            throw new RuntimeException(e);
        }

        slave.setServerAddress(slaveId);
        System.out.println("Slave id : " + slave.getServerAddress());
        slave.setBroadcastEnabled(true);
        slave.setReadTimeout(1000000);
        slave.setDataHolder(new SimpleDataHolderBuilder(INPUT_REGISTERS_TABLE_SIZE));

        return slave;
    }

    private static void setZoneConfigurationRegisters(List<ZoneConfiguration> configurations, DataHolder dataHolder) {
        for (int i = 0; i < configurations.size(); i++) {
            try {
                dataHolder.getInputRegisters().set(i * 4, configurations.get(i).getDeviceAddress());
                dataHolder.getInputRegisters().set(i * 4 + 1, configurations.get(i).getSignalLineNumber());
                dataHolder.getInputRegisters().set(i * 4 + 2, configurations.get(i).getModbusChapterNumber());
                dataHolder.getInputRegisters().set(i * 4 + 3, configurations.get(i).getZoneType().ordinal());
                System.out.println((i * 4) + " : " + dataHolder.getInputRegisters().get(i * 4) + "; "
                        + (i * 4 + 1) + " : " + dataHolder.getInputRegisters().get(i * 4 + 1) + "; "
                        + (i * 4 + 2) + " : " + dataHolder.getInputRegisters().get(i * 4 + 2) + "; "
                        + (i * 4 + 3) + " : " + dataHolder.getInputRegisters().get(i * 4 + 3)
                );
            } catch (IllegalDataAddressException | IllegalDataValueException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
