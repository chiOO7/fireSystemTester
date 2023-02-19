package ru.chislab.fireSystemTester.consoleUserInterfaces;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;
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
        chapterManager.initChaptersFromDevice();
        consoleUIManager = new ConsoleUIManager(chapterManager);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    //getStartMenu()
    void showStartMenuTest() throws ZoneNotFoundException {
        System.out.println("Start of show start menu TEST");
        scanner = new Scanner("0");
        //consoleUIManager = new ConsoleUIManager(chapterManager, scanner);
        consoleUIManager.setScanner(scanner);
        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();
        System.out.println();
        System.out.println();
        System.out.println("End of show start menu TEST");
    }

    @Test
    //getReadZonesFromDeviceMenu()
    void enterToReadingFromDeviceChaptersMenuTest() throws ZoneNotFoundException {
        System.out.println("Start of enter to reading from device chapters menu TEST");
        scanner = new Scanner("1 0 0");
        //consoleUIManager = new ConsoleUIManager(chapterManager, scanner);
        consoleUIManager.setScanner(scanner);
        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();
        System.out.println();
        System.out.println();
        System.out.println("End of enter to reading from device chapters menu TEST");
    }

    @Test
    //getAvailableFromStorageChaptersMenu()
    void enterToAvailableFromStorageChaptersMenuTest() throws ZoneNotFoundException {
        enterToReadingFromDeviceChaptersMenuTest();
        System.out.println("Start of enter to available from storage chapters menu TEST");
        scanner = new Scanner("2 0 0 0");
        //consoleUIManager = new ConsoleUIManager(chapterManager, scanner);
        consoleUIManager.setScanner(scanner);
        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();
        System.out.println();
        System.out.println();
        System.out.println("End of enter to available from storage chapters menu TEST");
    }

    @Test
    //getChaptersFromDeviceMenu()
    void enterToChapterMenuTest() throws ZoneNotFoundException {
        System.out.println("Start of enter to chapter menu TEST");
        scanner = new Scanner("1 2 0 0 0");
        //consoleUIManager = new ConsoleUIManager(chapterManager, scanner);
        consoleUIManager.setScanner(scanner);
        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();
        System.out.println();
        System.out.println();
        System.out.println("End of enter to chapter menu TEST");
    }

    @Test
    //getZonesFromChapterByChapterNumberMenu(int number)
    void enterToZoneMenuTest() throws ZoneNotFoundException {
        System.out.println("Start of enter to zone menu TEST");
        scanner = new Scanner("1 2 3 0 0 0 0 0");
        //consoleUIManager = new ConsoleUIManager(chapterManager, scanner);
        consoleUIManager.setScanner(scanner);
        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();
        System.out.println();
        System.out.println();
        System.out.println("End of enter to zone menu TEST");
    }

    @Test
    //getStatesFromZoneByZoneNumberMenu(int number)
    void enterToZoneStateMenuTest() throws ZoneNotFoundException {
        System.out.println("Start of enter to zone state menu TEST");
        scanner = new Scanner("1 2 3 3 0 0 0 0 0");
        //consoleUIManager = new ConsoleUIManager(chapterManager, scanner);
        consoleUIManager.setScanner(scanner);
        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();
        System.out.println();
        System.out.println();
        System.out.println("End of enter to zone test menu TEST");
    }

    @Test
    void changeZoneStateTest() throws ZoneNotFoundException {
        System.out.println("Start of change zone state TEST");
        scanner = new Scanner("1 2 3 3 2 0 0 0 0 0 0 0");
        //consoleUIManager = new ConsoleUIManager(chapterManager, scanner);
        consoleUIManager.setScanner(scanner);
        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();
        System.out.println();
        System.out.println();
        System.out.println("End of change zone state TEST");
    }
}