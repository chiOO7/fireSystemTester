package ru.chislab.fireSystemTester.chapters;

import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
//    private final ChapterDao chapterDao;
    private final ChapterRepository chapterRepository;

    public ChapterManager(ZoneManager zoneManager, ChapterRepository chapterRepository) {
        this.zoneManager = zoneManager;
        this.chapterRepository = chapterRepository;
        reInitChapters();
    }

//    public ChapterManager(ZoneManager zoneManager, ChapterDao chapterDao) {
//        this.zoneManager = zoneManager;
//        this.chapterDao = chapterDao;
//        reInitChapters();
//    }

//    public ChapterManager(ZoneManager zoneManager) {
//        this.zoneManager = zoneManager;
//        this.chapterDao = null;
//        reInitChapters();
//    }


    @Transactional
    public void saveChaptersToStorage() {
//        chapterDao.saveChaptersToStorage(getAvailableChapters());
        chapterRepository.saveAll(getAvailableChapters());
    }

    @Transactional(readOnly = true)
    public void initChaptersFromStorage() {
//        List<Chapter> chaptersFromStorage = chapterDao.getChaptersFromStorage();
        List<Chapter> chaptersFromStorage = getAvailableChaptersFromDb();
        zoneManager.clearZones();
        for (Chapter chapter : chaptersFromStorage) {
//            chapters[chapter.getModbusChapterNumber() - 1] = chapter;
//            Integer chNumber = chapter.getModbusChapterNumber();
            zoneManager.getZones().addAll(chapter.getZones());
//            zoneManager.getZones().addAll(chapters[chapter.getModbusChapterNumber() - 1].getZones());
        }
    }

    public void initChaptersFromDevice() {
        reInitChapters();
        zoneManager.clearZones();
        zoneManager.readZoneConfigsFromDevice();
        addNewZoneToChapter();
    }

    public void updateChapter(Chapter chapter) {
        Chapter updatedChapter = chapterRepository.findById(chapter.getModbusChapterNumber()).get();
        updatedChapter.setModbusChapterNumber(chapter.getModbusChapterNumber());
        updatedChapter.setDeviceType(chapter.getDeviceType());
        updatedChapter.setDeviceAddress(chapter.getDeviceAddress());
        updatedChapter.setZones(chapter.getZones());
        updatedChapter.setChapterName(chapter.getChapterName());

        chapterRepository.save(updatedChapter);
//        chapterRepository.merge(chapter);
//        chapterDao.updateChapter(chapter);
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

    @Transactional(readOnly = true)
    public List<Chapter> getAvailableChaptersFromDb() {
//        List<Chapter> chapters1 = chapterRepository.findAll();
//        for (Chapter chapter: chapters1) {
//            List<Zone>zones = zoneManager.getZoneRepository().findAllById(chapter.getModbusChapterNumber());
//            chapter.setZones(zones);
//        }

                List<Chapter> chapters1 = chapterRepository.getAll();
        return chapters1;
//        return chapterDao.getChaptersFromStorage();
    }
}


