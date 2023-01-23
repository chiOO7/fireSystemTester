package ru.chislab.fireSystemTester.zones;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.chislab.fireSystemTester.ModbusDataSource;
import ru.chislab.fireSystemTester.ModbusDataSourceForTests;
import ru.chislab.fireSystemTester.enums.ZoneTypes;

import static org.junit.jupiter.api.Assertions.*;

class ZoneManagerTest {

    private static ZoneManager zoneManager;

    private static ModbusDataSource modbusDataSource;

    @BeforeEach
    void setUp() {
        modbusDataSource = new ModbusDataSourceForTests();
        zoneManager = new ZoneManager(modbusDataSource);
    }

    @AfterEach
    void tearDown() {
        modbusDataSource = null;
        zoneManager = null;
    }

    @Test
    void readZoneConfigsFromDevice() {
        zoneManager.readZoneConfigsFromDevice();
        assertEquals(10, zoneManager.getZones().size());
        for (int i = 0; i < 10; i++) {
            assertEquals(i + 1, zoneManager.getZones().get(i).getModbusZoneNumber());
            assertEquals(i + 1, zoneManager.getZones().get(i).getSignalLineNumber());
            assertEquals(ZoneTypes.SIGNAL_LINE_STATE, zoneManager.getZones().get(i).getZoneType());
        }
    }

    @Test
    void saveZonesToStorage() {
    }

    @Test
    void getZonesFromStorage() {
    }

    @Test
    void getZones() {
    }

    @Test
    void updateZonesState() {
    }

    @Test
    void updateZoneStateByZoneNumber() {
    }

    @Test
    void getZoneByZoneNumber() {
    }

    @Test
    void getZonesByChapterNumber() {
    }

    @Test
    void setZoneStateByZoneNumber() {
    }

    @Test
    void getModbusDataSource() {
    }
}