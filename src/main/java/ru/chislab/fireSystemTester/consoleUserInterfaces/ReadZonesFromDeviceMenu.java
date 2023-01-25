package ru.chislab.fireSystemTester.consoleUserInterfaces;

public class ReadZonesFromDeviceMenu extends ConsoleUIMenu{
    public ReadZonesFromDeviceMenu(String menuName) {
        super(menuName);
    }

//    @Override
//    protected void printMenuHeader() {
//        System.out.println();
//        System.out.println("# " + getMenuName());
//        System.out.println("0. Назад");
//        if (!getSubMenus().isEmpty()) {
//            System.out.println("1. Обновить состояние зон");
//        }
//    }

    @Override
    public void printMenus() {
        printMenuHeader();
        if (!getSubMenus().isEmpty()) {
            for (int i = 0; i < getSubMenus().size(); i++) {
                ChapterMenu chapterMenu = (ChapterMenu) getSubMenus().get(i);
                System.out.println((i + 1) + ". " + chapterMenu.getMenuName() + ": " + chapterMenu.getChapterName());
            }
        }
        printMenuFooter();
    }
}
