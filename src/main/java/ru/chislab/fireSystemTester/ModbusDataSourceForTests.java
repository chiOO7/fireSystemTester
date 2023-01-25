package ru.chislab.fireSystemTester;

import com.intelligt.modbus.jlibmodbus.data.DataHolder;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.serial.SerialPortException;
import ru.chislab.fireSystemTester.enums.States;
import ru.chislab.fireSystemTester.enums.ZoneTypes;
import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneConfigurationDto;
import ru.chislab.fireSystemTester.zones.ZoneState;

import java.util.ArrayList;
import java.util.List;

public class ModbusDataSourceForTests extends ModbusDataSource {

    private final int ZONE_COUNT = 10;

    private List<ZoneState> zoneStates = new ArrayList<>(10);

    @Override
    public List<ZoneConfigurationDto> getModbusZoneConfigurations() {
        List<ZoneConfigurationDto> configurations = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            ZoneState zoneState = new ZoneState();
            zoneStates.add(zoneState);
        }

        for (int i = 0; i < ZONE_COUNT / 2; i++) {
            ZoneConfigurationDto configuration = new ZoneConfigurationDto();
            configuration.setModbusZoneNumber(i + 1);
            configuration.setDeviceAddress(2);
            configuration.setSignalLineNumber(i + 1);
            configuration.setModbusChapterNumber(1);
            configuration.setZoneType(ZoneTypes.SIGNAL_LINE_STATE);
            configurations.add(configuration);

            States event1 = States.values()[i];
            States event2 = States.values()[i + 1];
            List<States> events = new ArrayList<>();
            events.add(event1);
            events.add(event2);
            zoneStates.get(i).setStates(events);
        }

        for (int i = ZONE_COUNT / 2; i < ZONE_COUNT; i++) {
            ZoneConfigurationDto configuration = new ZoneConfigurationDto();
            configuration.setModbusZoneNumber(i + 1);
            configuration.setDeviceAddress(3);
            configuration.setSignalLineNumber(i + 1);
            configuration.setModbusChapterNumber(2);
            configuration.setZoneType(ZoneTypes.SIGNAL_LINE_STATE);
            configurations.add(configuration);

            States event1 = States.values()[i];
            States event2 = States.values()[i + 1];
            List<States> events = new ArrayList<>();
            events.add(event1);
            events.add(event2);
            zoneStates.get(i).setStates(events);
        }

        return configurations;
    }

    @Override
    public ZoneState getZoneStateByModbusZoneNumber(int number) {
        return zoneStates.get(number - 1);
    }

    @Override
    public void setZoneStateByModbusZoneNumber(int number, List<States> state) {
        zoneStates.get(number - 1).setStates(state);
    }
}
