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

//    Scanner scanner = new Scanner(System.in);

    int level = -1;
    List<TestAbstractMenu> menus;

    String name;

    public abstract void doSomething(int command);



    @Override
    public String toString() {
        return name ;
    }

    public void printMenus() {
        System.out.println();
        System.out.println(name);
        System.out.println("0. Назад");
        for (int i = 0; i < menus.size(); i++) {
            System.out.println((i + 1) + ". " + menus.get(i));
        }
        System.out.print("Enter command number: ");
    }

//    public int somthingWithMenu(TestAbstractMenu menu) {
//        menu.printMenus();
//        int command = scanner.nextInt();
//        if (command == 0) return menu.getLevel() - 1;
//        menu.doSomething(command);
//        if (menu.getMenus().isEmpty()) return 0;
//        int level = somthingWithMenu(menu.getMenus().get(command - 1));
//        return level;
//    }
}
