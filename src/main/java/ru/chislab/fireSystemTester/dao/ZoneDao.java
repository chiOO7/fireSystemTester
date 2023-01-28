package ru.chislab.fireSystemTester.dao;

import ru.chislab.fireSystemTester.zones.Zone;

import java.util.ArrayList;
import java.util.List;

public class ZoneDao {
    private List<Zone> zonesStorage;

    public ZoneDao() {
        zonesStorage = new ArrayList<>();
    }

    public void saveZonesToStorage(List<Zone> zones) {
        for (Zone zone : zones) {
            if (!zonesStorage.contains(zone)) zonesStorage.add(zone);
        }
        //zonesStorage.stream().forEach(System.out::println);
    }

    public void saveZoneToStorage(Zone zone) {
        if (!zonesStorage.contains(zone)) zonesStorage.add(zone);
    }

    public List<Zone> getZonesFromStorage() {
        return zonesStorage;
    }

    public Zone getZoneFromStorageByZoneNumber(int number) {
        for (Zone zone : zonesStorage) {
            if (zone.getModbusZoneNumber() == number) return zone;
        }
        return null;
    }
}
