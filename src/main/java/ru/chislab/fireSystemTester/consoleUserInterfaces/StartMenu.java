package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.Setter;
import ru.chislab.fireSystemTester.chapters.ChapterManager;

import java.util.Collections;
import java.util.Scanner;

@Setter
public class StartMenu extends ConsoleUIMenu {

//    private ChapterManager chapterManager;

//    private AvailableChaptersMenu availableChaptersMenu;

    public StartMenu(Scanner scanner, AvailableChaptersMenu availableChaptersMenu) {
        super("Главное меню", scanner, Collections.singletonList(availableChaptersMenu));
    }

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
}
