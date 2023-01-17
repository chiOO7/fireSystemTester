package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.Getter;

import java.util.List;
import java.util.Scanner;

@Getter
public class ZoneMenu extends ConsoleUIMenu{

//    private final List<StateMenu> stateMenus;

    public ZoneMenu(String menuName, Scanner scanner, List<ConsoleUIMenu> stateMenus) {
        super(menuName, scanner, stateMenus);
    }
}
