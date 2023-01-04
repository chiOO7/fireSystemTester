package ru.chislab.fireSystemTester.zones;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Zone {

    private ZoneConfiguration configuration;

    private ZoneState state;

    public Zone(ZoneConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zone zone = (Zone) o;
        return Objects.equals(configuration, zone.configuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(configuration, state);
    }
}
