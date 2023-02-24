package ru.chislab.fireSystemTester.consoleUserInterfaces;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.chislab.fireSystemTester.dao.ChapterDao;
import ru.chislab.fireSystemTester.dao.ZoneDao;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;
import ru.chislab.fireSystemTester.modbus.ModbusDataSource;
import ru.chislab.fireSystemTester.modbus.ModbusDataSourceForTests;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.Scanner;

class ConsoleUIManagerTest {
    private ConsoleUIManager consoleUIManager;
    private Scanner scanner;
    private final ZoneDao zoneDao = null;
    private final ChapterDao chapterDao = null;

    @BeforeEach
    void setUp() {
        ModbusDataSource modbusDataSource = new ModbusDataSourceForTests();
        ZoneManager zoneManager = new ZoneManager(modbusDataSource, null);

        ChapterManager chapterManager = new ChapterManager(zoneManager, null);
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
        scanner = new Scanner("1\n0\n0");
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
    void enterToAvailableFromStorageChaptersMenuTest() {
//        System.out.println("Start of enter to available from storage chapters menu TEST");
//        scanner = new Scanner("2\n0\n0\n");
//        //consoleUIManager = new ConsoleUIManager(chapterManager, scanner);
//        consoleUIManager.setScanner(scanner);
//        StartMenu startMenu = consoleUIManager.getStartMenu();
//        startMenu.processMenu();
//        System.out.println();
//        System.out.println();
//        System.out.println("End of enter to available from storage chapters menu TEST");
    }

    @Test
    //getChaptersFromDeviceMenu()
    void enterToChapterMenuTest() throws ZoneNotFoundException {
        System.out.println("Start of enter to chapter menu TEST");
        scanner = new Scanner("1\n2\n0\n0\n0\n");
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
        scanner = new Scanner("1\n2\n3\n0\n0\n0\n0\n0\n");
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
        scanner = new Scanner("1\n2\n3\n3\n0\n0\n0\n0\n0\n");
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
        scanner = new Scanner("1\n2\n3\n3\n2\n0\n0\n0\n0\n0\n0\n0\n");
        //consoleUIManager = new ConsoleUIManager(chapterManager, scanner);
        consoleUIManager.setScanner(scanner);
        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();
        System.out.println();
        System.out.println();
        System.out.println("End of change zone state TEST");
    }
}