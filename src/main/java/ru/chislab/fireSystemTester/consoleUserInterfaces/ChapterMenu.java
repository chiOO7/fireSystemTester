package ru.chislab.fireSystemTester.consoleUserInterfaces;


import lombok.Data;


@Data
public class ChapterMenu extends ConsoleUIMenu{

    private int chapterNumber;
    private String chapterName = "Name of ch not set";

    public ChapterMenu(String menuName, int chapterNumber) {

        super(menuName);
        this.chapterNumber = chapterNumber;
    }

    @Override
    protected void printMenuHeader() {
        System.out.println();
        System.out.println("# " + getMenuName() + ": " + chapterName);
        System.out.println("0. Назад");
        System.out.println("1. Обновить состояние зон");
    }

    @Override
    public void processCommand(int command) {
        if (!getSubMenus().isEmpty()) {
            getSubMenus().get(command - 2).processMenu();
        }
    }

    @Override
    public void printSubMenus() {
        printMenuHeader();
        for (int i = 0; i < getSubMenus().size(); i++) {
            ZoneMenu zoneMenu = (ZoneMenu) getSubMenus().get(i);
            System.out.println((i + 2) + ". " + zoneMenu.getMenuName() + ": " + zoneMenu.getZoneName());
        }
        printMenuFooter();
    }

    @Override
    public void processMenu() {
        while (true) {
            getChapterManager().getZoneManager().updateZonesState(getChapterManager().getChapterByNumber(chapterNumber).getZones());
            setSubMenus(getConsoleUIManager().getZonesFromChapterByChapterNumberMenu(chapterNumber));
            printSubMenus();
            int command = getScanner().nextInt();
            if (command == -1) System.exit(0);
            if (command == 0) break;
            processCommand(command);
        }
    }
}
