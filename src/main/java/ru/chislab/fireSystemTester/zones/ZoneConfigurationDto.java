package ru.chislab.fireSystemTester.zones;

import lombok.Builder;
import lombok.Data;
import ru.chislab.fireSystemTester.enums.ZoneTypes;

@Data
@Builder
public class ZoneConfigurationDto {

    private Integer modbusZoneNumber;
    private Integer deviceAddress;
    private Integer signalLineNumber;
    private Integer modbusChapterNumber;
    private ZoneTypes zoneType;

}
