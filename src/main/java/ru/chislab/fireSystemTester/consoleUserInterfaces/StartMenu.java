package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.Setter;
import ru.chislab.fireSystemTester.chapters.ChapterManager;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Setter
public class StartMenu extends ConsoleUIMenu {

    public StartMenu(Scanner scanner, List<ConsoleUIMenu> menus) {
        super("Главное меню", scanner, menus);
    }

    @Override
    protected void printMenuHeader() {
        System.out.println("# Главное меню:");
        System.out.println("0. Выход из программы");
    }


}
