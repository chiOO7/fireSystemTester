package ru.chislab.fireSystemTester.chapters;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chislab.fireSystemTester.repositories.ChapterRepository;
import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class ChapterManager {
    private static final int CHAPTERS_COUNT = 64;
    private final Chapter[] chapters = new Chapter[CHAPTERS_COUNT];
    private final ZoneManager zoneManager;
    private final ChapterRepository chapterRepository;

    public ChapterManager(ZoneManager zoneManager, ChapterRepository chapterRepository) {
        this.zoneManager = zoneManager;
        this.chapterRepository = chapterRepository;
        reInitChapters();
    }

    @Transactional
    public void saveChaptersToStorage() {
        chapterRepository.saveAll(getAvailableChapters());
    }

    @Transactional(readOnly = true)
    public void initChaptersFromStorage() {
        List<Chapter> chaptersFromStorage = getAvailableChaptersFromDb();
        zoneManager.clearZones();
        for (Chapter chapter : chaptersFromStorage) {
            zoneManager.getZones().addAll(chapter.getZones());
            chapters[chapter.getModbusChapterNumber() - 1] = chapter;
        }

    }

    public void initChaptersFromDevice() {
        reInitChapters();
        zoneManager.clearZones();
        zoneManager.readZoneConfigsFromDevice();
        addNewZoneToChapter();
    }

    @Transactional
    public void updateChapter(Chapter chapter) {
        chapterRepository.update(chapter.getModbusChapterNumber(), chapter.getChapterName());
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

    public Chapter getChapterByNumber(int number) {
        return chapters[number - 1];
    }

    public List<Chapter> getAvailableChapters() {
        List<Chapter> availableChapters = new ArrayList<>();
        for (Chapter chapter : chapters) {
            if (chapter != null) {
                if (!chapter.getZones().isEmpty()) availableChapters.add(chapter);
            }
        }
        return availableChapters;
    }

    public List<Chapter> getAvailableChaptersFromDb() {
        return chapterRepository.findAll();
    }
}


