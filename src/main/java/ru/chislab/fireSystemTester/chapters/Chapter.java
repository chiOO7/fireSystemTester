package ru.chislab.fireSystemTester.chapters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.chislab.fireSystemTester.devices.Device;
import ru.chislab.fireSystemTester.zones.Zone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {
//    private ChapterConfiguration configuration;
    private int modbusChapterNumber;

    private String chapterName;
    private List<Zone> zones;

    public Chapter(int modbusChapterNumber) {
        this.modbusChapterNumber = modbusChapterNumber;
        this.zones = new ArrayList<>();
        this.chapterName = "Имя раздела не установлено";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return modbusChapterNumber == chapter.modbusChapterNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(modbusChapterNumber);
    }
}
