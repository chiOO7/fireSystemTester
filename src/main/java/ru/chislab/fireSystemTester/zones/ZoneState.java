package ru.chislab.fireSystemTester.zones;

import lombok.*;
import ru.chislab.fireSystemTester.enums.States;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZoneState {

    private List<States> states;

}
