package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;



public class ReadZonesFromDeviceMenu extends ConsoleUIMenu{
    @Override
    public void processMenu() {
        getChapterManager().initChaptersFromDevice();
    }

    public ReadZonesFromDeviceMenu(String menuName) {
        super(menuName);
    }
}
