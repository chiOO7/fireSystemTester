package ru.chislab.fireSystemTester;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Zone {

    private Device device;
    private SignalLine signalLine;
    private ModbusChapter modbusChapter;
    private ZoneTypes zoneType;

    public static void main(String[] args) {
//        Zone zone = new Zone();
//        zone.setZoneType(ZoneTypes.BACKUP_POWER_SUPPLY);
//
//        System.out.println(zone.getZoneType().ordinal());
    }
}
