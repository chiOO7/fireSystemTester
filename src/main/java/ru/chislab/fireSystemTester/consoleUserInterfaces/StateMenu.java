package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.*;
import ru.chislab.fireSystemTester.enums.States;

import java.util.Collections;
import java.util.Scanner;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateMenu extends ConsoleUIMenu{
//    private int stateNumber;
    private States state;

//    public States getState() {
//        return state;
//    }

    @Override
    public String toString() {
        return "Состояние " + getMenuRowNumber() + ": " + state;
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
