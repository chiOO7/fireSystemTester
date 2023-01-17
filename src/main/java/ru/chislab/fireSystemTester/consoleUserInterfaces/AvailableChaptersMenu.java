package ru.chislab.fireSystemTester.consoleUserInterfaces;

import java.util.List;
import java.util.Scanner;

public class AvailableChaptersMenu extends ConsoleUIMenu {

    public AvailableChaptersMenu(Scanner scanner, List<ConsoleUIMenu> subMenus) {
        super("Доступные разделы", scanner, subMenus);
    }
}
