package ru.chislab.fireSystemTester.testMenu;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestSubMenu extends TestAbstractMenu {

    @Override
    public void doSomething(int command) {

    }

    @Override
    public String toString() {
        return name + ": " + getMenus().get(0).toString() + "; " + getMenus().get(1).toString() ;
    }

    public TestSubMenu(List<TestAbstractMenu> menus, String name) {
        super(1, menus, name);
    }
}
