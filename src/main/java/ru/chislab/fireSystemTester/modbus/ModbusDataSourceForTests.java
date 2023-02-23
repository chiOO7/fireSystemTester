package ru.chislab.fireSystemTester.modbus;

//import org.springframework.stereotype.Component;
import ru.chislab.fireSystemTester.enums.States;
import ru.chislab.fireSystemTester.modbus.modbusEmulators.S2000PPEmulator;
import ru.chislab.fireSystemTester.zones.ZoneConfigurationDto;
import ru.chislab.fireSystemTester.zones.ZoneState;

import java.util.ArrayList;
import java.util.List;

//@Component
public class ModbusDataSourceForTests extends ModbusDataSource {
    public ModbusDataSourceForTests() {
        super("", 0);
    }
    private final List<ZoneState> zoneStates = new ArrayList<>(10);
    @Override
    public List<ZoneConfigurationDto> getModbusZoneConfigurations() {
        List<ZoneConfigurationDto> configurations = new ArrayList<>();

        configurations.addAll(S2000PPEmulator.initZoneConfigurations(0, S2000PPEmulator.ZONE_COUNT / 2,
                2, 1));
        configurations.addAll(S2000PPEmulator.initZoneConfigurations(S2000PPEmulator.ZONE_COUNT / 2,
                S2000PPEmulator.ZONE_COUNT, 3, 2));

        zoneStates.addAll(S2000PPEmulator.initZoneStates(0, S2000PPEmulator.ZONE_COUNT / 2));
        zoneStates.addAll(S2000PPEmulator.initZoneStates(S2000PPEmulator.ZONE_COUNT / 2, S2000PPEmulator.ZONE_COUNT));

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
