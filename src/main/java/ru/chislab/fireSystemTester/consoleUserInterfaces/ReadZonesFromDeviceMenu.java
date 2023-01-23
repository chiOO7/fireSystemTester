package ru.chislab.fireSystemTester.consoleUserInterfaces;

import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadZonesFromDeviceMenu extends ConsoleUIMenu{

    private final ZoneManager zoneManager;
    public ReadZonesFromDeviceMenu(Scanner scanner, ZoneManager zoneManager) {
        super("Read Zones", scanner, new ArrayList<>());
        this.zoneManager = zoneManager;
    }

    @Override
    public void processMenu() {
        zoneManager.readZoneConfigsFromDevice();
        while (true) {
            printMenuHeader();
            int command = getScanner().nextInt();
            if (command == -1) System.exit(0);
            if (command == 0) break;
            doSomething(command);
        }
    }

    @Override
    public void doSomething(int command) {
        if (command == 1) {
            zoneManager.saveZonesToStorage();
        }
    }

    @Override
    protected void printMenuHeader() {
        System.out.println();
        System.out.println("# " + getMenuName() + ":");
        System.out.println("0. Назад");
        System.out.println("1. Сохранить найденные зоны в базу");
    }
}
