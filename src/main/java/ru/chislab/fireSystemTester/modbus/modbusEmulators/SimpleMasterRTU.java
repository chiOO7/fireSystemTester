package ru.chislab.fireSystemTester.modbus.modbusEmulators;

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.serial.SerialParameters;
import com.intelligt.modbus.jlibmodbus.serial.SerialPort;
import jssc.SerialPortList;

public class SimpleMasterRTU {
//    static public void main(String[] arg) {
//        SerialParameters sp = new SerialParameters();
//        Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);
//        try {
//            String[] dev_list = SerialPortList.getPortNames();
//
//            if (dev_list.length > 0) {
//                // you can choose the one of those you need
//                sp.setDevice(dev_list[0]);
//                // these parameters are set by default
//                sp.setBaudRate(SerialPort.BaudRate.BAUD_RATE_9600);
//                sp.setDataBits(8);
//                sp.setParity(SerialPort.Parity.NONE);
//                sp.setStopBits(1);
//
//                ModbusMaster m = ModbusMasterFactory.createModbusMasterRTU(sp);
//                m.connect();
//
//                int slaveId = 1;
//                int offset = 0;
//                int quantity = 4;
//                try {
//                    int[] registerValues = m.readInputRegisters(slaveId, offset, quantity);
//                    // print values
//                    for (int value : registerValues) {
//                        System.out.println("Address: " + offset++ + ", Value: " + value);
//                    }
//                } catch (RuntimeException e) {
//                    throw e;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    try {
//                        m.disconnect();
//                    } catch (ModbusIOException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//            }
//        } catch (RuntimeException e) {
//            throw e;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

