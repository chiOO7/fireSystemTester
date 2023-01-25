package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.*;
import ru.chislab.fireSystemTester.zones.Zone;

@Data
public class StateMenu extends ConsoleUIMenu{

    private Zone zone;
    public StateMenu(String menuName, Zone zone) {
        super(menuName);
        this.zone = zone;
    }

    @Override
    public void processMenu() {
            printSubMenus();
            int command = getScanner().nextInt();
            if (command == -1) System.exit(0);
            if (command == 0) return;
            processCommand(command);
    }
}
