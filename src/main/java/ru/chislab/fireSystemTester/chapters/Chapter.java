package ru.chislab.fireSystemTester.chapters;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import ru.chislab.fireSystemTester.enums.DeviceType;
import ru.chislab.fireSystemTester.zones.Zone;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "chapterName")
@Entity
@Table(name = "chapters")
public class Chapter {
    @Id
    @Column(name = "chapter_number")
    private Integer modbusChapterNumber;

    @Column(name = "device_type")
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @Column(name = "device_address")
    private Integer deviceAddress;

    @Column(name = "chapter_name")
    private String chapterName;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "chapter_number")
    private List<Zone> zones;

    public Chapter(Integer modbusChapterNumber) {
        this.modbusChapterNumber = modbusChapterNumber;
        this.zones = new ArrayList<>();
        this.chapterName = "Имя раздела не установлено";
    }
}
