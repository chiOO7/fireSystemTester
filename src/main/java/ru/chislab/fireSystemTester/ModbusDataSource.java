package ru.chislab.fireSystemTester;

import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneConfiguration;
import ru.chislab.fireSystemTester.zones.ZoneManager;
import ru.chislab.fireSystemTester.zones.ZoneState;

import java.util.List;

public class ModbusDataSource {





    public long[] getModbusChaptersIDs() {
        return null;
    }


    public List<ZoneConfiguration> getModbusZoneConfigurations() {
        return null;
    }



    public Integer getNumberOfNewestOrionEvent() {
        return null;
    }

    public Integer getNumberOfEldestOrionEvent() {
        return null;
    }

    public Integer getCountOfUnreadOrionEvent() {
        return null;
    }

    public void setOrionEventWasRead() {

    }

    public void cleanOrionEventBuffer() {

    }

    public OrionEvent getEldestUnreadOrionEvent() {
        return null;
    }

    public OrionEvent getOrionEventByNumber(int number) {
        return null;
    }

    public List<ZoneState> getZonesStates() {
        return null;
    }

    public List<ZoneConfiguration> getZonesConfigurations() {
        return null;
    }

}
