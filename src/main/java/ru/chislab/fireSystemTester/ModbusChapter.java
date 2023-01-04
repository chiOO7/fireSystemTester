package ru.chislab.fireSystemTester;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.chislab.fireSystemTester.zones.Zone;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModbusChapter {
    private Integer chapterNumber;
    private Long id;

    private List<Zone> zones;
}
