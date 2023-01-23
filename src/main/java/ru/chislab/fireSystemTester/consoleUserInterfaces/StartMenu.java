package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.*;


@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class StartMenu extends ConsoleUIMenu {
    @Override
    protected void printMenuHeader() {
        System.out.println();
        System.out.println("# Главное меню:");
        System.out.println("0. Выход из программы");
    }
}
