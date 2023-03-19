package ru.chislab.fireSystemTester.mappers;


import org.springframework.stereotype.Component;
import ru.chislab.fireSystemTester.chapters.Chapter;
import ru.chislab.fireSystemTester.dto.ChapterDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChapterMapper {

    private final ZoneMapper zoneMapper;

    public ChapterMapper(ZoneMapper zoneMapper) {
        this.zoneMapper = zoneMapper;
    }

    public ChapterDto chapterToChapterDto(Chapter chapter) {
        return ChapterDto.builder()
                .chapterNumber(chapter.getModbusChapterNumber())
                .deviceType(chapter.getDeviceType())
                .deviceAddress(chapter.getDeviceAddress())
                .chapterName(chapter.getChapterName())
                .zones(zoneMapper.zonesToZoneDtos(chapter.getZones()))
                .build();
    }

    public List<ChapterDto> chaptersToChapterDtos(List<Chapter> chapters) {
        return chapters.stream().map(this::chapterToChapterDto).collect(Collectors.toList());
    }
}
