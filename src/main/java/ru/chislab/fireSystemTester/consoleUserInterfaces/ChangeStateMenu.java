package ru.chislab.fireSystemTester.consoleUserInterfaces;

import ru.chislab.fireSystemTester.enums.States;
import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneState;

import java.util.List;

public class ChangeStateMenu extends ConsoleUIMenu{

    private Zone zone;

    private int stateNumber;
    public ChangeStateMenu(String menuName, Zone zone, int stateNumber) {
        super(menuName);
        this.zone = zone;
        this.stateNumber = stateNumber;
    }

    @Override
    public void processMenu() {
//        ZoneState zoneState = zone.getZoneState();
//        List<States> states = zone.getZoneState().getStates();
//        states.get(stateNumber) =
//        zoneState
//        zone.setZoneState(zone.getZoneState().getStates().get(stateNumber));
        System.out.println("CHANGE STATE!!!");
    }
}
