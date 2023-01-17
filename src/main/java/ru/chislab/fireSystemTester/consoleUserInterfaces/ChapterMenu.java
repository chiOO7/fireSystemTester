package ru.chislab.fireSystemTester.consoleUserInterfaces;

import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ChapterMenu extends ConsoleUIMenu{
    private int chapterNumber;

    private String chapterName;

    private ZoneManager zoneManager;

    public ChapterMenu(int number, Scanner scanner, List<ConsoleUIMenu> zoneMenus, ZoneManager zoneManager) {
        super("Раздел " + number, scanner, zoneMenus);
        this.chapterNumber = number;
        this.zoneManager = zoneManager;
//        this.chapterName = "Имя раздела не установлено";
    }

    public ChapterMenu(int number, String chapterName, Scanner scanner, List<ConsoleUIMenu> zoneMenus, ZoneManager zoneManager) {
        super("Раздел " + number, scanner, zoneMenus);
        this.chapterNumber = number;
        this.zoneManager = zoneManager;
        this.chapterName = chapterName;
    }

    @Override
    public String toString() {
        return "Раздел " + chapterNumber + ": " + chapterName;
    }

    @Override
    protected void printMenuHeader() {
        System.out.println();
        System.out.println("# " + getMenuName() + ": " + chapterName);
        System.out.println("0. Назад");
        System.out.println("1. Обновить состояние зон");
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
        if (command == 1) {
            List<Zone> zones = zoneManager.getZonesByChapterNumber(chapterNumber);
            for (Zone zone : zones) {
                zoneManager.updateZoneStateByZoneNumber(zone.getConfiguration().getModbusZoneNumber());
            }
        }
        else getSubMenus().get(command - 2).processMenu();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChapterMenu that = (ChapterMenu) o;
        return chapterNumber == that.chapterNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chapterNumber);
    }
}
