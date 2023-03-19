package ru.chislab.fireSystemTester.dto;

import lombok.Builder;
import lombok.Data;
import ru.chislab.fireSystemTester.enums.ZoneTypes;
import ru.chislab.fireSystemTester.zones.ZoneState;

@Data
@Builder
public class ZoneDto {

    private Integer zoneNumber;

    private Integer signalLineNumber;

    private ZoneTypes zoneType;

    private String zoneName;

    private ZoneState zoneState;
}
