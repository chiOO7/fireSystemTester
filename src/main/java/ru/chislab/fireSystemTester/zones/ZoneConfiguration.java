package ru.chislab.fireSystemTester.zones;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.chislab.fireSystemTester.enums.ZoneTypes;

@Getter
@Setter
@ToString
public class ZoneConfiguration {

    private Integer modbusZoneNumber;
    private Integer deviceAddress;
    private Integer signalLineNumber;
    private Integer modbusChapterNumber;
    private ZoneTypes zoneType;

}
