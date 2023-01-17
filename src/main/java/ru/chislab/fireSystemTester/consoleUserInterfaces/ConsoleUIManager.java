package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.AllArgsConstructor;
import ru.chislab.fireSystemTester.ModbusDataSource;
import ru.chislab.fireSystemTester.chapters.Chapter;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.enums.Events;
import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class ConsoleUIManager {
    private static Scanner scanner = new Scanner(System.in);
    private static ChapterManager chapterManager;
    public static void main(String[] args) {
        ModbusDataSource modbusDataSource = new ModbusDataSource();
        ZoneManager zoneManager = new ZoneManager(modbusDataSource);
        chapterManager = new ChapterManager(zoneManager);
        initMenus();
    }

    public static void initMenus() {
        List<ConsoleUIMenu> chapterMenus = new ArrayList<>();
        for (Chapter chapter : chapterManager.getAvailableChapters()) {
            List<ConsoleUIMenu> zoneMenus = new ArrayList<>();
            for (Zone zone : chapter.getZones()) {
                List<ConsoleUIMenu> stateMenus = new ArrayList<>();
                for (Events state : zone.getZoneState().getStates()) {
                    StateMenu stateMenu = new StateMenu(state, scanner);
                    stateMenus.add(stateMenu);
                }
                ZoneMenu zoneMenu = new ZoneMenu("Зона ", scanner, stateMenus);
                zoneMenus.add(zoneMenu);
            }
            ChapterMenu chapterMenu = new ChapterMenu(chapter.getModbusChapterNumber(), scanner, zoneMenus);
            chapterMenus.add(chapterMenu);
        }
        AvailableChaptersMenu availableChaptersMenu = new AvailableChaptersMenu(scanner, chapterMenus);
        StartMenu startMenu = new StartMenu(scanner, availableChaptersMenu);
        startMenu.processMenu();
    }

}
