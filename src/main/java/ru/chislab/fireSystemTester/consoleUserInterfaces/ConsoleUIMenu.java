package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.*;
import ru.chislab.fireSystemTester.chapters.ChapterManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ConsoleUIMenu {
    private String menuName;

    private int menuRowNumber;

    private Scanner scanner;

    private ChapterManager chapterManager;

    private List<ConsoleUIMenu> subMenus = new ArrayList<>();

    public ConsoleUIMenu(String menuName) {
        this.menuName = menuName;
    }

    public void processMenu() {
        while (true) {
            printMenus();
            int command = scanner.nextInt();
            if (command == -1) System.exit(0);
            if (command == 0) break;
            doSomething(command);
        }
    }

    protected void printMenuHeader() {
        System.out.println();
        System.out.println("# " + menuName + ":");
        System.out.println("0. Назад");
    }

    protected void printMenuFooter() {
        System.out.print("Введите номер команды: ");
    }

    public void printMenus() {
        printMenuHeader();
        if (!subMenus.isEmpty()) {
            for (int i = 0; i < subMenus.size(); i++) {
                System.out.println((i + 1) + ". " + subMenus.get(i).getMenuName());
            }
        }
        printMenuFooter();
    }

    public void doSomething(int command) {
        if (!subMenus.isEmpty()) {
            subMenus.get(command - 1).processMenu();
        }
    }
}
