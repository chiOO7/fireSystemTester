package ru.chislab.fireSystemTester.modbus;


import com.intelligt.modbus.jlibmodbus.serial.SerialParameters;
import jssc.SerialPortList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Scanner;


public class ModbusSerialPort {
    private static final Logger logger = LoggerFactory.getLogger("ModbusSerialPort");
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
            logger.error("Ports do not available");
        }
        return serialParameters;
    }
    public static SerialParameters initSerial(String portName) {
        SerialParameters serialParameters = new SerialParameters();
        serialParameters.setDevice(portName);
        serialParameters.setBaudRate(com.intelligt.modbus.jlibmodbus.serial.SerialPort.BaudRate.BAUD_RATE_9600);
        serialParameters.setDataBits(8);
        serialParameters.setParity(com.intelligt.modbus.jlibmodbus.serial.SerialPort.Parity.NONE);
        serialParameters.setStopBits(1);
        logger.debug("Port: " + serialParameters.getDevice()
                + "; Baud rate: " + serialParameters.getBaudRate()
                + "; Data bits: " + serialParameters.getDataBits()
                + "; Parity: " + serialParameters.getParity()
                + "; Stop bits: " + serialParameters.getStopBits());

        return serialParameters;
    }
}
