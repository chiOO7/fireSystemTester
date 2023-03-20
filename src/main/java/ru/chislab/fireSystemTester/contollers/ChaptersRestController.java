package ru.chislab.fireSystemTester.contollers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.dto.ChapterDto;
import ru.chislab.fireSystemTester.mappers.ChapterMapper;

import java.util.List;

@RestController
@RequestMapping("/chapters")
public class ChaptersRestController {

    private final ChapterManager chapterManager;

    private final ChapterMapper chapterMapper;

    public ChaptersRestController(ChapterManager chapterManager, ChapterMapper chapterMapper) {
        this.chapterManager = chapterManager;
        this.chapterMapper = chapterMapper;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/device")
    public List<ChapterDto> getChaptersFromDevice() {
        chapterManager.initChaptersFromDevice();
        chapterManager.getZoneManager().updateZonesState(chapterManager.getZoneManager().getZones());
        return chapterMapper.chaptersToChapterDtos(chapterManager.getAvailableChapters());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/storage")
    public List<ChapterDto> getChaptersFromStorage() {
        chapterManager.initChaptersFromStorage();
        chapterManager.getZoneManager().updateZonesState(chapterManager.getZoneManager().getZones());
        return chapterMapper.chaptersToChapterDtos(chapterManager.getAvailableChapters());
    }
}
