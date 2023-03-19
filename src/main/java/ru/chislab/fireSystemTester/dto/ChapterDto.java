package ru.chislab.fireSystemTester.dto;

import lombok.Builder;
import lombok.Data;
import ru.chislab.fireSystemTester.enums.DeviceType;

import java.util.List;

@Data
@Builder
public class ChapterDto {

    private Integer chapterNumber;

    private DeviceType deviceType;

    private Integer deviceAddress;

    private String chapterName;

    private List<ZoneDto> zones;

}
