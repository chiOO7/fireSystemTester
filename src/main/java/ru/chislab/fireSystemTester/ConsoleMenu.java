package ru.chislab.fireSystemTester;

import java.util.List;

public class ConsoleMenu {
    private MainMenuCommands command;
    private int level;
    private List<ConsoleMenu> subMenus;

    enum MainMenuCommands {
        DEFINE_ZONE_CONFIGURATIONS("1. Считать конфигурацию зон"),
        LOAD_ZONE_CONFIGURATION("2. Загрузить конфигурацию зон");

        String description;

        String getDescription() {
            return this.description;
        }
        MainMenuCommands(String description) {
            this.description = description;
        }
    }
}
