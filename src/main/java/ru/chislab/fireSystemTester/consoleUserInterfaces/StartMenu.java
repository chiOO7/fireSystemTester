package ru.chislab.fireSystemTester.consoleUserInterfaces;

import ru.chislab.fireSystemTester.chapters.ChapterManager;

public class StartMenu extends ConsoleUIMenu {

    private final ChapterManager chapterManager;

    public StartMenu(String menuName, ChapterManager chapterManager) {
        super(menuName);
        this.chapterManager = chapterManager;
    }

    @Override
    public void processMenu() {
        while (true) {
            int command = checkCommand();
            if (command == 0) {
                getConsoleUIManager().getContext().close();
                System.exit(0);
            }
            if (command == 1) chapterManager.initChaptersFromDevice();
            processCommand(command);
        }
    }

    @Override
    protected void printMenuHeader() {
        System.out.println("# " + getMenuName() + ":");
        System.out.println("0. Выход из программы");
    }
}
