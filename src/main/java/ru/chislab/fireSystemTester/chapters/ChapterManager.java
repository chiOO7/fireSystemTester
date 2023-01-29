package ru.chislab.fireSystemTester.chapters;

import lombok.Getter;
import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ChapterManager {
    private static final int CHAPTERS_COUNT = 64;
    private static final Chapter[] chapters = new Chapter[CHAPTERS_COUNT];
    private final ZoneManager zoneManager;

    public ChapterManager(ZoneManager zoneManager) {
        this.zoneManager = zoneManager;
        reInitChapters();
    }

    public void initChaptersFromDevice() {
        reInitChapters();
        zoneManager.clearZones();
        zoneManager.readZoneConfigsFromDevice();
        addNewZoneToChapter();
    }

    public void reInitChapters() {
        for (int i = 0; i < chapters.length; i++) {
            chapters[i] = new Chapter();
            chapters[i].setModbusChapterNumber(i + 1);
            chapters[i].setZones(new ArrayList<>());
        }
    }

    private void addNewZoneToChapter() {
        for (Zone zone : zoneManager.getZones()) {
            Chapter chapter = chapters[zone.getModbusChapterNumber() - 1];
            if (!chapter.getZones().contains(zone) && (chapter.getModbusChapterNumber().equals(zone.getModbusChapterNumber()))) {
                chapter.getZones().add(zone);
                chapter.setDeviceAddress(zone.getDeviceAddress());
            }
        }
    }

    public void initChaptersFromStorage() {
        reInitChapters();
        zoneManager.clearZones();
        zoneManager.getZonesFromStorage();
        addNewZoneToChapter();
    }

    public Chapter getChapterByNumber(int number) {
        return chapters[number - 1];
    }

//    public Chapter[] getChapters() {
//        return chapters;
//    }

    public List<Chapter> getAvailableChapters() {
        List<Chapter> availableChapters = new ArrayList<>();
        for (Chapter chapter : chapters) {
            if (!chapter.getZones().isEmpty()) availableChapters.add(chapter);
        }
        return availableChapters;
    }
}


