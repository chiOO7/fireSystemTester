package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.Setter;
import ru.chislab.fireSystemTester.chapters.ChapterManager;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Setter
public class StartMenu extends ConsoleUIMenu {

//    private ChapterManager chapterManager;

//    private AvailableChaptersMenu availableChaptersMenu;

    public StartMenu(Scanner scanner, List<ConsoleUIMenu> menus) {
        super("Главное меню", scanner, menus);
    }

    @Override
    protected void printMenuHeader() {
        System.out.println("# Главное меню:");
        System.out.println("0. Выход из программы");
    }

//    @Override
//    public void doSomething(int command) {
//
//        getSubMenus().get(command - 1).processMenu();
//
//
//    }

//    @Override
//    public void printMenus() {
//        printMenuHeader();
//        System.out.println("1. " + getSubMenus().get(0).getMenuName());
//        System.out.println("2. " + getSubMenus().get(1).getMenuName());
//        printMenuFooter();
//    }
}
