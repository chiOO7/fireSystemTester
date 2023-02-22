package ru.chislab.fireSystemTester.consoleUserInterfaces;

import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;
import ru.chislab.fireSystemTester.zones.Zone;

public class ZoneMenuDb extends ZoneMenu {
    public ZoneMenuDb(String menuName, int zoneNumber, ChapterManager chapterManager) throws ZoneNotFoundException {
        super(menuName, zoneNumber, chapterManager);
    }

    @Override
    public void processMenu() throws ZoneNotFoundException {
        while (true) {
            getChapterManager().getZoneManager().updateZoneStateByZoneNumber(getZoneNumber());
            setSubMenus(getConsoleUIManager().getStatesFromZoneByZoneNumberMenu(getZoneNumber()));
            int command = checkCommand();
            if (command == 0) break;
            if (command == 1) {
                System.out.println("Введите новое имя зоны:");
                String newName = getScanner().nextLine();
                try {
                    Zone zone = getChapterManager().getZoneManager().getZoneByZoneNumber(getZoneNumber());
                    zone.setZoneName(newName);
                    getChapterManager().getZoneManager().updateZone(zone);
                } catch (ZoneNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            if (command == 2) {
                getChapterManager().getZoneManager().updateZoneStateByZoneNumber(getZoneNumber());
            }
            processCommand(command);
        }
    }
}
