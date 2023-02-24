package ru.chislab.fireSystemTester.zones;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.chislab.fireSystemTester.modbus.ModbusDataSource;
import ru.chislab.fireSystemTester.dao.ZoneDao;
import ru.chislab.fireSystemTester.enums.States;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;
import ru.chislab.fireSystemTester.repositories.ZoneRepository;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class ZoneManager {
    private final List<Zone> zones;
    private final ModbusDataSource modbusDataSource;
//    private final ZoneDao zoneDao;
    private final ZoneRepository zoneRepository;

//    public ZoneManager(ModbusDataSource modbusDataSource, ZoneDao zoneDao) {
//        this.modbusDataSource = modbusDataSource;
//        this.zones = new ArrayList<>();
//        this.zoneDao = zoneDao;
//    }

    public ZoneManager(ModbusDataSource modbusDataSource, ZoneRepository zoneRepository) {
        this.modbusDataSource = modbusDataSource;
        this.zones = new ArrayList<>();
        this.zoneRepository = zoneRepository;
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
//        Zone updatedZone = zoneRepository.findAllById(List.of(zone.getModbusZoneNumber())).get(0);
//
//        updatedZone.setModbusZoneNumber(zone.getModbusZoneNumber());
//        updatedZone.setSignalLineNumber(zone.getSignalLineNumber());
//        updatedZone.setChapter(zone.getChapter());
//        updatedZone.setZoneType(zone.getZoneType());
//        updatedZone.setZoneName(zone.getZoneName());
//
//        zoneRepository.save(updatedZone);
        zoneRepository.update(zone.getZoneName());
//        if (zoneDao != null) {
//            zoneDao.updateZone(zone);
//        }
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

    public void setZoneStateByZoneNumber(int number, List<States> state) {
        modbusDataSource.setZoneStateByModbusZoneNumber(number, state);
    }
}
