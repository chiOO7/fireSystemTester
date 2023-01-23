package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.AllArgsConstructor;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import ru.chislab.fireSystemTester.ModbusDataSource;
import ru.chislab.fireSystemTester.chapters.Chapter;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;
import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class ConsoleUIManager {
    private static Scanner scanner = new Scanner(System.in);
    private static ChapterManager chapterManager;

    public static void main(String[] args) throws ZoneNotFoundException {
        String LOG4J_CONFIGURATION_PATH = "log4j.properties";
        BasicConfigurator.configure();
        PropertyConfigurator.configure(LOG4J_CONFIGURATION_PATH);
        ModbusDataSource modbusDataSource = new ModbusDataSource();
        ZoneManager zoneManager = new ZoneManager(modbusDataSource);
        chapterManager = new ChapterManager(zoneManager);
        initMenus();
    }

    public static List<ConsoleUIMenu> getStateMenusByZoneNumber(int zoneNumber) throws ZoneNotFoundException {
        List<ConsoleUIMenu> stateMenus = new ArrayList<>();
        Zone zone = chapterManager.getZoneManager().getZoneByZoneNumber(zoneNumber);
        StateMenu stateMenu1 = new StateMenu();
//        stateMenu1.setState(zone.getZoneState().getStates().get(0));
        stateMenu1.setScanner(scanner);
        StateMenu stateMenu2 = new StateMenu();
//        stateMenu2.setState(zone.getZoneState().getStates().get(1));
        stateMenu2.setScanner(scanner);
        stateMenus.add(stateMenu1);
        stateMenus.add(stateMenu2);


        return stateMenus;
    }

    public static List<ConsoleUIMenu> getZoneMenusByChapterNumber(int chapterNumber) throws ZoneNotFoundException {
        List<ConsoleUIMenu> zoneMenus = new ArrayList<>();
        Chapter chapter = chapterManager.getChapterByNumber(chapterNumber);
        for (int i = 0; i < chapter.getZones().size(); i++) {
            ZoneMenu zoneMenu = new ZoneMenu();
            zoneMenu.setZoneName("ZM " + (i + 1));
            zoneMenu.setScanner(scanner);
            zoneMenu.setChapterManager(chapterManager);
            zoneMenu.setSubMenus(getStateMenusByZoneNumber(i + 1));
            zoneMenus.add(zoneMenu);
        }

        return zoneMenus;
    }

    public static List<ConsoleUIMenu> getChapterMenus() throws ZoneNotFoundException {
        List<ConsoleUIMenu> chapterMenus = new ArrayList<>();

        chapterManager.initChaptersFromDevice();

        List<Chapter> chapters = chapterManager.getAvailableChapters();
        for (int i = 0; i < chapters.size(); i++) {
            ChapterMenu chapterMenu = new ChapterMenu();
            chapterMenu.setChapterMenuName("CM " + (i + 1));
            chapterMenu.setChapterMenuNumber(i + 1);
            chapterMenu.setChapterManager(chapterManager);
            chapterMenu.setScanner(scanner);
            chapterMenu.setSubMenus(getZoneMenusByChapterNumber(i + 1));
            chapterMenus.add(chapterMenu);
        }

        return chapterMenus;
    }

    public static StartMenu getStartMenu() throws ZoneNotFoundException {
        StartMenu startMenu = new StartMenu();
        startMenu.setMenuName("Start_menu");
        startMenu.setMenuRowNumber(0);
        startMenu.setScanner(scanner);
        startMenu.setChapterManager(chapterManager);
        List<ConsoleUIMenu> startSubMenus = new ArrayList<>();
        AvailableFromStorageChaptersMenu availableChaptersFromStorage = new AvailableFromStorageChaptersMenu();
        availableChaptersFromStorage.setSubMenus(getChapterMenus());
        availableChaptersFromStorage.setScanner(scanner);
        availableChaptersFromStorage.setChapterManager(chapterManager);
        ReadZonesFromDeviceMenu readZonesFromDeviceMenu = new ReadZonesFromDeviceMenu();
        readZonesFromDeviceMenu.setChapterManager(chapterManager);
        readZonesFromDeviceMenu.setScanner(scanner);
        startSubMenus.add(readZonesFromDeviceMenu);
        startSubMenus.add(availableChaptersFromStorage);

        startMenu.setSubMenus(startSubMenus);

        return startMenu;
    }



    public static void initMenus() throws ZoneNotFoundException {
        StartMenu startMenu = getStartMenu();
        startMenu.processMenu();

    }

}
