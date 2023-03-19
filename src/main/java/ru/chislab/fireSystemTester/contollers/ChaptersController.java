package ru.chislab.fireSystemTester.contollers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.dto.ChapterDto;
import ru.chislab.fireSystemTester.mappers.ChapterMapper;

import java.util.List;

@RestController
public class ChaptersController {

    private final ChapterManager chapterManager;

    private final ChapterMapper chapterMapper;

    public ChaptersController(ChapterManager chapterManager, ChapterMapper chapterMapper) {
        this.chapterManager = chapterManager;
        this.chapterMapper = chapterMapper;
    }

    @GetMapping("/chapters")
    public List<ChapterDto> getChapters() {
        return chapterMapper.chaptersToChapterDtos(chapterManager.getAvailableChapters());
    }

    @GetMapping("/chapters/device")
    public List<ChapterDto> getChaptersFromDevice() {
        chapterManager.initChaptersFromDevice();
        chapterManager.getZoneManager().updateZonesState(chapterManager.getZoneManager().getZones());
        return chapterMapper.chaptersToChapterDtos(chapterManager.getAvailableChapters());
    }

    @GetMapping("/chapters/storage")
    public List<ChapterDto> getChaptersFromStorage() {
        chapterManager.initChaptersFromStorage();
        chapterManager.getZoneManager().updateZonesState(chapterManager.getZoneManager().getZones());
        return chapterMapper.chaptersToChapterDtos(chapterManager.getAvailableChapters());
    }
}
