package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.*;
import ru.chislab.fireSystemTester.enums.States;
import ru.chislab.fireSystemTester.zones.Zone;

import java.util.Collections;
import java.util.Scanner;

@Data
public class StateMenu extends ConsoleUIMenu{
    private Zone zone;
    public StateMenu(String menuName, Zone zone) {
        super(menuName);
        this.zone = zone;
    }

    @Override
    public void processMenu() {
            printMenus();
            int command = getScanner().nextInt();
            if (command == -1) System.exit(0);
            if (command == 0) return;
            processCommand(command);
    }
}
