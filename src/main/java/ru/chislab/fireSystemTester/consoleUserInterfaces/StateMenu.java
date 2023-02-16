package ru.chislab.fireSystemTester.consoleUserInterfaces;

public class StateMenu extends ConsoleUIMenu{
    public StateMenu(String menuName) {
        super(menuName);
    }

    @Override
    public void processMenu() {
            printSubMenus();
            int command = getScanner().nextInt();
            System.out.println();
            if (command == -1) System.exit(0);
            if (command == 0) return;
            processCommand(command);
    }
}
