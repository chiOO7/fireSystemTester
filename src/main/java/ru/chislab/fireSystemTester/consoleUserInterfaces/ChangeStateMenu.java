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
        List<States> states = zone.getZoneState().getStates();
        states.set(stateNumber, States.valueOf(getMenuName()));
        getChapterManager().getZoneManager().setZoneStateByZoneNumber(zone.getModbusZoneNumber(), states);
    }
}
