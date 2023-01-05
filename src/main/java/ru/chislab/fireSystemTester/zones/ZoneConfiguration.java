package ru.chislab.fireSystemTester.zones;

import lombok.Getter;
import lombok.Setter;
import ru.chislab.fireSystemTester.enums.ZoneTypes;

@Getter
@Setter
public class ZoneConfiguration {

    private Integer deviceAddress;

    private Integer signalLineNumber;
    private Integer modbusChapterNumber;
    private ZoneTypes zoneType;

}
