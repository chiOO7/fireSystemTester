package ru.chislab.fireSystemTester.zones;

import lombok.Getter;
import lombok.Setter;
import ru.chislab.fireSystemTester.enums.Events;

import java.util.List;

@Getter
@Setter
public class ZoneState {

    private List<Events> state;

}
