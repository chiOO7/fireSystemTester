package ru.chislab.fireSystemTester.consoleUserInterfaces;

import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;

public class AvailableFromStorageChaptersMenu extends ConsoleUIMenu {
    public AvailableFromStorageChaptersMenu(String menuName) {
        super(menuName);
    }

    @Override
    public void printSubMenus() {
        printMenuHeader();
        if (!getSubMenus().isEmpty()) {
            for (int i = 0; i < getSubMenus().size(); i++) {
                ChapterMenu chapterMenu = (ChapterMenu) getSubMenus().get(i);
                System.out.println((i + 1) + ". " + chapterMenu.getMenuName() + ": " + chapterMenu.getChapterName());
            }
        }
        printMenuFooter();
    }

    @Override
    public void processMenu() throws ZoneNotFoundException {
        while (true) {
            getChapterManager().initChaptersFromStorage();
            setSubMenus(getConsoleUIManager().getChaptersFromDeviceMenu());
            printSubMenus();
            int command = getScanner().nextInt();
            System.out.println();
            if (command == -1) System.exit(0);
            if (command == 0) break;
            processCommand(command);
        }
    }
}
