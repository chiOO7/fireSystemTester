package ru.chislab.fireSystemTester.consoleUserInterfaces;

import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;

public class StartMenu extends ConsoleUIMenu {

    private final ChapterManager chapterManager;

    public StartMenu(String menuName, ChapterManager chapterManager) {
        super(menuName);
        this.chapterManager = chapterManager;
    }

    @Override
    public void processMenu() throws ZoneNotFoundException {
        while (true) {
            printSubMenus();
            int command = getScanner().nextInt();
            System.out.println();
            if (command == -1) System.exit(0);
            if (command == 0) break;
            if (command == 1) {
                chapterManager.initChaptersFromDevice();
            }
            processCommand(command);
        }
    }

    @Override
    protected void printMenuHeader() {
        System.out.println("# " + getMenuName() + ":");
        System.out.println("0. Выход из программы");
    }
}
