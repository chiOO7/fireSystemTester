package ru.chislab.fireSystemTester.zones;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.chislab.fireSystemTester.enums.ZoneTypes;

@Data
public class ZoneConfigurationDto {

    private Integer modbusZoneNumber;
    private Integer deviceAddress;
    private Integer signalLineNumber;
    private Integer modbusChapterNumber;
    private ZoneTypes zoneType;

}
