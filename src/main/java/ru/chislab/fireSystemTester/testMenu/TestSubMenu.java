package ru.chislab.fireSystemTester.testMenu;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class TestSubMenu extends TestAbstractMenu {

    @Override
    public String toString() {
        return name + ": " + getMenuPunkts().get(0).toString() + "; " + getMenuPunkts().get(1).toString();
    }


    public TestSubMenu(Scanner scanner, List<TestAbstractMenu> menus, String name) {
        super(scanner, 1, menus, name);
    }
}
