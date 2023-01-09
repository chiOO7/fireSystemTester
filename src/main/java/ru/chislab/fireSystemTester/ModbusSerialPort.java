package ru.chislab.fireSystemTester;

import com.intelligt.modbus.jlibmodbus.serial.SerialParameters;
import jssc.SerialPortList;

import java.util.Arrays;
import java.util.Scanner;

public class ModbusSerialPort {
    public static SerialParameters initSerial() {
        SerialParameters serialParameters = new SerialParameters();
        String[] portNames = SerialPortList.getPortNames();
        System.out.println("Available ports : " + Arrays.toString(portNames));
        if (portNames.length > 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter port to choose it : ");
            String portName = scanner.nextLine();
            initSerial(portName.toUpperCase());
        } else {
            System.out.println("Ports do not available");
        }
        return serialParameters;
    }

    public static SerialParameters initSerial(String portName) {
        SerialParameters serialParameters = new SerialParameters();
            // you can choose the one of those you need
            serialParameters.setDevice(portName);
            serialParameters.setBaudRate(com.intelligt.modbus.jlibmodbus.serial.SerialPort.BaudRate.BAUD_RATE_9600);
            serialParameters.setDataBits(8);
            serialParameters.setParity(com.intelligt.modbus.jlibmodbus.serial.SerialPort.Parity.NONE);
            serialParameters.setStopBits(1);
            System.out.println("Port : " + serialParameters.getDevice()
                    + "; Baud rate : " + serialParameters.getBaudRate()
                    + "; Data bits : " + serialParameters.getDataBits()
                    + "; Parity : " + serialParameters.getParity()
                    + "; Stop bits : " + serialParameters.getStopBits());
        return serialParameters;
    }
}