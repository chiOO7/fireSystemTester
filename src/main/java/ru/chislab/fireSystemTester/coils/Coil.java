package ru.chislab.fireSystemTester.coils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.chislab.fireSystemTester.devices.Device;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coil {
    private Device device;
}
