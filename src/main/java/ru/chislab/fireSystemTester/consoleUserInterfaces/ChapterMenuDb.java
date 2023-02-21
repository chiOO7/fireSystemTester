package ru.chislab.fireSystemTester.consoleUserInterfaces;

import ru.chislab.fireSystemTester.chapters.Chapter;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;

public class ChapterMenuDb extends ChapterMenu{
    public ChapterMenuDb(String menuName, int chapterNumber, ChapterManager chapterManager) {
        super(menuName, chapterNumber, chapterManager);
    }

    @Override
    public void processMenu() throws ZoneNotFoundException {
        while (true) {
            getChapterManager().getZoneManager().updateZonesState(getChapterManager()
                    .getChapterByNumber(getChapterNumber()).getZones());
            setSubMenus(getConsoleUIManager().getZonesFromDbByChapterNumberMenu(getChapterNumber()));
            printSubMenus();
            int command = getScanner().nextInt();
            System.out.println();
            if (command == -1) System.exit(0);
            if (command == 0) break;
            if (command == 1) {
                System.out.println("Введите новое имя раздела:");
                String newName = getScanner().next();
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
