package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.Getter;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;
import ru.chislab.fireSystemTester.zones.Zone;

@Getter
public class ZoneMenu extends ConsoleUIMenu{

    private final ChapterManager chapterManager;

    private final String zoneName;

    private final int zoneNumber;

    public ZoneMenu(String menuName, int zoneNumber, ChapterManager chapterManager) throws ZoneNotFoundException {
        super(menuName);
        this.zoneNumber = zoneNumber;
        this.chapterManager = chapterManager;
        this.zoneName = chapterManager.getZoneManager().getZoneByZoneNumber(zoneNumber).getZoneName();
    }

    @Override
    protected void printMenuHeader() {
        System.out.println("# " + getMenuName() + ": " + zoneName);
        System.out.println("0. Назад");
        System.out.println("1. Задать наименование зоны");
        System.out.println("2. Обновить состояние зоны");
    }

    @Override
    public void processCommand(int command) throws ZoneNotFoundException {
        if (!getSubMenus().isEmpty() && command > 2) {
            getSubMenus().get(command - 3).processMenu();
        }
    }

    @Override
    public void printSubMenus() {
        printMenuHeader();
        for (int i = 0; i < getSubMenus().size(); i++) {
            StateMenu stateMenu = (StateMenu) getSubMenus().get(i);
            System.out.println((i + 3) + ". " + stateMenu.getMenuName());
        }
        printMenuFooter();
    }

    @Override
    public void processMenu() throws ZoneNotFoundException {
        while (true) {
            chapterManager.getZoneManager().updateZoneStateByZoneNumber(zoneNumber);
            setSubMenus(getConsoleUIManager().getStatesFromZoneByZoneNumberMenu(zoneNumber));
            printSubMenus();
            int command = getScanner().nextInt();
            System.out.println();
            if (command == -1) System.exit(0);
            if (command == 0) break;
            if (command == 1) {
                System.out.println("Введите новое имя зоны:");
                String newName = getScanner().next();
                try {
                    Zone zone = chapterManager.getZoneManager().getZoneByZoneNumber(zoneNumber);
                    zone.setZoneName(newName);
                } catch (ZoneNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            if (command == 2) {
                chapterManager.getZoneManager().updateZoneStateByZoneNumber(zoneNumber);
            }
            processCommand(command);
        }
    }
}
