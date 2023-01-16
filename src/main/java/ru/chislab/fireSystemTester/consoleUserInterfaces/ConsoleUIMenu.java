package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class ConsoleUIMenu {
    private List<Integer> menuLevel;
    private String consoleView;

    public ConsoleUIMenu(String consoleView) {
        this.consoleView = consoleView;
    }
}
