package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.Getter;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;

@Getter
public class ZoneMenu extends ConsoleUIMenu{

    private final ChapterManager chapterManager;

    private String zoneName;

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
        System.out.println("1. Обновить состояние зоны");
    }

    @Override
    public void processCommand(int command) {
        if (!getSubMenus().isEmpty() && command > 1) {
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
            System.out.println();
            if (command == -1) System.exit(0);
            if (command == 0) break;
            if (command == 1) {
                getChapterManager().getZoneManager().updateZoneStateByZoneNumber(zoneNumber);
            }
            processCommand(command);
        }
    }
}
