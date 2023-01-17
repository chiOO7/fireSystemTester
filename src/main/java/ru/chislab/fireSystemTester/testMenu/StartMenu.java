package ru.chislab.fireSystemTester.testMenu;

import lombok.AllArgsConstructor;
import lombok.Setter;
import ru.chislab.fireSystemTester.chapters.Chapter;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.consoleUserInterfaces.AvailableChaptersMenu;
import ru.chislab.fireSystemTester.consoleUserInterfaces.ChapterMenu;
import ru.chislab.fireSystemTester.consoleUserInterfaces.ConsoleUIMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Setter
public class StartMenu extends ConsoleUIMenu {

    private ChapterManager chapterManager;

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
        List<ConsoleUIMenu> chapterMenus = new ArrayList<>();
        for (int i = 0; i < chapterManager.getChapters().length; i++) {
            ChapterMenu chapterMenu = new ChapterMenu(i + 1, scanner, Collections.emptyList());
        }
//        AvailableChaptersMenu availableChaptersMenu = new AvailableChaptersMenu("Доступные разделы",
//                scanner, Collections.emptyList());
        super(menuName, scanner, chapterMenus);
    }
}
