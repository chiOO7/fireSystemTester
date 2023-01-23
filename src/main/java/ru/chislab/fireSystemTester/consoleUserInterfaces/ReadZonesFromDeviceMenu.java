package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.ArrayList;
import java.util.Scanner;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadZonesFromDeviceMenu extends ConsoleUIMenu{

    private ChapterManager chapterManager;
//    public ReadZonesFromDeviceMenu(Scanner scanner, ChapterManager chapterManager) {
//        super("Считать конфигурацию с устройства", scanner, new ArrayList<>());
//        this.chapterManager = chapterManager;
//    }

    @Override
    public void processMenu() {
        chapterManager.initChaptersFromDevice();
        while (true) {
            printMenuHeader();
            int command = getScanner().nextInt();
            if (command == -1) System.exit(0);
            if (command == 0) break;
            doSomething(command);
        }
    }

    @Override
    public void doSomething(int command) {
        if (command == 1) {
            chapterManager.getZoneManager().saveZonesToStorage();
        }
    }

    @Override
    protected void printMenuHeader() {
        System.out.println();
        System.out.println("# " + getMenuName() + ":");
        System.out.println("0. Назад");
        System.out.println("1. Сохранить найденные зоны в базу");
    }
}
