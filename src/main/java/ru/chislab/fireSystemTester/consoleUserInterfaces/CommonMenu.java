package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class CommonMenu extends ConsoleUIMenu{
    public CommonMenu(String menuName) {
        super(menuName);
    }

    public CommonMenu(String menuName, List<ConsoleUIMenu> subMenus) {
        super(menuName);
        super.getSubMenus().addAll(subMenus);
    }
}
