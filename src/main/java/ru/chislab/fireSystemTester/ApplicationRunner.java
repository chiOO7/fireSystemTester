package ru.chislab.fireSystemTester;

import ru.chislab.fireSystemTester.zones.ZoneManager;


public class ApplicationRunner {
    public static void main(String[] args) throws InterruptedException {
        ModbusDataSource dataSource = new ModbusDataSource();

        ZoneManager zoneManager = new ZoneManager(dataSource);
        zoneManager.defineZones();


            zoneManager.updateZonesState();

            System.out.println("\nZones :");

            zoneManager.getZones().stream().map(zone -> zone.toString()).forEach(System.out::println);
            Thread.sleep(1000);

    }
}
