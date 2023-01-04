package ru.chislab.fireSystemTester.zones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.chislab.fireSystemTester.ModbusChapter;
import ru.chislab.fireSystemTester.SignalLine;
import ru.chislab.fireSystemTester.devices.Device;
import ru.chislab.fireSystemTester.enums.ZoneTypes;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ZoneConfiguration {

    private Device device;
    private List<SignalLine> signalLines;
    private ModbusChapter modbusChapter;
    private ZoneTypes zoneType;

}
