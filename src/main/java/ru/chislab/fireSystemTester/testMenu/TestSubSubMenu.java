package ru.chislab.fireSystemTester.testMenu;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestSubSubMenu extends TestAbstractMenu {

    @Override
    public void doSomething(int command) {
        switch (command) {
            case 1:
                System.out.println(name + " first");
                break;
            case 2:
                System.out.println(name + " second");
                break;
            case 3:
                System.out.println(name + " third");
                break;
        }
    }
    public TestSubSubMenu(List<TestAbstractMenu> menus, String name) {
        super(2, menus, name);
    }


    List<String> punkts;

    @Override
    public void printMenus() {
        System.out.println();
        System.out.println(name);
        System.out.println("0. Back");
        for (int i = 0; i < punkts.size(); i++) {
            System.out.println((i + 1) + ". " + punkts.get(i));
        }
        System.out.print("Enter command number: ");
    }

}
