package ru.chislab.fireSystemTester.zones;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.chislab.fireSystemTester.enums.States;

import java.util.List;

@Getter
@Setter
@ToString
public class ZoneState {

    private List<States> states;

}
