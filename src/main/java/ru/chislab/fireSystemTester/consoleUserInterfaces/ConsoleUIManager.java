package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.AllArgsConstructor;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import ru.chislab.fireSystemTester.ModbusDataSource;
import ru.chislab.fireSystemTester.chapters.Chapter;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
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
        String LOG4J_CONFIGURATION_PATH = "log4j.properties";
        BasicConfigurator.configure();
        PropertyConfigurator.configure(LOG4J_CONFIGURATION_PATH);
        ModbusDataSource modbusDataSource = new ModbusDataSource();
        ZoneManager zoneManager = new ZoneManager(modbusDataSource);
        chapterManager = new ChapterManager(zoneManager);
        initMenus();
    }





    public static void initMenus() {
        List<ConsoleUIMenu> chapterMenus = new ArrayList<>();
        for (Chapter chapter : chapterManager.getAvailableChaptersFromStorage()) {
            List<ConsoleUIMenu> zoneMenus = new ArrayList<>();
            for (Zone zone : chapter.getZones()) {
                List<ConsoleUIMenu> stateMenus = new ArrayList<>();
                for (int i = 0; i < zone.getZoneState().getStates().size(); i++) {
                    StateMenu stateMenu = new StateMenu(zone.getZoneState().getStates().get(i), scanner);
                    stateMenu.setStateNumber(i + 1);
                    stateMenus.add(stateMenu);
                }
                ZoneMenu zoneMenu = new ZoneMenu("Зона ", scanner, stateMenus,
                        chapterManager.getZoneManager());
                zoneMenu.setZoneNumber(zone.getModbusZoneNumber());
                zoneMenus.add(zoneMenu);
            }
            ChapterMenu chapterMenu = new ChapterMenu(chapter.getModbusChapterNumber(),
                    scanner, zoneMenus, chapterManager.getZoneManager());
            chapterMenus.add(chapterMenu);
        }
        AvailableChaptersMenu availableChaptersMenu = new AvailableChaptersMenu(scanner, chapterMenus);
        List<ConsoleUIMenu> startMenuSubMenus = new ArrayList<>();
        startMenuSubMenus.add(new ReadZonesFromDeviceMenu(scanner, chapterManager.getZoneManager()));
        startMenuSubMenus.add(availableChaptersMenu);
        StartMenu startMenu = new StartMenu(scanner, startMenuSubMenus);
        startMenu.processMenu();
    }

}
