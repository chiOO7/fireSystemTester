package ru.chislab.fireSystemTester;

import ru.chislab.fireSystemTester.chapters.ChapterConfiguration;
import ru.chislab.fireSystemTester.enums.Events;
import ru.chislab.fireSystemTester.enums.ZoneTypes;
import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneConfiguration;
import ru.chislab.fireSystemTester.zones.ZoneState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModbusDataSource {

//    public long[] getModbusChaptersIDs() {
//        long[] modbusChaptersIDs = new long[64];
//        Arrays.fill(modbusChaptersIDs, 0);
//        modbusChaptersIDs[0] = 1;
//        modbusChaptersIDs[1] = 23;
//        modbusChaptersIDs[2] = 5554;
//        return modbusChaptersIDs;
//    }

//    public ChapterConfiguration[] getModbusChaptersConfigurations() {
//        ChapterConfiguration[] modbusChaptersConfigurations = new ChapterConfiguration[64];
//        for (int i = 0; i < modbusChaptersConfigurations.length; i++) {
//            ChapterConfiguration configuration = new ChapterConfiguration();
//            configuration.setModbusChapterNumber(i + 1);
//            configuration.setChapterID(0L);
//            modbusChaptersConfigurations[i] = configuration;
//        }
//        modbusChaptersConfigurations[0].setChapterID(1L);
//        modbusChaptersConfigurations[1].setChapterID(23L);
//        modbusChaptersConfigurations[2].setChapterID(5554L);
//        return modbusChaptersConfigurations;
//    }

    //Mock
    public List<ZoneConfiguration> getModbusZoneConfigurations() {

        ZoneConfiguration zoneConfiguration1 = new ZoneConfiguration();
        ZoneConfiguration zoneConfiguration2 = new ZoneConfiguration();
        ZoneConfiguration zoneConfiguration3 = new ZoneConfiguration();

        zoneConfiguration1.setModbusZoneNumber(1);
        zoneConfiguration1.setZoneType(ZoneTypes.SIGNAL_LINE_STATE);
        zoneConfiguration1.setModbusChapterNumber(1);
        zoneConfiguration1.setDeviceAddress(1);
        zoneConfiguration1.setSignalLineNumber(1);

        zoneConfiguration2.setModbusZoneNumber(2);
        zoneConfiguration2.setZoneType(ZoneTypes.SIGNAL_LINE_STATE);
        zoneConfiguration2.setModbusChapterNumber(1);
        zoneConfiguration2.setDeviceAddress(1);
        zoneConfiguration2.setSignalLineNumber(2);

        zoneConfiguration3.setModbusZoneNumber(3);
        zoneConfiguration3.setZoneType(ZoneTypes.SIGNAL_LINE_STATE);
        zoneConfiguration3.setModbusChapterNumber(1);
        zoneConfiguration3.setDeviceAddress(1);
        zoneConfiguration3.setSignalLineNumber(3);

        List<ZoneConfiguration> zoneConfigurations = new ArrayList<>();
        zoneConfigurations.add(zoneConfiguration1);
        zoneConfigurations.add(zoneConfiguration2);
        zoneConfigurations.add(zoneConfiguration3);

        return zoneConfigurations;
    }

    //Mock
    public ZoneState getZoneStateByModbusZoneNumber(int number) {
        ZoneState state = new ZoneState();
        List<Events> events = new ArrayList<>();
        events.add(Events.FIRE);
        events.add(Events.ACTUATOR_FAILURE);
        state.setState(events);

        return state;
    }


}
