package ru.chislab.fireSystemTester.zones;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.chislab.fireSystemTester.enums.States;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;
import ru.chislab.fireSystemTester.modbus.ModbusDataSource;
import ru.chislab.fireSystemTester.modbus.ModbusDataSourceForTests;
import ru.chislab.fireSystemTester.enums.ZoneTypes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ZoneManagerTest {

    private static ZoneManager zoneManager;

    private static ModbusDataSource modbusDataSource;


    @BeforeEach
    void setUp() {
        modbusDataSource = new ModbusDataSourceForTests();
        zoneManager = new ZoneManager(modbusDataSource, null);
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
//        zoneManager.readZoneConfigsFromDevice();
//        zoneManager.saveZonesToStorage();
//        List<Zone> zonesFromStorage = zoneManager.getZoneDao().getZonesFromStorage();
//        assertEquals(10, zonesFromStorage.size());
//        for (int i = 0; i < 10; i++) {
//            assertEquals(i + 1, zonesFromStorage.get(i).getModbusZoneNumber());
//            assertEquals(i + 1, zonesFromStorage.get(i).getSignalLineNumber());
//            assertEquals(ZoneTypes.SIGNAL_LINE_STATE, zonesFromStorage.get(i).getZoneType());
//        }
    }

    @Test
    void getZonesFromStorage() {
//        zoneManager.readZoneConfigsFromDevice();
//        zoneManager.saveZonesToStorage();
//        zoneManager.clearZones();
//        zoneManager.getZonesFromStorage();
//        List<Zone> zones = zoneManager.getZones();
//        assertEquals(10, zones.size());
//        for (int i = 0; i < 10; i++) {
//            assertEquals(i + 1, zones.get(i).getModbusZoneNumber());
//            assertEquals(i + 1, zones.get(i).getSignalLineNumber());
//            assertEquals(ZoneTypes.SIGNAL_LINE_STATE, zones.get(i).getZoneType());
//        }
    }

    @Test
    void updateZonesState() {
    }

    @Test
    void updateZoneStateByZoneNumber() {
    }

    @Test
    void getZoneByZoneNumber() {
        zoneManager.readZoneConfigsFromDevice();
        try {
            assertEquals(3, zoneManager.getZoneByZoneNumber(3).getModbusZoneNumber());
        } catch (ZoneNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void setZoneStateByZoneNumber() {
        zoneManager.readZoneConfigsFromDevice();
        zoneManager.setZoneStateByZoneNumber(1, List.of(States.FIRE_2,States.HINDRANCE));
        zoneManager.updateZoneStateByZoneNumber(1);
        try {
            assertSame(States.FIRE_2, zoneManager.getZoneByZoneNumber(1).getZoneState().getStates().get(0));
            assertSame(States.HINDRANCE, zoneManager.getZoneByZoneNumber(1).getZoneState().getStates().get(1));
        } catch (ZoneNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void clearZones() {
        zoneManager.readZoneConfigsFromDevice();
        zoneManager.clearZones();
        assertTrue(zoneManager.getZones().isEmpty());
    }

    @Test
    void updateZone() {
    }
}