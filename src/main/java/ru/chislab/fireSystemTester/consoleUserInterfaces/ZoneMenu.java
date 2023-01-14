package ru.chislab.fireSystemTester.consoleUserInterfaces;

import ru.chislab.fireSystemTester.zones.ZoneState;

import java.util.List;
import java.util.Objects;

public class ZoneMenu extends ConsoleUIMenu{

    private int number;

    private List<StateMenu> stateMenus;
    public ZoneMenu(int number, List<StateMenu> stateMenus) {
        super("Зона " + number);
        this.number = number;
        this.stateMenus = stateMenus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZoneMenu zoneMenu = (ZoneMenu) o;
        return number == zoneMenu.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
