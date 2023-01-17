package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.Getter;
import lombok.Setter;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class ZoneMenu extends ConsoleUIMenu{

    private int zoneNumber;
    private String zoneName;
    private ZoneManager zoneManager;

    @Override
    public String toString() {
            return "Зона " + zoneNumber + ": " + zoneName;
    }

    @Override
    protected void printMenuHeader() {
        System.out.println();
        System.out.println("# " + getMenuName() + ": " + zoneName);
        System.out.println("0. Назад");
        System.out.println("1. Обновить состояние зоны");
    }

    @Override
    public void printMenus() {
        printMenuHeader();
        for (int i = 0; i < getSubMenus().size(); i++) {
            System.out.println((i + 2) + ". " + getSubMenus().get(i));
        }
        printMenuFooter();
    }

    @Override
    public void doSomething(int command) {
        if (command == 1) zoneManager.updateZoneStateByZoneNumber(zoneNumber);
        else getSubMenus().get(command - 2).processMenu();
    }

    public ZoneMenu(String menuName, Scanner scanner, List<ConsoleUIMenu> stateMenus, ZoneManager zoneManager) {
        super(menuName, scanner, stateMenus);
        this.zoneManager = zoneManager;
//        try {
//            zoneName = zoneManager.getZoneByZoneNumber(zoneNumber).getZoneName();
//        } catch (ZoneNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }
}
