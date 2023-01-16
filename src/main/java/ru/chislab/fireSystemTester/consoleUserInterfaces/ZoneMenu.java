package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.Getter;
import ru.chislab.fireSystemTester.zones.ZoneState;

import java.util.List;
import java.util.Objects;

@Getter
public class ZoneMenu extends ConsoleUIMenu{

    private int zoneMenuNumber;

    private List<StateMenu> stateMenus;
    public ZoneMenu(int number, List<StateMenu> stateMenus) {
        super("Зона " + number);
        this.zoneMenuNumber = number + 1;
        this.stateMenus = stateMenus;
    }

    @Override
    public String toString() {
        return ". " + super.getConsoleView() + ": " + getStateMenus().get(0).getState() + "; " +
                getStateMenus().get(1).getState();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZoneMenu zoneMenu = (ZoneMenu) o;
        return zoneMenuNumber == zoneMenu.zoneMenuNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(zoneMenuNumber);
    }
}
