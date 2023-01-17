package ru.chislab.fireSystemTester;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

public class ConsoleUserInterface {
    Writer consoleOut = new OutputStreamWriter(System.out);
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_COMMAND_NUMBER = "Введите номер команды и нажмите ENTER";

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

    public void refreshDisplayCommandsSet() {
        for (int i = 0; i < MainMenuCommands.values().length; i++) {
            System.out.println(MainMenuCommands.values()[i].description);
        }
        System.out.println(INPUT_COMMAND_NUMBER);
    }
}
