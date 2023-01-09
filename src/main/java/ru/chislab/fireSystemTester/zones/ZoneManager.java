package ru.chislab.fireSystemTester.zones;

import ru.chislab.fireSystemTester.ModbusDataSource;

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

    public void updateZoneConfigurationByZoneNumber(int number) {
        for (Zone zone : zones) {
            if (zone.getConfiguration().getModbusZoneNumber() == number) {
                ZoneConfiguration settableConfiguration = modbusDataSource.getModbusZoneConfigurationByZoneNumber(number);
                if (!zone.getConfiguration().equals(settableConfiguration)) {
                    zone.setConfiguration(modbusDataSource.getModbusZoneConfigurationByZoneNumber(number));
                }
            }
        }
    }

    public void updateZonesState() {
        for (Zone zone : zones) {
            zone.setState(modbusDataSource.getZoneStateByModbusZoneNumber(zone.getConfiguration().getModbusZoneNumber()));
        }
    }

    public void updateZoneStateByZoneNumber(int number) {
        for (Zone zone : zones) {
            if (zone.getConfiguration().getModbusZoneNumber() == number) {
                zone.setState(modbusDataSource.getZoneStateByModbusZoneNumber(number));
            }
        }
    }
}
