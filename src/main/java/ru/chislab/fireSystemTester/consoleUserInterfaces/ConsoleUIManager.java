package ru.chislab.fireSystemTester.consoleUserInterfaces;

import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.ArrayList;
import java.util.List;

public class ConsoleUIManager {
    private final static List<ChapterMenu> chapterMenus = new ArrayList<>();

//    private final static List<StateMenu> stateMenus = new ArrayList<>();

    private final ZoneManager zoneManager;

    public ConsoleUIManager(ZoneManager zoneManager) {
        this.zoneManager = zoneManager;

        List<Zone> zones = zoneManager.getZones();



        List<ZoneMenu> zoneMenus = new ArrayList<>();

        for (Zone zone : zones) {
            StateMenu stateMenu1 = new StateMenu(1, zone.getState().getStates().get(0));
            StateMenu stateMenu2 = new StateMenu(2, zone.getState().getStates().get(1));
            List<StateMenu> stateMenus = new ArrayList<>();
            stateMenus.add(stateMenu1);
            stateMenus.add(stateMenu2);

            ZoneMenu zoneMenu = new ZoneMenu(zone.getConfiguration().getModbusZoneNumber(), stateMenus);

            zoneMenus.add(zoneMenu);
        }


//            ChapterMenu chapterMenu = new ChapterMenu(zone.getConfiguration().getModbusChapterNumber());





    }
}
