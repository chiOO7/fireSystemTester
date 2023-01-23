package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterMenu extends ConsoleUIMenu{
    private int chapterMenuNumber;
    private String chapterMenuName;
    private ChapterManager chapterManager;

//    public ChapterMenu(String menuName, Scanner scanner, List<ConsoleUIMenu> subMenus, int chapterMenuNumber,
//                       ChapterManager chapterManager) {
//        super(menuName, scanner, subMenus);
//        List<Zone> zones = chapterManager.getChapterByNumber(chapterMenuNumber).getZones();
//        List<ZoneMenu> zoneMenus = new ArrayList<>();
//        for (Zone zone : zones) {
//            ZoneMenu zoneMenu = new ZoneMenu("zm", scanner, chapterManager);
//        }
//        this.chapterMenuNumber = chapterMenuNumber;
//        this.chapterManager = chapterManager;
//    }

    @Override
    protected void printMenuHeader() {
        System.out.println();
        System.out.println("# " + getMenuName() + ": " + chapterMenuName);
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
            ZoneManager zoneManager = chapterManager.getZoneManager();
            List<Zone> zones = zoneManager.getZonesByChapterNumber(chapterMenuNumber);
            for (Zone zone : zones) {
                zoneManager.updateZoneStateByZoneNumber(zone.getModbusZoneNumber());
            }
        }
        else getSubMenus().get(command - 2).processMenu();
    }
}
