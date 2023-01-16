package ru.chislab.fireSystemTester.testMenu;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestMenu extends TestAbstractMenu {




    public TestMenu(List<TestAbstractMenu> menus, String name) {
        super(0, menus, name);
    }



    public void printMenus() {
        System.out.println();
        System.out.println(name);
        System.out.println("0. Назад");
        System.out.println("1. Обновить состояние зон");
        for (int i = 0; i < menus.size(); i++) {
            System.out.println((i + 2) + ". " + menus.get(i));
        }
        System.out.print("Enter command number: ");
    }

    @Override
    public void doSomething(int command) {
        switch (command) {
            case 1:
                System.out.println(name + " first");
                break;
        }
    }
}
