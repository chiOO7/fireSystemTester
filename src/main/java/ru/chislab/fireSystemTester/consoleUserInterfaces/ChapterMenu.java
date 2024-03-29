package ru.chislab.fireSystemTester.consoleUserInterfaces;


import lombok.Getter;
import ru.chislab.fireSystemTester.chapters.Chapter;
import ru.chislab.fireSystemTester.chapters.ChapterManager;

@Getter
public class ChapterMenu extends ConsoleUIMenu {

    private final ChapterManager chapterManager;
    private final int chapterNumber;
    private final String chapterName;

    public ChapterMenu(String menuName, int chapterNumber, ChapterManager chapterManager) {
        super(menuName);
        this.chapterNumber = chapterNumber;
        this.chapterManager = chapterManager;
        this.chapterName = chapterManager.getChapterByNumber(chapterNumber).getChapterName();
    }

    @Override
    protected void printMenuHeader() {
        System.out.println("# " + getMenuName() + ": " + chapterName);
        System.out.println("0. Назад");
        System.out.println("1. Задать имя раздела");
        System.out.println("2. Обновить состояние зон");
    }

    @Override
    public void processCommand(int command) {
        if (!getSubMenus().isEmpty() && command > 2) {
            getSubMenus().get(command - 3).processMenu();
        }
    }

    @Override
    public void printSubMenus() {
        printMenuHeader();
        for (int i = 0; i < getSubMenus().size(); i++) {
            ZoneMenu zoneMenu = (ZoneMenu) getSubMenus().get(i);
            System.out.println((i + 3) + ". " + zoneMenu.getMenuName() + ": " + zoneMenu.getZoneName());
        }
        printMenuFooter();
    }

    @Override
    public void processMenu() {
        while (true) {
            getChapterManager().getZoneManager().updateZonesState(getChapterManager()
                    .getChapterByNumber(chapterNumber).getZones());
            setSubMenus(getConsoleUIManager().getZonesFromChapterByChapterNumberMenu(chapterNumber));
            int command = checkCommand();
            if (command == 0) break;
            if (command == 1) {
                System.out.println("Введите новое имя раздела:");
                String newName = getScanner().nextLine();
                Chapter chapter = getChapterManager().getChapterByNumber(chapterNumber);
                chapter.setChapterName(newName);
                break;
            }
            if (command == 2) {
                getChapterManager().getZoneManager().updateZonesState(getChapterManager()
                        .getChapterByNumber(chapterNumber).getZones());
            }
            processCommand(command);
        }
    }
}
