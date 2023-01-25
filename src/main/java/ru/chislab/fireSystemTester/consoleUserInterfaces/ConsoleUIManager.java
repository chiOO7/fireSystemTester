package ru.chislab.fireSystemTester.consoleUserInterfaces;

import com.intelligt.modbus.jlibmodbus.Modbus;
import lombok.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chislab.fireSystemTester.ApplicationRunner;
import ru.chislab.fireSystemTester.ModbusDataSource;
import ru.chislab.fireSystemTester.ModbusDataSourceForTests;
import ru.chislab.fireSystemTester.chapters.Chapter;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.enums.States;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;
import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneManager;
import ru.chislab.fireSystemTester.zones.ZoneState;

import java.io.Console;
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

    public static void main(String[] args) {
        BasicConfigurator.configure();
        PropertyConfigurator.configure(LOG4J_CONFIGURATION_PATH);
        Modbus.setLogLevel(Modbus.LogLevel.LEVEL_VERBOSE);
        ModbusDataSource modbusDataSource = new ModbusDataSource();
//        ModbusDataSource modbusDataSource = new ModbusDataSourceForTests();
        ZoneManager zoneManager = new ZoneManager(modbusDataSource);
        ChapterManager chapterManager = new ChapterManager(zoneManager);
        ConsoleUIManager consoleUIManager = new ConsoleUIManager(chapterManager);

        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();
    }

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
        readZonesFromDeviceMenu.setConsoleUIManager(this);

        return readZonesFromDeviceMenu;
    }

    public List<ConsoleUIMenu> getChaptersFromDeviceMenu() {
        List<ConsoleUIMenu> chaptersFromDeviceMenu = new ArrayList<>();
        List<Chapter> chapters = getChapterManager().getAvailableChapters();
        for (int i = 0; i < chapters.size(); i++) {
            ChapterMenu chapterMenu = new ChapterMenu("Раздел " + (i + 1), chapters.get(i).getModbusChapterNumber());
            chapterMenu.setScanner(getScanner());
            chapterMenu.setChapterManager(getChapterManager());
            chapterMenu.setConsoleUIManager(this);
            chaptersFromDeviceMenu.add(chapterMenu);
        }

        return chaptersFromDeviceMenu;
    }

    public List<ConsoleUIMenu> getZonesFromChapterByChapterNumberMenu(int number) {
        List<ConsoleUIMenu> zonesFromChapterMenu = new ArrayList<>();
        List<Zone> zones = getChapterManager().getChapterByNumber(number).getZones();
        for (int i = 0; i < zones.size(); i++) {
            ZoneMenu zoneMenu = new ZoneMenu("Зона " + (zones.get(i).getModbusZoneNumber()), zones.get(i).getModbusZoneNumber());
            zoneMenu.setScanner(getScanner());
            zoneMenu.setChapterManager(getChapterManager());
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
                StateMenu stateMenu = new StateMenu("Состояние " + (i + 1) + ": " + state.getStates().get(i),
                        zone);
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
        CommonMenu availableFromStorageChaptersMenu = new CommonMenu("Доступные из базы данных разделы");
//        availableFromStorageChaptersMenu.setScanner(getScanner());

        return availableFromStorageChaptersMenu;
    }

//    public void initMenus() throws ZoneNotFoundException {
//        StartMenu startMenu = getStartMenu();
//        startMenu.processMenu();
//    }
}
