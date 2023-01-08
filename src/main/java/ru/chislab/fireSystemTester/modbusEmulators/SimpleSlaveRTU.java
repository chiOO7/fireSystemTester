package ru.chislab.fireSystemTester.modbusEmulators;

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.data.DataHolder;
import com.intelligt.modbus.jlibmodbus.data.SimpleDataHolderBuilder;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.serial.SerialParameters;
import com.intelligt.modbus.jlibmodbus.serial.SerialPort;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlave;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlaveFactory;
import jssc.SerialPortList;


public class SimpleSlaveRTU {
    final static private int slaveId = 1;

    public static void main(String[] argv) {
        Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);
        SerialParameters serialParameters = new SerialParameters();

        try {

            String[] dev_list = SerialPortList.getPortNames();
            // if there is at least one serial port at your system
            if (dev_list.length > 0) {

                // you can choose the one of those you need
                serialParameters.setDevice(dev_list[1]);
                // these parameters are set by default
                serialParameters.setBaudRate(SerialPort.BaudRate.BAUD_RATE_9600);
                serialParameters.setDataBits(8);
                serialParameters.setParity(SerialPort.Parity.NONE);
                serialParameters.setStopBits(1);

                ModbusSlave slave = ModbusSlaveFactory.createModbusSlaveRTU(serialParameters);

                slave.setServerAddress(slaveId);
                slave.setBroadcastEnabled(true);

                slave.setReadTimeout(1000000);

                slave.setDataHolder(new SimpleDataHolderBuilder(4));
                DataHolder dataHolder = slave.getDataHolder();

                dataHolder.getInputRegisters().set(0, 12);
                dataHolder.getInputRegisters().set(1, 3);
                dataHolder.getInputRegisters().set(2, 2);
                dataHolder.getInputRegisters().set(3, 1);

                slave.listen();

                slave.shutdown();
            }
        } catch (ModbusProtocolException e) {
            e.printStackTrace();
        } catch (ModbusIOException e) {
            e.printStackTrace();
        } catch (com.intelligt.modbus.jlibmodbus.serial.SerialPortException e) {
            e.printStackTrace();
        }
    }
}
