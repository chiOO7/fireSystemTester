package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.*;
import ru.chislab.fireSystemTester.ModbusDataSource;
import ru.chislab.fireSystemTester.chapters.Chapter;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.enums.States;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;
import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneManager;
import ru.chislab.fireSystemTester.zones.ZoneState;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
public class ConsoleUIManager {
    private Scanner scanner;
    private ChapterManager chapterManager;

    public ConsoleUIManager() {
        chapterManager = new ChapterManager(new ZoneManager(new ModbusDataSource()));
        scanner = new Scanner(System.in);
    }

    public ConsoleUIManager(ChapterManager chapterManager) {
        this.chapterManager = chapterManager;
        this.scanner = new Scanner(System.in);
    }

    public ConsoleUIManager(ChapterManager chapterManager, Scanner scanner) {
        this.chapterManager = chapterManager;
        this.scanner = scanner;
    }

//    public static void main(String[] args) throws ZoneNotFoundException {
//        String LOG4J_CONFIGURATION_PATH = "log4j.properties";
//        BasicConfigurator.configure();
//        PropertyConfigurator.configure(LOG4J_CONFIGURATION_PATH);
//
//        ModbusDataSource modbusDataSource = new ModbusDataSource();
//        ZoneManager zoneManager = new ZoneManager(modbusDataSource);
//        ChapterManager chapterManager = new ChapterManager(zoneManager);
//        ConsoleUIManager consoleUIManager = new ConsoleUIManager(chapterManager);
//        consoleUIManager.initMenus();
//    }

    public StartMenu getStartMenu() {
        StartMenu startMenu = new StartMenu("Главное меню");
        startMenu.setScanner(getScanner());
        startMenu.addSubMenu(getReadZonesFromDeviceMenu());
        startMenu.addSubMenu(getAvailableFromStorageChaptersMenu());

        return startMenu;
    }

    public ConsoleUIMenu getReadZonesFromDeviceMenu() {
        ReadZonesFromDeviceMenu readZonesFromDeviceMenu = new ReadZonesFromDeviceMenu("Считанные из устройства разделы");
        readZonesFromDeviceMenu.setScanner(getScanner());
        readZonesFromDeviceMenu.setChapterManager(getChapterManager());
        getChapterManager().initChaptersFromDevice();
        getChapterManager().getZoneManager().updateZonesState();
        readZonesFromDeviceMenu.addSubMenus(getChaptersFromDeviceMenu());

        return readZonesFromDeviceMenu;
    }

    public List<ConsoleUIMenu> getChaptersFromDeviceMenu() {
        List<ConsoleUIMenu> chaptersFromDeviceMenu = new ArrayList<>();
        List<Chapter> chapters = getChapterManager().getAvailableChapters();
        for (int i = 0; i < chapters.size(); i++) {
            ChapterMenu chapterMenu = new ChapterMenu("Раздел " + (i + 1));
            chapterMenu.setScanner(getScanner());
            chapterMenu.addSubMenus(getZonesFromChapterByChapterNumberMenu(chapters.get(i).getModbusChapterNumber()));
            chaptersFromDeviceMenu.add(chapterMenu);
        }

        return chaptersFromDeviceMenu;
    }

    public List<ConsoleUIMenu> getZonesFromChapterByChapterNumberMenu(int number) {
        List<ConsoleUIMenu> zonesFromChapterMenu = new ArrayList<>();
        List<Zone> zones = getChapterManager().getChapterByNumber(number).getZones();
        for (int i = 0; i < zones.size(); i++) {
            ZoneMenu zoneMenu = new ZoneMenu("Зона " + (zones.get(i).getModbusZoneNumber()));
            zoneMenu.setScanner(getScanner());
            zoneMenu.addSubMenus(getStatesFromZoneByZoneNumberMenu(zones.get(i).getModbusZoneNumber()));
            zonesFromChapterMenu.add(zoneMenu);
        }

        return zonesFromChapterMenu;
    }

    public List<ConsoleUIMenu> getStatesFromZoneByZoneNumberMenu(int number) {
        List<ConsoleUIMenu> statesFromZoneMenu = new ArrayList<>();
        try {
            Zone zone = getChapterManager().getZoneManager().getZoneByZoneNumber(number);
            ZoneState state = zone.getZoneState();

            for (int i = 0; i < state.getStates().size(); i++) {
                StateMenu stateMenu = new StateMenu("Состояние " + (i + 1) + ": " + state.getStates().get(i),
                        zone);
                stateMenu.setScanner(getScanner());
                List<ConsoleUIMenu> changeStateMenus = new ArrayList<>();
                for (States settableState : States.values()) {
                    ChangeStateMenu changeStateMenu = new ChangeStateMenu(settableState.name(), zone, i);
                    changeStateMenu.setScanner(getScanner());
                    changeStateMenus.add(changeStateMenu);
                }
                stateMenu.addSubMenus(changeStateMenus);
                statesFromZoneMenu.add(stateMenu);
            }

        } catch (ZoneNotFoundException e) {
            throw new RuntimeException(e);
        }

        return statesFromZoneMenu;
    }

    public ConsoleUIMenu getAvailableFromStorageChaptersMenu() {
        CommonMenu availableFromStorageChaptersMenu = new CommonMenu("Доступные из базы данных разделы");
        availableFromStorageChaptersMenu.setScanner(getScanner());

        return availableFromStorageChaptersMenu;
    }

    public void initMenus() throws ZoneNotFoundException {
        StartMenu startMenu = getStartMenu();
        startMenu.processMenu();
    }
}
