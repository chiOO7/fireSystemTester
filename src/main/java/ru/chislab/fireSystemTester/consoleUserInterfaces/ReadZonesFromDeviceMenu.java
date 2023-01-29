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
                System.out.println((i + 2) + ". " + chapterMenu.getMenuName() + ": " + chapterMenu.getChapterName());
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
            System.out.println();
            if (command == -1) System.exit(0);
            if (command == 0) break;
            if (command == 1) {
                getChapterManager().getZoneManager().saveZonesToStorage();
            }
            processCommand(command);
        }
    }

    @Override
    protected void printMenuHeader() {
        System.out.println("# Считанные из устройства разделы");
        System.out.println("0. Назад");
        System.out.println("1. Сохранить считанные разделы в базу данных");
    }

    @Override
    public void processCommand(int command) {
        if (!getSubMenus().isEmpty() && command > 1) {
            getSubMenus().get(command - 2).processMenu();
        }
    }
}
