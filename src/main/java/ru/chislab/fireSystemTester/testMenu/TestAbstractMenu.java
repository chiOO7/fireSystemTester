package ru.chislab.fireSystemTester.testMenu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Scanner;

@Setter
@Getter
@AllArgsConstructor
public abstract class TestAbstractMenu {

    Scanner scanner;

    int level = -1;
    List<TestAbstractMenu> menuPunkts;

    String name;

    public void doSomething(int command) {
        menuPunkts.get(command - 1).processMenu();
    };

    public void processMenu() {
        boolean flag = true;
        while (flag) {
            printMenus();
            int command = scanner.nextInt();
            if (command == 0) break;
            doSomething(command);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public void printMenus() {
        System.out.println();
        System.out.println(name);
        System.out.println("0. Назад");
        for (int i = 0; i < menuPunkts.size(); i++) {
            System.out.println((i + 1) + ". " + menuPunkts.get(i));
        }
        System.out.print("Enter command number: ");
    }
}
