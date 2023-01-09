package ru.chislab.fireSystemTester;

import ru.chislab.fireSystemTester.enums.Events;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.ArrayList;
import java.util.List;


public class ApplicationRunner {
    public static void main(String[] args) {
        ModbusDataSource dataSource = new ModbusDataSource();

        ZoneManager zoneManager = new ZoneManager(dataSource);
        zoneManager.defineZones();
        zoneManager.updateZonesState();

        System.out.println("\nZones :");

        zoneManager.getZones().stream().map(zone -> zone.toString()).forEach(System.out::println);

        int number = 1;
        System.out.println("\nZone" + number + " :");
        zoneManager.updateZoneStateByZoneNumber(number);
        try {
            System.out.println(zoneManager.getZoneByZoneNumber(number).getState());
        } catch (ZoneNotFoundException e) {
            System.err.println(e.getMessage());
        }
        List<Events> state = new ArrayList<>();
        state.add(Events.FIRE);
        state.add(Events.ACTUATOR_FAILURE);
        zoneManager.setZoneStateByZoneNumber(number, state);

        zoneManager.updateZoneStateByZoneNumber(number);
        try {
            System.out.println(zoneManager.getZoneByZoneNumber(number).getState());
        } catch (ZoneNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
