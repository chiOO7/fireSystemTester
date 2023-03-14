package ru.chislab.fireSystemTester.consoleUserInterfaces;

import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.zones.Zone;

public class ZoneMenuDb extends ZoneMenu {
    public ZoneMenuDb(String menuName, int zoneNumber, ChapterManager chapterManager) {
        super(menuName, zoneNumber, chapterManager);
    }

    @Override
    public void processMenu() {
        while (true) {
            getChapterManager().getZoneManager().updateZoneStateByZoneNumber(getZoneNumber());
            setSubMenus(getConsoleUIManager().getStatesFromZoneByZoneNumberMenu(getZoneNumber()));
            int command = checkCommand();
            if (command == 0) break;
            if (command == 1) {
                System.out.println("Введите новое имя зоны:");
                String newName = getScanner().nextLine();
                Zone zone = getChapterManager().getZoneManager().getZoneByZoneNumber(getZoneNumber());
                zone.setZoneName(newName);
                getChapterManager().getZoneManager().updateZone(zone);
                break;
            }
            if (command == 2) {
                getChapterManager().getZoneManager().updateZoneStateByZoneNumber(getZoneNumber());
            }
            processCommand(command);
        }
    }
}
