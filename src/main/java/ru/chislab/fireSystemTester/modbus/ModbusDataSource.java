package ru.chislab.fireSystemTester.modbus;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.serial.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chislab.fireSystemTester.enums.States;
import ru.chislab.fireSystemTester.enums.ZoneTypes;
import ru.chislab.fireSystemTester.zones.ZoneConfigurationDto;
import ru.chislab.fireSystemTester.zones.ZoneState;

import java.util.ArrayList;
import java.util.List;


public class ModbusDataSource {

    private final static Logger logger = LoggerFactory.getLogger("ModbusDataSource");

    private static final String PORT = "COM2";
    private static final int SLAVE_ID = 1;
    private static final int OFFSET = 0;
    private static final int ZONE_QUANTITY = 10;
    final static private int ZONE_STATE_HR_OFFSET = 40000;

    public List<ZoneConfigurationDto> getModbusZoneConfigurations() {

        List<ZoneConfigurationDto> zoneConfigurationsDto = new ArrayList<>();
        int[] registerValues = new int[0];
        try {
            ModbusMaster master = ModbusMasterFactory
                    .createModbusMasterRTU(ModbusSerialPort.initSerial(PORT));
            master.connect();
            try {
                registerValues = master.readInputRegisters(SLAVE_ID, OFFSET, ZONE_QUANTITY * 4);
            } catch (Exception e) {
                logger.error(e.getMessage());
            } finally {
                try {
                    master.disconnect();
                } catch (ModbusIOException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (SerialPortException | ModbusIOException e) {
            logger.error(e.getMessage());
        }

        if (registerValues.length != 0) {
            for (int i = 0; i < ZONE_QUANTITY; i++) {
                ZoneConfigurationDto zoneConfigurationDto = new ZoneConfigurationDto();

                zoneConfigurationDto.setModbusZoneNumber(i + 1);
                zoneConfigurationDto.setDeviceAddress(registerValues[i * 4]);
                zoneConfigurationDto.setSignalLineNumber(registerValues[i * 4 + 1]);
                zoneConfigurationDto.setModbusChapterNumber(registerValues[i * 4 + 2]);
                zoneConfigurationDto.setZoneType(ZoneTypes.values()[registerValues[i * 4 + 3]]);
                if (zoneConfigurationDto.getDeviceAddress() != 0 && zoneConfigurationDto.getZoneType() != ZoneTypes.EMPTY_TYPE
                        && zoneConfigurationDto.getSignalLineNumber() != 0) {
                    zoneConfigurationsDto.add(zoneConfigurationDto);
                }
            }
        } else {
            logger.info("Input registers do not available");
        }

        return zoneConfigurationsDto;
    }

//    public ZoneConfigurationDto getModbusZoneConfigurationByZoneNumber(int number) {
//
//        ZoneConfigurationDto zoneConfigurationDto = new ZoneConfigurationDto();
//        int[] registerValues = new int[0];
//        try {
//            ModbusMaster master = ModbusMasterFactory
//                    .createModbusMasterRTU(ModbusSerialPort.initSerial(PORT));
//            master.connect();
//            try {
//                registerValues = master.readInputRegisters(SLAVE_ID, number - 1, 4);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    master.disconnect();
//                } catch (ModbusIOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        } catch (SerialPortException | ModbusIOException e) {
//            throw new RuntimeException(e);
//        }
//
//        if (registerValues.length != 0) {
//            zoneConfigurationDto.setModbusZoneNumber(number);
//            zoneConfigurationDto.setDeviceAddress(registerValues[0]);
//            zoneConfigurationDto.setSignalLineNumber(registerValues[1]);
//            zoneConfigurationDto.setModbusChapterNumber(registerValues[2]);
//            zoneConfigurationDto.setZoneType(ZoneTypes.values()[registerValues[3]]);
//        } else {
//            System.out.println("Do not have input registers");
//        }
//
//        return zoneConfigurationDto;
//    }

    public ZoneState getZoneStateByModbusZoneNumber(int number) {
        ZoneState state = new ZoneState();

        int[] registerValues = new int[0];
        try {
            ModbusMaster master = ModbusMasterFactory
                    .createModbusMasterRTU(ModbusSerialPort.initSerial(PORT));
            master.connect();
            try {
                registerValues = master.readHoldingRegisters(SLAVE_ID, number + ZONE_STATE_HR_OFFSET - 1, 1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    master.disconnect();
                } catch (ModbusIOException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (SerialPortException | ModbusIOException e) {
            throw new RuntimeException(e);
        }

        if (registerValues.length != 0) {
            List<States> events = getEventsByWord(registerValues[0]);
            state.setStates(events);
        } else {
            System.out.println("Do not have holding registers");
        }
        return state;
    }

    public void setZoneStateByModbusZoneNumber(int number, List<States> state) {
        try {
            ModbusMaster master = ModbusMasterFactory
                    .createModbusMasterRTU(ModbusSerialPort.initSerial(PORT));
            master.connect();
            try {
                master.writeSingleRegister(SLAVE_ID, number + ZONE_STATE_HR_OFFSET - 1, getWordByEvent(state));
            } catch (Exception e) {
                logger.error(e.getMessage());
            } finally {
                try {
                    master.disconnect();
                } catch (ModbusIOException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (SerialPortException | ModbusIOException e) {
            logger.error(e.getMessage());
        }
    }

    private static List<States> getEventsByWord(int word) {
        int elderByte = word >> 8;
        int junByte = word - (elderByte << 8);

        States event1 = States.getStateByCode(elderByte);
        States event2 = States.getStateByCode(junByte);

        List<States> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);

        return events;
    }

    private static int getWordByEvent(List<States> events) {
        return (events.get(0).getCode() << 8) + events.get(1).getCode();
    }
}
