package ru.chislab.fireSystemTester.dao;

import lombok.AllArgsConstructor;
import ru.chislab.fireSystemTester.chapters.Chapter;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ChapterDao {
    private final ZoneManager zoneManager;
    private final ChapterManager chapterManager;

    public List<Chapter> getAvailableChapters() {
        List<Chapter> chapters = new ArrayList<>();
        for (Zone zone : zoneManager.getZones()) {
            int chapterNumber = zone.getModbusChapterNumber();
            if (!chapters.contains(new Chapter(chapterNumber))) {
//                Chapter chapter = new Chapter(chapterNumber);
                chapters.add(new Chapter(chapterNumber));
            }
        }

        for (Zone zone : zoneManager.getZones()) {
            for (Chapter chapter : chapters) {
                if (zone.getModbusChapterNumber() == chapter.getModbusChapterNumber()) {
                    chapter.getZones().add(zone);
                }
            }
        }
        return chapters;
    }
}
