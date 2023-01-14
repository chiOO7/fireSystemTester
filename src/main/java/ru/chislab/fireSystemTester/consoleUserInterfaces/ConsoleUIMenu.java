package ru.chislab.fireSystemTester.consoleUserInterfaces;

import java.util.List;

public abstract class ConsoleUIMenu {
    private List<Integer> menuLevel;
    private String consoleView;

    public ConsoleUIMenu(String consoleView) {
        this.consoleView = consoleView;
    }
}
