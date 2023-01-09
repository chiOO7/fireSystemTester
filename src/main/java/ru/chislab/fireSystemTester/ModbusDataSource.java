package ru.chislab.fireSystemTester;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.serial.SerialPortException;
import ru.chislab.fireSystemTester.enums.Events;
import ru.chislab.fireSystemTester.enums.ZoneTypes;
import ru.chislab.fireSystemTester.zones.ZoneConfiguration;
import ru.chislab.fireSystemTester.zones.ZoneState;

import java.util.ArrayList;
import java.util.List;

public class ModbusDataSource {
    private static final String PORT = "COM1";
    private static final int SLAVE_ID = 1;
    private static final int OFFSET = 0;
    private static final int ZONE_QUANTITY = 5;
    final static private int ZONE_STATE_HR_OFFSET = 40000;

    public List<ZoneConfiguration> getModbusZoneConfigurations() {

        List<ZoneConfiguration> zoneConfigurations = new ArrayList<>();
        int[] registerValues = new int[0];
        try {
            ModbusMaster master = ModbusMasterFactory
                    .createModbusMasterRTU(ModbusSerialPort.initSerial(PORT));
            master.connect();
            try {
                registerValues = master.readInputRegisters(SLAVE_ID, OFFSET, ZONE_QUANTITY * 4);
            } catch (Exception e) {
                System.err.println(e.getMessage());
//                e.printStackTrace();
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
            for (int i = 0; i < ZONE_QUANTITY; i++) {
                ZoneConfiguration zoneConfiguration = new ZoneConfiguration();

                zoneConfiguration.setModbusZoneNumber(i + 1);
                zoneConfiguration.setDeviceAddress(registerValues[i * 4]);
                zoneConfiguration.setSignalLineNumber(registerValues[i * 4 + 1]);
                zoneConfiguration.setModbusChapterNumber(registerValues[i * 4 + 2]);
                zoneConfiguration.setZoneType(ZoneTypes.values()[registerValues[i * 4 + 3]]);
                if (zoneConfiguration.getDeviceAddress() != 0 && zoneConfiguration.getZoneType() != ZoneTypes.EMPTY_TYPE
                        && zoneConfiguration.getSignalLineNumber() != 0) {
                    zoneConfigurations.add(zoneConfiguration);
                }
            }
        } else {
            System.out.println("Do not have input registers");
        }

        return zoneConfigurations;
    }

    public ZoneConfiguration getModbusZoneConfigurationByZoneNumber(int number) {

        ZoneConfiguration zoneConfiguration = new ZoneConfiguration();
        int[] registerValues = new int[0];
        try {
            ModbusMaster master = ModbusMasterFactory
                    .createModbusMasterRTU(ModbusSerialPort.initSerial(PORT));
            master.connect();
            try {
                registerValues = master.readInputRegisters(SLAVE_ID, number - 1, 4);
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
            zoneConfiguration.setModbusZoneNumber(number);
            zoneConfiguration.setDeviceAddress(registerValues[0]);
            zoneConfiguration.setSignalLineNumber(registerValues[1]);
            zoneConfiguration.setModbusChapterNumber(registerValues[2]);
            zoneConfiguration.setZoneType(ZoneTypes.values()[registerValues[3]]);
        } else {
            System.out.println("Do not have input registers");
        }

        return zoneConfiguration;
    }

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
            List<Events> events = getEventsByWord(registerValues[0]);
            state.setState(events);
        } else {
            System.out.println("Do not have holding registers");
        }
        return state;
    }

    public void setZoneStateByModbusZoneNumber(int number, List<Events> state) {
        try {
            ModbusMaster master = ModbusMasterFactory
                    .createModbusMasterRTU(ModbusSerialPort.initSerial(PORT));
            master.connect();
            try {
                master.writeSingleRegister(SLAVE_ID, number + ZONE_STATE_HR_OFFSET - 1, getWordByEvent(state));
            } catch (Exception e) {
                System.err.println(e.getMessage());
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
    }

    private static List<Events> getEventsByWord(int word) {
        int elderByte = word >> 8;
        int junByte = word - (elderByte << 8);

        Events event1 = Events.getEventByCode(elderByte);
        Events event2 = Events.getEventByCode(junByte);

        List<Events> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);

        return events;
    }

    private static int getWordByEvent(List<Events> events) {
        return (events.get(0).getCode() << 8) + events.get(1).getCode();
    }
}
