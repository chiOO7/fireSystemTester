package ru.chislab.fireSystemTester.modbus;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
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
    private static final int OFFSET = 0;
    private static final int ZONE_QUANTITY = 10;
    final static private int ZONE_STATE_HR_OFFSET = 40000;
    private final String PORT;
    private final int SLAVE_ID;

    public ModbusDataSource(String PORT, int SLAVE_ID) {
        this.PORT = PORT;
        this.SLAVE_ID = SLAVE_ID;
    }

    public List<ZoneConfigurationDto> getModbusZoneConfigurations() {
        List<ZoneConfigurationDto> zoneConfigurationsDto = new ArrayList<>();
        int[] registerValues = new int[0];
        try {
            ModbusMaster master = ModbusMasterFactory
                    .createModbusMasterRTU(ModbusSerialPort.initSerial(PORT));
            master.connect();
            try {
                registerValues = master.readInputRegisters(SLAVE_ID, OFFSET, ZONE_QUANTITY * 4);
            } catch (ModbusProtocolException | ModbusNumberException e) {
                logger.error(e.getMessage());
            } finally {
                try {
                    master.disconnect();
                } catch (ModbusIOException e1) {
                    logger.error(e1.getMessage());
                }
            }
        } catch (SerialPortException | ModbusIOException e) {
            logger.error(e.getMessage());
        }

        if (registerValues.length != 0) {
            for (int i = 0; i < ZONE_QUANTITY; i++) {
                ZoneConfigurationDto zoneConfigurationDto = ZoneConfigurationDto.builder()
                        .modbusZoneNumber(i + 1)
                        .deviceAddress(registerValues[i * 4])
                        .signalLineNumber(registerValues[i * 4 + 1])
                        .modbusChapterNumber(registerValues[i * 4 + 2])
                        .zoneType(ZoneTypes.values()[registerValues[i * 4 + 3]])
                        .build();

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

    public ZoneState getZoneStateByModbusZoneNumber(int number) {
        ZoneState state = new ZoneState();

        int[] registerValues = new int[0];
        try {
            ModbusMaster master = ModbusMasterFactory
                    .createModbusMasterRTU(ModbusSerialPort.initSerial(PORT));
            master.connect();
            try {
                registerValues = master.readHoldingRegisters(SLAVE_ID, number + ZONE_STATE_HR_OFFSET - 1, 1);
            } catch (ModbusProtocolException | ModbusNumberException e) {
                logger.error(e.getMessage());
            } finally {
                try {
                    master.disconnect();
                } catch (ModbusIOException e1) {
                    logger.error(e1.getMessage());
                }
            }
        } catch (SerialPortException | ModbusIOException e) {
            logger.error(e.getMessage());
        }

        if (registerValues.length != 0) {
            List<States> events = getEventsByWord(registerValues[0]);
            state.setStates(events);
        } else {
            logger.info("Do not have holding registers");
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
            } catch (ModbusProtocolException | ModbusNumberException e) {
                logger.error(e.getMessage());
            } finally {
                try {
                    master.disconnect();
                } catch (ModbusIOException e1) {
                    logger.error(e1.getMessage());
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
        return (events.get(0).getCode() << 8) | events.get(1).getCode();
    }
}
