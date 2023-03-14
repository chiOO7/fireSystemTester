package ru.chislab.fireSystemTester.chapters;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.chislab.fireSystemTester.modbus.ModbusDataSource;
import ru.chislab.fireSystemTester.modbus.ModbusDataSourceForTests;
import ru.chislab.fireSystemTester.enums.ZoneTypes;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChapterManagerTest {

    private static ChapterManager chapterManager;
    private static ZoneManager zoneManager;
    private static ModbusDataSource modbusDataSource;

    @BeforeEach
    void setUp() {
        modbusDataSource = new ModbusDataSourceForTests();
        zoneManager = new ZoneManager(modbusDataSource, null);
        chapterManager = new ChapterManager(zoneManager, null);
    }

    @AfterEach
    void tearDown() {
        modbusDataSource = null;
        zoneManager = null;
        chapterManager = null;
    }

    @Test
    void initChaptersFromDevice() {
        chapterManager.initChaptersFromDevice();
        List<Chapter> chapters = chapterManager.getAvailableChapters();
        assertEquals(2, chapters.size());
        for (int i = 0; i < 2; i++) {
            assertEquals(i + 1, chapterManager.getZoneManager().getZones().get(i).getModbusZoneNumber());
            assertEquals(i + 1, chapterManager.getZoneManager().getZones().get(i).getSignalLineNumber());
            assertEquals(ZoneTypes.SIGNAL_LINE_STATE, chapterManager.getZoneManager().getZones().get(i).getZoneType());
        }
    }

    @Test
    void initChaptersFromStorage() {
//        zoneManager.readZoneConfigsFromDevice();
//        zoneManager.getZoneDao().saveZonesToStorage(zoneManager.getZones());
//        chapterManager.saveChaptersToStorage();
//        chapterManager.initChaptersFromStorage();
//        List<Chapter> chapters = chapterManager.getAvailableChapters();
//        assertEquals(2, chapters.size());
//        for (int i = 0; i < 2; i++) {
//            assertEquals(i + 1, chapterManager.getZoneManager().getZones().get(i).getModbusZoneNumber());
//            assertEquals(i + 1, chapterManager.getZoneManager().getZones().get(i).getSignalLineNumber());
//            assertEquals(ZoneTypes.SIGNAL_LINE_STATE, chapterManager.getZoneManager().getZones().get(i).getZoneType());
//        }
    }

    @Test
    void getChapterByNumber() {
        chapterManager.initChaptersFromDevice();
        Chapter chapter = chapterManager.getChapterByNumber(1);
        assertEquals(1, chapter.getModbusChapterNumber());
        assertEquals(5, chapter.getZones().size());
        assertEquals(2, chapter.getDeviceAddress());
        for (int i = 0; i < 2; i++) {
            assertEquals(i + 1, chapter.getZones().get(i).getModbusZoneNumber());
            assertEquals(i + 1, chapter.getZones().get(i).getSignalLineNumber());
            assertEquals(ZoneTypes.SIGNAL_LINE_STATE, chapter.getZones().get(i).getZoneType());
        }
    }

    @Test
    void getAvailableChapters() {
        chapterManager.initChaptersFromDevice();
//        chapterManager.getAvailableChapters();
        assertEquals(2, chapterManager.getAvailableChapters().size());
        for (int i = 0; i < 2; i++) {
            assertEquals(i + 1, chapterManager.getZoneManager().getZones().get(i).getModbusZoneNumber());
            assertEquals(i + 1, chapterManager.getZoneManager().getZones().get(i).getSignalLineNumber());
            assertEquals(ZoneTypes.SIGNAL_LINE_STATE, chapterManager.getZoneManager().getZones().get(i).getZoneType());
        }
    }

    @Test
    void saveChaptersToStorage() {
    }

    @Test
    void updateChapter() {
    }

    @Test
    void reInitChapters() {
        chapterManager.reInitChapters();
        for (Chapter chapter: chapterManager.getChapters()) {
            assertEquals("Имя раздела не установлено", chapter.getChapterName());
            assertNull(chapter.getDeviceAddress());
            assertNull(chapter.getDeviceType());
        }
    }

    @Test
    void getAvailableChaptersFromDb() {
    }

    @Test
    void addNewZoneToChapter() {

    }
}