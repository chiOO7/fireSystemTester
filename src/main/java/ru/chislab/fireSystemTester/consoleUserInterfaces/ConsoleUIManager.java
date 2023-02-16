package ru.chislab.fireSystemTester.consoleUserInterfaces;


import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chislab.fireSystemTester.ApplicationRunner;
//import ru.chislab.fireSystemTester.modbus.ModbusDataSource;
import ru.chislab.fireSystemTester.chapters.Chapter;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.enums.States;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;
import ru.chislab.fireSystemTester.zones.Zone;
//import ru.chislab.fireSystemTester.zones.ZoneManager;
import ru.chislab.fireSystemTester.zones.ZoneState;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

@Data
public class ConsoleUIManager {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationRunner.class.getName());
    private static final String LOG4J_CONFIGURATION_PATH = "log4j.properties";
    private Scanner scanner;
    private ChapterManager chapterManager;

    EnumSet<States> settableStates = EnumSet.of(States.DISARMING_INPUT, States.ARMING_INPUT);

//    public ConsoleUIManager() {
//        chapterManager = new ChapterManager(new ZoneManager(new ModbusDataSource()));
//        scanner = new Scanner(System.in);
//    }

    public ConsoleUIManager(ChapterManager chapterManager) {
        this.chapterManager = chapterManager;
        this.scanner = new Scanner(System.in);
    }

    public ConsoleUIManager(ChapterManager chapterManager, Scanner scanner) {
        this.chapterManager = chapterManager;
        this.scanner = scanner;
    }

    public StartMenu getStartMenu() {
        StartMenu startMenu = new StartMenu("Главное меню");
        startMenu.setScanner(getScanner());
        startMenu.addSubMenu(getReadZonesFromDeviceMenu());
        startMenu.addSubMenu(getAvailableFromStorageChaptersMenu());

        return startMenu;
    }

    public ConsoleUIMenu getReadZonesFromDeviceMenu() {
        ReadZonesFromDeviceMenu readZonesFromDeviceMenu = new ReadZonesFromDeviceMenu("Считать разделы из устройства");
        readZonesFromDeviceMenu.setScanner(getScanner());
        readZonesFromDeviceMenu.setChapterManager(getChapterManager());
        readZonesFromDeviceMenu.setConsoleUIManager(this);

        return readZonesFromDeviceMenu;
    }

    public List<ConsoleUIMenu> getChaptersFromDeviceMenu() {
        List<ConsoleUIMenu> chaptersFromDeviceMenu = new ArrayList<>();
        List<Chapter> chapters = getChapterManager().getAvailableChapters();
        for (int i = 0; i < chapters.size(); i++) {
            ChapterMenu chapterMenu = new ChapterMenu("Раздел " + (i + 1),
                    chapters.get(i).getModbusChapterNumber(),
                    chapterManager);
            chapterMenu.setScanner(getScanner());
            //chapterMenu.setChapterManager(getChapterManager());
            chapterMenu.setConsoleUIManager(this);
            chaptersFromDeviceMenu.add(chapterMenu);
        }

        return chaptersFromDeviceMenu;
    }

    public List<ConsoleUIMenu> getZonesFromChapterByChapterNumberMenu(int number) {
        List<ConsoleUIMenu> zonesFromChapterMenu = new ArrayList<>();
        List<Zone> zones = getChapterManager().getChapterByNumber(number).getZones();
        for (Zone zone : zones) {
            ZoneMenu zoneMenu = null;
            try {
                zoneMenu = new ZoneMenu("Зона " + (zone.getModbusZoneNumber()),
                        zone.getModbusZoneNumber(), chapterManager);
            } catch (ZoneNotFoundException e) {
                logger.error(e.getMessage());
            }
            zoneMenu.setScanner(getScanner());
           // zoneMenu.setChapterManager(getChapterManager());
            zoneMenu.setConsoleUIManager(this);
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
                StateMenu stateMenu = new StateMenu("Состояние " + (i + 1) + ": " + state.getStates().get(i));
                stateMenu.setScanner(getScanner());
                List<ConsoleUIMenu> changeStateMenus = new ArrayList<>();
                for (States settableState : settableStates) {
                    ChangeStateMenu changeStateMenu = new ChangeStateMenu(settableState.name(), zone, i);
                    changeStateMenu.setScanner(getScanner());
                    changeStateMenu.setChapterManager(getChapterManager());
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
        AvailableFromStorageChaptersMenu availableFromStorageChaptersMenu =
                new AvailableFromStorageChaptersMenu("Доступные из базы данных разделы");

        availableFromStorageChaptersMenu.setScanner(getScanner());
        availableFromStorageChaptersMenu.setChapterManager(getChapterManager());
        availableFromStorageChaptersMenu.setConsoleUIManager(this);

        return availableFromStorageChaptersMenu;
    }
}
