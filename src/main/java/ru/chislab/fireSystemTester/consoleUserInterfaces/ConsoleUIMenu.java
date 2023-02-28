package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.*;
import ru.chislab.fireSystemTester.chapters.ChapterManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ConsoleUIMenu {
    private String menuName;
    private int menuRowNumber;
    private Scanner scanner;
    private ChapterManager chapterManager;
    private List<ConsoleUIMenu> subMenus = new ArrayList<>();
    private ConsoleUIManager consoleUIManager;

    public ConsoleUIMenu(String menuName) {
        this.menuName = menuName;
    }

    public int checkCommand() {
        printSubMenus();
        String commandStr;
        int command = -2;
        commandStr = getScanner().nextLine();
        if (commandStr.matches("^-?\\d$")) {
            command = Integer.parseInt(commandStr);
        }
        System.out.println();
        if (command == -1) {
            consoleUIManager.getContext().close();
            System.exit(0);
        }

        return command;
    }

    public void processMenu() {
        while (true) {
            printSubMenus();
            int command = checkCommand();
            if (command == 0) break;
            processCommand(command);
        }
    }

    protected void printMenuHeader() {
        System.out.println("# " + menuName + ":");
        System.out.println("0. Назад");
    }

    protected void printMenuFooter() {
        System.out.print("Введите номер команды: ");
    }

    public void printSubMenus() {
        printMenuHeader();
        if (!subMenus.isEmpty()) {
            for (int i = 0; i < subMenus.size(); i++) {
                System.out.println((i + 1) + ". " + subMenus.get(i).getMenuName());
            }
        }
        printMenuFooter();
    }

    public void processCommand(int command) {
        if (!subMenus.isEmpty() && subMenus.size() >= command - 1 && command > 0) {
            subMenus.get(command - 1).processMenu();
        } else System.out.println("Введите номер пункта меню");
    }

    public void addSubMenu(ConsoleUIMenu subMenu) {
        getSubMenus().add(subMenu);
    }

    public void addSubMenus(List<ConsoleUIMenu> subMenus) {
        getSubMenus().addAll(subMenus);
    }
}
