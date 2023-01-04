package ru.chislab.fireSystemTester.zones;

import ru.chislab.fireSystemTester.ModbusDataSource;
import ru.chislab.fireSystemTester.OrionEvent;

import java.util.ArrayList;
import java.util.List;

public class ZoneManager {

    private List<Zone> zones;
    private ModbusDataSource modbusDataSource;

    public ZoneManager(ModbusDataSource modbusDataSource) {
        this.modbusDataSource = modbusDataSource;
    }

    public void updateZonesState() {
        List<ZoneState> states = modbusDataSource.getZonesStates();
        for (int i = 0; i < zones.size(); i++) {
            zones.get(i).setState(states.get(i));
        }
    }

    public void updateZonesStateByOrionEvents() {
        List<OrionEvent> orionEvents = new ArrayList<>();
        int eventsCount = modbusDataSource.getCountOfUnreadOrionEvent();
        int eldestEventNumber = modbusDataSource.getNumberOfEldestOrionEvent();
        for (int i = 0; i < eventsCount; i++) {
            OrionEvent event = modbusDataSource.getOrionEventByNumber(eldestEventNumber + i);
            orionEvents.add(event);
        }
        for (OrionEvent event : orionEvents) {
            Zone eventZone = event.getZone();
            for (Zone zone : zones) {
                if (eventZone.equals(zone)) {
                    ZoneState newState = new ZoneState();
                    newState.getState().add(event.getEvent());
                    zone.setState(newState);
                }
            }
        }
    }

    public void initZones() {
        List<ZoneConfiguration> configurations = modbusDataSource.getZonesConfigurations();
        List<ZoneState> states = modbusDataSource.getZonesStates();

        List<Zone> zones = new ArrayList<>();

        for (int i = 0; i < configurations.size(); i++) {
            Zone zone = new Zone(configurations.get(i));
            zone.setState(states.get(i));
            zones.add(zone);
        }

        this.zones = zones;
    }

}
