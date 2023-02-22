package ru.chislab.fireSystemTester.consoleUserInterfaces;


import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;

public class StateMenu extends ConsoleUIMenu {

    public StateMenu(String menuName) {
        super(menuName);
    }

    @Override
    public void processMenu() throws ZoneNotFoundException {
        int command = checkCommand();
        if (command == 0) return;
        processCommand(command);
    }
}
