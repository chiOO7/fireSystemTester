package ru.chislab.fireSystemTester.chapters;

import lombok.Getter;
import ru.chislab.fireSystemTester.dao.ChapterDao;
import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ChapterManager {
    private static final int CHAPTERS_COUNT = 64;
    private final Chapter[] chapters = new Chapter[CHAPTERS_COUNT];
    private final ZoneManager zoneManager;
    private final ChapterDao chapterDao;

    public ChapterManager(ZoneManager zoneManager) {
        this.zoneManager = zoneManager;
        chapterDao = new ChapterDao();
        reInitChapters();
    }

    public void saveChaptersToStorage() {
        chapterDao.saveChaptersToStorage(chapters);
    }

    public void getChaptersFromStorage() {
        System.arraycopy(chapterDao.getChapters(), 0, chapters, 0, CHAPTERS_COUNT);
    }

    public void initChaptersFromDevice() {
        reInitChapters();
        zoneManager.clearZones();
        zoneManager.readZoneConfigsFromDevice();
        addNewZoneToChapter();
    }

    public void reInitChapters() {
        for (int i = 0; i < chapters.length; i++) {
            chapters[i] = new Chapter(i + 1);
        }
    }

    private void addNewZoneToChapter() {
        for (Zone zone : zoneManager.getZones()) {
            Chapter chapter = chapters[zone.getModbusChapterNumber() - 1];
            chapter.setDeviceAddress(zone.getDeviceAddress());
            if (!chapter.getZones().contains(zone) && (chapter.getModbusChapterNumber().equals(zone.getModbusChapterNumber()))) {
                chapter.getZones().add(zone);
                chapter.setDeviceAddress(zone.getDeviceAddress());
            }
        }
    }

    public void initChaptersFromStorage() {
        //reInitChapters();
        getChaptersFromStorage();
        zoneManager.clearZones();
        zoneManager.getZonesFromStorage();
        addNewZoneToChapter();
    }

    public Chapter getChapterByNumber(int number) {
        return chapters[number - 1];
    }

    public List<Chapter> getAvailableChapters() {
        List<Chapter> availableChapters = new ArrayList<>();
        for (Chapter chapter : chapters) {
            if (!chapter.getZones().isEmpty()) availableChapters.add(chapter);
        }
        return availableChapters;
    }
}


