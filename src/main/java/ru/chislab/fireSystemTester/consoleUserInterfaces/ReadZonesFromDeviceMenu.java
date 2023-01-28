package ru.chislab.fireSystemTester.consoleUserInterfaces;


public class ReadZonesFromDeviceMenu extends ConsoleUIMenu{
    public ReadZonesFromDeviceMenu(String menuName) {
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
    public void processMenu() {
        while (true) {
            getChapterManager().initChaptersFromDevice();
            setSubMenus(getConsoleUIManager().getChaptersFromDeviceMenu());
            printSubMenus();
            int command = getScanner().nextInt();
            if (command == -1) System.exit(0);
            if (command == 0) break;
            processCommand(command);
        }
    }
}
