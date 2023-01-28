package ru.chislab.fireSystemTester.consoleUserInterfaces;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.chislab.fireSystemTester.modbus.ModbusDataSource;
import ru.chislab.fireSystemTester.modbus.ModbusDataSourceForTests;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.Scanner;

class ConsoleUIManagerTest {
    private ConsoleUIManager consoleUIManager;
    private ChapterManager chapterManager;
    private Scanner scanner;

    @BeforeEach
    void setUp() {
        ModbusDataSource modbusDataSource = new ModbusDataSourceForTests();
        ZoneManager zoneManager = new ZoneManager(modbusDataSource);
        chapterManager = new ChapterManager(zoneManager);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getStartMenu() {
        scanner = new Scanner("0");
        consoleUIManager = new ConsoleUIManager(chapterManager, scanner);
        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();
    }

    @Test
    void getReadZonesFromDeviceMenu() {
        scanner = new Scanner("1 0 0");
        consoleUIManager = new ConsoleUIManager(chapterManager, scanner);
        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();
    }

    @Test
    void getAvailableFromStorageChaptersMenu() {
        scanner = new Scanner("2 0 0");
        consoleUIManager = new ConsoleUIManager(chapterManager, scanner);
        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();
    }

    @Test
    void getChaptersFromDeviceMenu() {
        scanner = new Scanner("1 2 0 0 0");
        consoleUIManager = new ConsoleUIManager(chapterManager, scanner);
        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();
    }

    @Test
    void getZonesFromChapterByChapterNumberMenu() {
        scanner = new Scanner("1 2 2 0 0 0 0 0");
        consoleUIManager = new ConsoleUIManager(chapterManager, scanner);
        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();
    }

    @Test
    void getStatesFromZoneByZoneNumberMenu() {
        scanner = new Scanner("1 2 2 2 0 0 0 0 0");
        consoleUIManager = new ConsoleUIManager(chapterManager, scanner);
        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();
    }

    @Test
    void getChangeStatesFromZoneByZoneNumberMenu() {
        scanner = new Scanner("1 2 2 2 2 0 0 0 0 0 0 0");
        consoleUIManager = new ConsoleUIManager(chapterManager, scanner);
        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();
    }
}