package ru.chislab.fireSystemTester.consoleUserInterfaces;

public class StateMenu extends ConsoleUIMenu {

    public StateMenu(String menuName) {
        super(menuName);
    }

    @Override
    public void processMenu() {
        int command = checkCommand();
        if (command == 0) return;
        processCommand(command);
    }
}
