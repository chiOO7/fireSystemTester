package ru.chislab.fireSystemTester.zones;

import lombok.*;
import ru.chislab.fireSystemTester.enums.ZoneTypes;

import java.util.Objects;


@Data
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
        this.setZoneName("zoneName");
    }

//    public Zone(ZoneConfigurationDto configuration, String zoneName) {
//        this.modbusZoneNumber = configuration.getModbusZoneNumber();
//        this.deviceAddress = configuration.getDeviceAddress();
//        this.signalLineNumber = configuration.getSignalLineNumber();
//        this.modbusChapterNumber = configuration.getModbusChapterNumber();
//        this.zoneType = configuration.getZoneType();
//        this.setZoneName(zoneName);
//    }
}
