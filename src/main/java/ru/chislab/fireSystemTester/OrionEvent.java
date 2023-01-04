package ru.chislab.fireSystemTester;

import lombok.Getter;
import ru.chislab.fireSystemTester.enums.Events;
import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneConfiguration;

import java.time.LocalDateTime;

@Getter
public class OrionEvent {
    private Events event;
    private Zone zone;
    private ModbusChapter chapter;
    private User user;
    private LocalDateTime dateTime;
    private Coil coil;
}
