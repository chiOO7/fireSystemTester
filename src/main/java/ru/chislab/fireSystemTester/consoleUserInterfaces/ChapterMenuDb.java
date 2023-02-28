package ru.chislab.fireSystemTester.consoleUserInterfaces;

import ru.chislab.fireSystemTester.chapters.Chapter;
import ru.chislab.fireSystemTester.chapters.ChapterManager;

public class ChapterMenuDb extends ChapterMenu {
    public ChapterMenuDb(String menuName, int chapterNumber, ChapterManager chapterManager) {
        super(menuName, chapterNumber, chapterManager);
    }

    @Override
    public void processMenu() {
        while (true) {
            getChapterManager().getZoneManager().updateZonesState(getChapterManager()
                    .getChapterByNumber(getChapterNumber()).getZones());
//            setSubMenus(getConsoleUIManager().getZonesFromDbByChapterNumberMenu(getChapterNumber()));
            setSubMenus(getConsoleUIManager().getZonesFromChapterByChapterNumberMenu(getChapterNumber()));
            int command = checkCommand();
            if (command == 0) break;
            if (command == 1) {
                System.out.println("Введите новое имя раздела:");
                String newName = getScanner().nextLine();
                Chapter chapter = getChapterManager().getChapterByNumber(getChapterNumber());
                chapter.setChapterName(newName);
                getChapterManager().updateChapter(chapter);
                break;
            }
            if (command == 2) {
                getChapterManager().getZoneManager().updateZonesState(getChapterManager()
                        .getChapterByNumber(getChapterNumber()).getZones());
            }
            processCommand(command);
        }
    }
}
