package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.Getter;
import lombok.Setter;
import ru.chislab.fireSystemTester.enums.Events;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Setter
public class StateMenu extends ConsoleUIMenu{

    private final Events state;

    public StateMenu(Events state, Scanner scanner) {
        super(state.toString(), scanner, Collections.emptyList());
        this.state = state;
    }

    @Override
    public void printMenus() {
        printMenuHeader();
        for (int i = 0; i < Events.values().length; i++) {
            System.out.println((i + 1) + ". " + Events.values()[i]);
        }
        printMenuFooter();
    }
}
