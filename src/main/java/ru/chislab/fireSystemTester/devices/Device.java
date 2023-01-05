package ru.chislab.fireSystemTester.devices;

import lombok.Getter;
import lombok.Setter;
import ru.chislab.fireSystemTester.coils.Coil;
import ru.chislab.fireSystemTester.SignalLine;

import java.util.List;

@Getter
@Setter
public abstract class Device {
    private List<SignalLine> signalLines;
    private List<Coil> coils;
}
