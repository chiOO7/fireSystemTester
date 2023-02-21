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
        getChapterManager().initChaptersFromStorage();
        while (true) {
            setSubMenus(getConsoleUIManager().getChaptersFromDbMenu());
            printSubMenus();
            int command = checkCommand();
            if (command == 0) break;
            processCommand(command);
        }
    }
}
