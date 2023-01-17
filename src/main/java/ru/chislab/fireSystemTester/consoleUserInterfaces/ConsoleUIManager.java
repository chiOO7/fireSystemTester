package ru.chislab.fireSystemTester.consoleUserInterfaces;

import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUIManager {

    private final ZoneManager zoneManager;

    Scanner scanner = new Scanner(System.in);

    private List<ZoneMenu> zoneMenus = new ArrayList<>();

    public ConsoleUIManager(ZoneManager zoneManager) {
        this.zoneManager = zoneManager;

//        List<Zone> zones = zoneManager.getZones();
//
//        for (Zone zone : zones) {
//            StateMenu stateMenu1 = new StateMenu(1, zone.getState().getStates().get(0));
//            StateMenu stateMenu2 = new StateMenu(2, zone.getState().getStates().get(1));
//            List<StateMenu> stateMenus = new ArrayList<>();
//            stateMenus.add(stateMenu1);
//            stateMenus.add(stateMenu2);
//
//            ZoneMenu zoneMenu = new ZoneMenu(zone.getConfiguration().getModbusZoneNumber(), stateMenus);
//
//            zoneMenus.add(zoneMenu);
//        }
    }

    public void printMenus(ConsoleUIMenu consoleUIMenu) {

    }

    public int printZoneMenus() {
        System.out.println();
        System.out.println("0. Назад");
        System.out.println("1. Обновить состояние зон");
        for (int i = 0; i < zoneMenus.size(); i++) {
            System.out.println((i + 2) + zoneMenus.get(i).toString());
        }
        System.out.println("Введите номер команды от 0 до " + (zoneMenus.size() + 1));
        System.out.println();
        return scanner.nextInt();
    }

}
