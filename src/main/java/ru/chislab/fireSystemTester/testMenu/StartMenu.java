package ru.chislab.fireSystemTester.testMenu;

import ru.chislab.fireSystemTester.consoleUserInterfaces.AvailableChaptersMenu;
import ru.chislab.fireSystemTester.consoleUserInterfaces.ConsoleUIMenu;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class StartMenu extends ConsoleUIMenu {


    @Override
    protected void printMenuHeader() {
        System.out.println("# Главное меню:");
        System.out.println("0. Выход из программы");
    }

    @Override
    public void doSomething(int command) {
        switch (command) {
            case 1: break;
            case 2:
                getSubMenus().get(command - 2).processMenu();
                break;
        }
    }

    @Override
    public void printMenus() {
        printMenuHeader();
        System.out.println("1. Конфигурация зон");
        System.out.println("2. Доступные разделы");
        printMenuFooter();
    }

    public StartMenu(String menuName, Scanner scanner) {
        super(menuName, scanner, Collections.emptyList());
    }
}
