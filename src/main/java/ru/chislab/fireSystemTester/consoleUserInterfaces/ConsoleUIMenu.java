package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Scanner;

@Setter
@Getter
@AllArgsConstructor
public abstract class ConsoleUIMenu {
    private final String menuName;
    private final Scanner scanner;
    private final List<ConsoleUIMenu> subMenus;
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
        for (int i = 0; i < subMenus.size(); i++) {
            System.out.println((i + 1) + ". " + subMenus.get(i));
        }
        printMenuFooter();
    }
    public void doSomething(int command) {
        subMenus.get(command - 1).processMenu();
    }
}
