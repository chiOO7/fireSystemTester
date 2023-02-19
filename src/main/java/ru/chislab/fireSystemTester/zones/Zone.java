package ru.chislab.fireSystemTester.zones;

import lombok.*;
import ru.chislab.fireSystemTester.enums.ZoneTypes;

@Data
@EqualsAndHashCode(exclude = {"zoneState", "zoneName"})
public class Zone {
    private Integer modbusZoneNumber;

    private Integer deviceAddress;

    private Integer signalLineNumber;

    private Integer modbusChapterNumber;

    private ZoneTypes zoneType;

    private String zoneName;

    private ZoneState zoneState;

    public Zone(ZoneConfigurationDto configuration) {
        this.modbusZoneNumber = configuration.getModbusZoneNumber();
        this.deviceAddress = configuration.getDeviceAddress();
        this.signalLineNumber = configuration.getSignalLineNumber();
        this.modbusChapterNumber = configuration.getModbusChapterNumber();
        this.zoneType = configuration.getZoneType();
        this.zoneName = "Имя зоны не задано";
    }
}
