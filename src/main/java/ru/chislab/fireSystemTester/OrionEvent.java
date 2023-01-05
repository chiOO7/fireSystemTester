package ru.chislab.fireSystemTester;

import lombok.Getter;
import ru.chislab.fireSystemTester.chapters.Chapter;
import ru.chislab.fireSystemTester.coils.Coil;
import ru.chislab.fireSystemTester.enums.Events;
import ru.chislab.fireSystemTester.zones.Zone;

import java.time.LocalDateTime;

@Getter
public class OrionEvent {
    private Events event;
    private Zone zone;
    private Chapter chapter;
    private User user;
    private LocalDateTime dateTime;
    private Coil coil;
}
