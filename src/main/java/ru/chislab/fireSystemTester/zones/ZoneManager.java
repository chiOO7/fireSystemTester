package ru.chislab.fireSystemTester.zones;

import ru.chislab.fireSystemTester.ModbusDataSource;
import ru.chislab.fireSystemTester.OrionEvent;

import java.util.ArrayList;
import java.util.List;

public class ZoneManager {

    private static List<Zone> zones;

    public static List<Zone> getZones() {
        return zones;
    }
    private ModbusDataSource modbusDataSource;

    public ZoneManager(ModbusDataSource modbusDataSource) {
        this.modbusDataSource = modbusDataSource;
    }

    public void defineZones() {
        List<Zone> zones = ZoneManager.getZones();
        List<ZoneConfiguration> zoneConfigurations = modbusDataSource.getModbusZoneConfigurations();
        for (ZoneConfiguration configuration : zoneConfigurations) {
            Zone zone = new Zone(configuration);
            zones.add(zone);
        }
    }

}
