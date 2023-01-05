package ru.chislab.fireSystemTester.coils;

import lombok.Getter;
import lombok.Setter;
import ru.chislab.fireSystemTester.devices.Device;

@Getter
@Setter
public class CoilConfiguration {

    private Integer coilNumber;
    private Integer deviceAddress;
    private Integer coilNumberInDevice;
}
