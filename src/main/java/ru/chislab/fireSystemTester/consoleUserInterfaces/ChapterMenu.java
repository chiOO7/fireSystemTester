package ru.chislab.fireSystemTester.consoleUserInterfaces;

import java.util.List;
import java.util.Objects;

public class ChapterMenu extends ConsoleUIMenu{

    private int number;

    private List<ZoneMenu> zoneMenus;

    public ChapterMenu(int number, List<ZoneMenu> zoneMenus) {
        super("Раздел " + number);
        this.number = number;
        this.zoneMenus = zoneMenus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChapterMenu that = (ChapterMenu) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
