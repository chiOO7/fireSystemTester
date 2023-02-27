package ru.chislab.fireSystemTester.zones;


import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chislab.fireSystemTester.modbus.ModbusDataSource;
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
    private final ZoneRepository zoneRepository;

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

    public void updateZonesState(List<Zone> zones) {
        for (Zone zone : zones) {
            zone.setZoneState(modbusDataSource.getZoneStateByModbusZoneNumber(zone.getModbusZoneNumber()));
        }
    }

    @Transactional
    public void updateZone(Zone zone) {
        zoneRepository.update(zone.getModbusZoneNumber(), zone.getZoneName());
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
