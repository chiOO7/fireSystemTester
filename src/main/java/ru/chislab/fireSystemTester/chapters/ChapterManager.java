package ru.chislab.fireSystemTester.chapters;

import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChapterManager {
    private static final int CHAPTERS_COUNT = 64;
    private Chapter[] chapters;
//    private ZoneManager zoneManager;

    public ChapterManager(ZoneManager zoneManager) {
//        this.zoneManager = zoneManager;
        chapters = new Chapter[CHAPTERS_COUNT];
//        Arrays.fill(chapters, new Chapter());
        for (int i = 0; i < chapters.length; i++) {
            chapters[i] = new Chapter();
            chapters[i].setZones(new ArrayList<>());
        }
        zoneManager.defineZones();
        zoneManager.updateZonesState();
        for (Zone zone : zoneManager.getZones()) {
            int cN = zone.getConfiguration().getModbusChapterNumber();
            List<Zone> zones = chapters[zone.getConfiguration().getModbusChapterNumber() - 1].getZones();

            zones.add(zone);
        }
    }

    public Chapter getChapterByNumber(int number) {
        return chapters[number - 1];
    }

    public Chapter[] getChapters() {
        return chapters;
    }

    public List<Chapter> getAvailableChapters() {
        List<Chapter> availableChapters = new ArrayList<>();
        for (Chapter chapter : chapters) {
            if (!chapter.getZones().isEmpty()) availableChapters.add(chapter);
        }
        return availableChapters;
    }
}


