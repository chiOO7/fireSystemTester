package ru.chislab.fireSystemTester.zones;

import lombok.Getter;
import ru.chislab.fireSystemTester.ModbusDataSource;
import ru.chislab.fireSystemTester.dao.ZoneDao;
import ru.chislab.fireSystemTester.enums.States;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ZoneManager {

    private final List<Zone> zones;

    private final ModbusDataSource modbusDataSource;

    private final ZoneDao zoneDao;

    public ZoneManager(ModbusDataSource modbusDataSource) {
        this.modbusDataSource = modbusDataSource;
        this.zones = new ArrayList<>();
        this.zoneDao = new ZoneDao();
    }

    public void readZoneConfigsFromDevice() {
        List<ZoneConfigurationDto> zoneConfigurationsDto = modbusDataSource.getModbusZoneConfigurations();
        for (ZoneConfigurationDto configuration : zoneConfigurationsDto) {
            Zone zone = new Zone(configuration);
            zones.add(zone);
        }
//        zones.stream().forEach(System.out::println);
    }

    public void saveZonesToStorage() {
        zoneDao.saveZonesToStorage(zones);
    }

    public List<Zone> getZonesFromStorage() {
        return zoneDao.getZonesFromStorage();
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void updateZonesState() {
        for (Zone zone : zones) {
            zone.setZoneState(modbusDataSource.getZoneStateByModbusZoneNumber(zone.getModbusZoneNumber()));
        }
    }

    public void updateZoneStateByZoneNumber(int number) {
        for (Zone zone : zones) {
            if (zone.getModbusZoneNumber() == number) {
                zone.setZoneState(modbusDataSource.getZoneStateByModbusZoneNumber(number));
            }
        }
    }

    public Zone getZoneByZoneNumber(int number) throws ZoneNotFoundException {
        for (Zone zone : zones) {
            if (zone.getModbusZoneNumber() == number) {
                return zone;
            }
        }
        throw new ZoneNotFoundException("Zone not found or connection with С2000-ПП failed");
    }

    public List<Zone> getZonesByChapterNumber(int chapterNumber) {
        List<Zone> zoneList = new ArrayList<>();
        for (Zone zone : zones) {
            if (zone.getModbusChapterNumber() == chapterNumber) {
                zoneList.add(zone);
            }
        }
        return zoneList;
    }

    public void setZoneStateByZoneNumber(int number, List<States> state) {
        modbusDataSource.setZoneStateByModbusZoneNumber(number, state);
    }
}
