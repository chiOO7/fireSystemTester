package ru.chislab.fireSystemTester.consoleUserInterfaces;


//import ru.chislab.fireSystemTester.zones.Zone;

public class StateMenu extends ConsoleUIMenu{

//    private final Zone zone;
    public StateMenu(String menuName
//            , Zone zone
    ) {
        super(menuName);
//        this.zone = zone;
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
