package ru.chislab.fireSystemTester.zones;

import lombok.Getter;
import ru.chislab.fireSystemTester.ModbusDataSource;
import ru.chislab.fireSystemTester.enums.Events;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ZoneManager {

    private List<Zone> zones;

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
            zone.setZoneState(modbusDataSource.getZoneStateByModbusZoneNumber(zone.getConfiguration().getModbusZoneNumber()));
        }
    }

    public void updateZoneStateByZoneNumber(int number) {
        for (Zone zone : zones) {
            if (zone.getConfiguration().getModbusZoneNumber() == number) {
                zone.setZoneState(modbusDataSource.getZoneStateByModbusZoneNumber(number));
            }
        }
    }

    public Zone getZoneByZoneNumber(int number) throws ZoneNotFoundException {
        for (Zone zone : zones) {
            if (zone.getConfiguration().getModbusZoneNumber() == number) {
                return zone;
            }
        }
        throw new ZoneNotFoundException("Zone not found or connection with С2000-ПП failed");
    }

    public List<Zone> getZonesByChapterNumber(int chapterNumber) {
        List<Zone> zoneList = new ArrayList<>();
        for (Zone zone : zones) {
            if (zone.getConfiguration().getModbusChapterNumber() == chapterNumber) {
                zoneList.add(zone);
            }
        }
        return zoneList;
    }

    public void setZoneStateByZoneNumber(int number, List<Events> state) {
        modbusDataSource.setZoneStateByModbusZoneNumber(number, state);
    }
}
