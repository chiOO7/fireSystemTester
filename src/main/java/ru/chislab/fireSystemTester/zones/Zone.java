package ru.chislab.fireSystemTester.zones;

import jakarta.persistence.*;
import lombok.*;
import ru.chislab.fireSystemTester.chapters.Chapter;
import ru.chislab.fireSystemTester.enums.ZoneTypes;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"zoneState", "zoneName"})
@Builder
@Entity
@Table(name = "zones")
public class Zone {
    @Id
    @Column(name = "zone_number")
    private Integer modbusZoneNumber;

    @Transient
    private Integer deviceAddress;

    @Column(name = "signal_line_number")
    private Integer signalLineNumber;

    @Transient
    private Integer modbusChapterNumber;

    @ManyToOne
    @JoinColumn(name = "chapter_number")
    private Chapter chapter;

    @Column(name = "zone_type")
    @Enumerated(EnumType.STRING)
    private ZoneTypes zoneType;

    @Column(name = "zone_name")
    private String zoneName;

    @Transient
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
