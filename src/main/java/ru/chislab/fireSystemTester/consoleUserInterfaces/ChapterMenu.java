package ru.chislab.fireSystemTester.consoleUserInterfaces;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ChapterMenu extends ConsoleUIMenu{
    private int number;

//    private List<ZoneMenu> zoneMenus;

    public ChapterMenu(int number, Scanner scanner, List<ConsoleUIMenu> zoneMenus) {
        super("Раздел " + number, scanner, zoneMenus);
        this.number = number;
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
