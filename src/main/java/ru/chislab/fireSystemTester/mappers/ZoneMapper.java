package ru.chislab.fireSystemTester.mappers;


import org.springframework.stereotype.Component;
import ru.chislab.fireSystemTester.dto.ZoneDto;
import ru.chislab.fireSystemTester.zones.Zone;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ZoneMapper {
    public ZoneDto zoneToZoneDto(Zone zone) {
        return ZoneDto.builder()
                .zoneNumber(zone.getModbusZoneNumber())
                .signalLineNumber(zone.getSignalLineNumber())
                .zoneType(zone.getZoneType())
                .zoneName(zone.getZoneName())
                .zoneState(zone.getZoneState())
                .build();
    }

    public List<ZoneDto> zonesToZoneDtos(List<Zone> zones) {
        return zones.stream().map(this::zoneToZoneDto).collect(Collectors.toList());
    }
}
