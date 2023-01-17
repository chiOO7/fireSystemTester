package ru.chislab.fireSystemTester.chapters;

import ru.chislab.fireSystemTester.ModbusDataSource;
import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.ArrayList;
import java.util.List;

public class ChapterManager {

    private static final int CHAPTERS_COUNT = 64;


    private Chapter[] chapters;
    private ZoneManager zoneManager;

    public ChapterManager(ZoneManager zoneManager) {

        this.zoneManager = zoneManager;
        chapters = new Chapter[CHAPTERS_COUNT];
    }

    public Chapter getChapterByNumber(int number) {
        return chapters[number - 1];
    }

    public Chapter[] getChapters() {return chapters;}

}
