package ru.chislab.fireSystemTester.chapters;

//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.*;
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

    @Column(name = "device_type", length = 64)
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @Column(name = "device_address", nullable = false)
    private Integer deviceAddress;

    @Column(name = "chapter_name", length = 128)
    private String chapterName;

    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_number")
    private List<Zone> zones;

    public Chapter(Integer modbusChapterNumber) {
        this.modbusChapterNumber = modbusChapterNumber;
        this.zones = new ArrayList<>();
        this.chapterName = "Имя раздела не установлено";
    }
}
