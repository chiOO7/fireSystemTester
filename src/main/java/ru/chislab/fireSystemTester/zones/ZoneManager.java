package ru.chislab.fireSystemTester.zones;

import lombok.Data;
import ru.chislab.fireSystemTester.modbus.ModbusDataSource;
import ru.chislab.fireSystemTester.dao.ZoneDao;
import ru.chislab.fireSystemTester.enums.States;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Data
public class ZoneManager {
    private final List<Zone> zones;
    private final ModbusDataSource modbusDataSource;
    private final ZoneDao zoneDao;

    public ZoneManager(ModbusDataSource modbusDataSource, ZoneDao zoneDao) {
        this.modbusDataSource = modbusDataSource;
        this.zones = new ArrayList<>();
        this.zoneDao = zoneDao;
    }

    public ZoneManager(ModbusDataSource modbusDataSource) {
        this.modbusDataSource = modbusDataSource;
        this.zones = new ArrayList<>();
        this.zoneDao = null;
    }

    public void readZoneConfigsFromDevice() {
        List<ZoneConfigurationDto> zoneConfigurationsDto = modbusDataSource.getModbusZoneConfigurations();
        for (ZoneConfigurationDto configuration : zoneConfigurationsDto) {
            Zone zone = new Zone(configuration);
            if (!zones.contains(zone)) {
                zones.add(zone);
            }
        }
    }

    public void clearZones() {
        zones.clear();
    }

//    public void saveZonesToStorage() {
//        zoneDao.saveZonesToStorage(zones);
//    }

//    public void getZonesFromStorage() {
//        this.zones.addAll(zoneDao.getZonesFromStorage());
//    }

    public List<Zone> getZones() {
        return zones;
    }

    public void updateZonesState(List<Zone> zones) {
        for (Zone zone : zones) {
            zone.setZoneState(modbusDataSource.getZoneStateByModbusZoneNumber(zone.getModbusZoneNumber()));
        }
    }

    public void updateZone(Zone zone) {
        if (zoneDao != null) {
            zoneDao.updateZone(zone);
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
        throw new ZoneNotFoundException("Zone not found or connection with ??2000-???? failed");
    }

    public void setZoneStateByZoneNumber(int number, List<States> state) {
        modbusDataSource.setZoneStateByModbusZoneNumber(number, state);
    }
}
