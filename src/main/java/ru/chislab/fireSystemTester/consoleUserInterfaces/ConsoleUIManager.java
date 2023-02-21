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

    private final static Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);
//    private static final String LOG4J_CONFIGURATION_PATH = "log4j.properties";
    private Scanner scanner;
    private ChapterManager chapterManager;

    EnumSet<States> settableStates = EnumSet.of(States.DISARMING_INPUT, States.ARMING_INPUT);


    public ConsoleUIManager(ChapterManager chapterManager) {
        this.chapterManager = chapterManager;
        this.scanner = new Scanner(System.in);
    }


    public StartMenu getStartMenu() {
        StartMenu startMenu = new StartMenu("Главное меню", chapterManager);
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
            chapterMenu.setConsoleUIManager(this);
            chaptersFromDeviceMenu.add(chapterMenu);
        }

        return chaptersFromDeviceMenu;
    }

    public List<ConsoleUIMenu> getChaptersFromDbMenu() {
        List<ConsoleUIMenu> chaptersMenu = new ArrayList<>();
        List<Chapter> chapters = getChapterManager().getAvailableChapters();
        for (int i = 0; i < chapters.size(); i++) {
            ChapterMenuDb chapterMenu = new ChapterMenuDb("Раздел " + (i + 1),
                    chapters.get(i).getModbusChapterNumber(),
                    chapterManager);
            chapterMenu.setScanner(getScanner());
            chapterMenu.setConsoleUIManager(this);
            chaptersMenu.add(chapterMenu);
        }

        return chaptersMenu;
    }

    public List<ConsoleUIMenu> getZonesFromChapterByChapterNumberMenu(int number) throws ZoneNotFoundException {
        List<ConsoleUIMenu> zonesFromChapterMenu = new ArrayList<>();
        List<Zone> zones = getChapterManager().getChapterByNumber(number).getZones();
        for (Zone zone : zones) {
            ZoneMenu zoneMenu = new ZoneMenu("Зона " + (zone.getModbusZoneNumber()),
                        zone.getModbusZoneNumber(), chapterManager);

            zoneMenu.setScanner(scanner);
            zoneMenu.setConsoleUIManager(this);
            zonesFromChapterMenu.add(zoneMenu);
        }

        return zonesFromChapterMenu;
    }

    public List<ConsoleUIMenu> getZonesFromDbByChapterNumberMenu(int chapterNumber) throws ZoneNotFoundException {
        List<ConsoleUIMenu> zonesFromChapterMenu = new ArrayList<>();
        List<Zone> zones = getChapterManager().getChapterByNumber(chapterNumber).getZones();
        for (Zone zone : zones) {
            ZoneMenuDb zoneMenu = new ZoneMenuDb("Зона " + (zone.getModbusZoneNumber()),
                    zone.getModbusZoneNumber(), chapterManager);

            zoneMenu.setScanner(scanner);
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
