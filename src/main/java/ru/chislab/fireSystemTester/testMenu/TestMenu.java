package ru.chislab.fireSystemTester.testMenu;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class TestMenu extends TestAbstractMenu {


    public TestMenu(Scanner scanner, List<TestAbstractMenu> menus, String name) {
        super(scanner, 0, menus, name);
    }


    public void printMenus() {
        System.out.println();
        System.out.println(name);
        System.out.println("0. Назад");
        System.out.println("1. Обновить состояние зон");
        for (int i = 0; i < menuPunkts.size(); i++) {
            System.out.println((i + 2) + ". " + menuPunkts.get(i));
        }
        System.out.print("Enter command number: ");
    }

    @Override
    public void doSomething(int command) {
        if (command == 1) {
            System.out.println(name + " first");
        } else {
            menuPunkts.get(command - 2).processMenu();
        }
    }
}
