package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.Getter;
import ru.chislab.fireSystemTester.zones.ZoneState;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Getter
public class ZoneMenu extends ConsoleUIMenu{


    public ZoneMenu(String menuName, Scanner scanner, List<ConsoleUIMenu> subMenus) {
        super(menuName, scanner, subMenus);
    }
}
