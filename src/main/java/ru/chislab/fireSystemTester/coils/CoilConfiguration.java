package ru.chislab.fireSystemTester.coils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoilConfiguration {

    private Integer modbusCoilNumber;
    private Integer deviceAddress;
    private Integer coilNumberInDevice;

}
