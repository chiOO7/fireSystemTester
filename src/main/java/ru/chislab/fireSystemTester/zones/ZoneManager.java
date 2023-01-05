package ru.chislab.fireSystemTester.zones;

import ru.chislab.fireSystemTester.ModbusDataSource;
import ru.chislab.fireSystemTester.OrionEvent;

import java.util.ArrayList;
import java.util.List;

public class ZoneManager {

    private List<Zone> zones;

    public List<Zone> getZones() {
        return zones;
    }
    private ModbusDataSource modbusDataSource;

    public ZoneManager(ModbusDataSource modbusDataSource) {

        this.modbusDataSource = modbusDataSource;
        zones = new ArrayList<>();
    }

    public void defineZones() {
        List<ZoneConfiguration> zoneConfigurations = modbusDataSource.getModbusZoneConfigurations();
        for (ZoneConfiguration configuration : zoneConfigurations) {
            Zone zone = new Zone(configuration);
            zones.add(zone);
        }
    }

    public void updateZonesState() {
        for (Zone zone : zones) {
            zone.setState(modbusDataSource.getZoneStateByModbusZoneNumber(zone.getConfiguration().getModbusZoneNumber()));
        }
    }

}
