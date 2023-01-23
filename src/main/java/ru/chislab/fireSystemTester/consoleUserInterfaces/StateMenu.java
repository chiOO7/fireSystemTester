package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.chislab.fireSystemTester.enums.States;

import java.util.Collections;
import java.util.Scanner;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateMenu extends ConsoleUIMenu{
    private int stateNumber;
    private States state;

    @Override
    public String toString() {
        return "Состояние " + stateNumber + ": " + state;
    }

    @Override
    public void printMenus() {
        printMenuHeader();
        for (int i = 0; i < States.values().length; i++) {
            System.out.println((i + 1) + ". " + States.values()[i]);
        }
        printMenuFooter();
    }
}
