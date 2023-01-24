package ru.chislab.fireSystemTester.consoleUserInterfaces;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.chislab.fireSystemTester.ModbusDataSource;
import ru.chislab.fireSystemTester.ModbusDataSourceForTests;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.enums.States;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleUIManagerTest {
    private ConsoleUIManager consoleUIManager;
    private ChapterManager chapterManager;
    private Scanner scanner;

    @BeforeEach
    void setUp() {
        ModbusDataSource modbusDataSource = new ModbusDataSourceForTests();
        ZoneManager zoneManager = new ZoneManager(modbusDataSource);
        chapterManager = new ChapterManager(zoneManager);
//        chapterManager.initChaptersFromDevice();
//        chapterManager.getZoneManager().updateZonesState();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getStateMenusByZoneNumber() throws ZoneNotFoundException {
        List<ConsoleUIMenu> consoleUIMenus = consoleUIManager.getStateMenusByZoneNumber(1);
        StateMenu stateMenu1 = (StateMenu) consoleUIMenus.get(0);
        StateMenu stateMenu2 = (StateMenu) consoleUIMenus.get(1);
        assertEquals(1, stateMenu1.getMenuRowNumber());
        assertEquals(2, stateMenu2.getMenuRowNumber());
        assertEquals(States.FIRE, stateMenu1.getState());
        assertEquals(States.BAD_START, stateMenu2.getState());
        System.out.println();
    }

    @Test
    void getZoneMenusByChapterNumber() {
    }

    @Test
    void getChapterMenus() {
    }

    @Test
    void getStartMenu() {
        scanner = new Scanner("1 1 0 0");
        consoleUIManager = new ConsoleUIManager(chapterManager, scanner);
        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();
    }



    @Test
    void testGetStateMenusByZoneNumber() {
    }

    @Test
    void testGetZoneMenusByChapterNumber() {
    }

    @Test
    void testGetChapterMenus() {
    }

    @Test
    void testGetStartMenu() {
    }
}