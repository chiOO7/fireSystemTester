package ru.chislab.fireSystemTester.consoleUserInterfaces;


public class StartMenu extends ConsoleUIMenu {
    public StartMenu(String menuName) {
        super(menuName);
    }

    @Override
    protected void printMenuHeader() {
        System.out.println("# " + getMenuName() + ":");
        System.out.println("0. Выход из программы");
    }
}
