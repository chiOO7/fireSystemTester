package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.*;

import java.util.List;


//@NoArgsConstructor
public class StartMenu extends ConsoleUIMenu {
    public StartMenu(String menuName) {
        super(menuName);
    }

//    public StartMenu(String menuName, List<ConsoleUIMenu> subMenus) {
//        super(menuName);
//        super.getSubMenus().addAll(subMenus);
//    }

    @Override
    protected void printMenuHeader() {
        System.out.println();
        System.out.println("# " + getMenuName() + ":");
        System.out.println("0. Выход из программы");
    }
}
