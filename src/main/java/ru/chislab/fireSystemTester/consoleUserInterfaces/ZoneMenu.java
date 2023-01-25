package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.*;


@Data
public class ZoneMenu extends ConsoleUIMenu{

    private String zoneName = "Zone name not set";

    private int zoneNumber;

    public ZoneMenu(String menuName, int zoneNumber) {

        super(menuName);
        this.zoneNumber = zoneNumber;
    }

    @Override
    protected void printMenuHeader() {
        System.out.println();
        System.out.println("# " + getMenuName() + ": " + zoneName);
        System.out.println("0. Назад");
        System.out.println("1. Обновить состояние зоны");
    }

    @Override
    public void processCommand(int command) {
        if (!getSubMenus().isEmpty()) {
            getSubMenus().get(command - 2).processMenu();
        }
    }

    @Override
    public void printSubMenus() {
        printMenuHeader();
        for (int i = 0; i < getSubMenus().size(); i++) {
            StateMenu stateMenu = (StateMenu) getSubMenus().get(i);
            System.out.println((i + 2) + ". " + stateMenu.getMenuName());
        }
        printMenuFooter();
    }

    @Override
    public void processMenu() {
        while (true) {
            getChapterManager().getZoneManager().updateZoneStateByZoneNumber(zoneNumber);
            setSubMenus(getConsoleUIManager().getStatesFromZoneByZoneNumberMenu(zoneNumber));
            printSubMenus();
            int command = getScanner().nextInt();
            if (command == -1) System.exit(0);
            if (command == 0) break;
            processCommand(command);
        }
    }
}
