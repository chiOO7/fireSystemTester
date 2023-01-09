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
            for (int i = 0; i < ZONE_QUANTITY; i++) {
                ZoneConfiguration zoneConfiguration = new ZoneConfiguration();

                zoneConfiguration.setModbusZoneNumber((i / 4) + 1);
                zoneConfiguration.setDeviceAddress(registerValues[i * 4]);
                zoneConfiguration.setSignalLineNumber(registerValues[i * 4 + 1]);
                zoneConfiguration.setModbusChapterNumber(registerValues[i * 4 + 2]);
                zoneConfiguration.setZoneType(ZoneTypes.values()[registerValues[i * 4 + 3]]);
                zoneConfigurations.add(zoneConfiguration);
            }
        } else {
            System.out.println("Do not have input registers");
        }

        return zoneConfigurations;
    }

    //Mock
    public ZoneState getZoneStateByModbusZoneNumber(int number) {
        ZoneState state = new ZoneState();
        List<Events> events = new ArrayList<>();
        events.add(Events.FIRE);
        events.add(Events.ACTUATOR_FAILURE);
        state.setState(events);

        return state;
    }


}
