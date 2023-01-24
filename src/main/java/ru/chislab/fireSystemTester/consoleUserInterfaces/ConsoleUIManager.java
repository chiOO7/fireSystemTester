package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import ru.chislab.fireSystemTester.ModbusDataSource;
import ru.chislab.fireSystemTester.chapters.Chapter;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;
import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
//@NoArgsConstructor
@Data
@Builder
public class ConsoleUIManager {
    private Scanner scanner;
    private ChapterManager chapterManager;

    public ConsoleUIManager() {
        chapterManager = new ChapterManager(new ZoneManager(new ModbusDataSource()));
        scanner = new Scanner(System.in);
    }

    public ConsoleUIManager(ChapterManager chapterManager) {
        this.chapterManager = chapterManager;
        this.scanner = new Scanner(System.in);
    }

    public ConsoleUIManager(ChapterManager chapterManager, Scanner scanner) {
        this.chapterManager = chapterManager;
        this.scanner = scanner;
    }

//    public static void main(String[] args) throws ZoneNotFoundException {
//        String LOG4J_CONFIGURATION_PATH = "log4j.properties";
//        BasicConfigurator.configure();
//        PropertyConfigurator.configure(LOG4J_CONFIGURATION_PATH);
//
//        ModbusDataSource modbusDataSource = new ModbusDataSource();
//        ZoneManager zoneManager = new ZoneManager(modbusDataSource);
//        ChapterManager chapterManager = new ChapterManager(zoneManager);
//        ConsoleUIManager consoleUIManager = new ConsoleUIManager(chapterManager);
//        consoleUIManager.initMenus();
//    }

    public List<ConsoleUIMenu> getStateMenusByZoneNumber(int zoneNumber) throws ZoneNotFoundException {
        List<ConsoleUIMenu> stateMenus = new ArrayList<>();
        ChapterManager chapterManager1 = this.getChapterManager();
        chapterManager1.getZoneManager().updateZonesState();
        Zone zone = chapterManager1.getZoneManager().getZoneByZoneNumber(zoneNumber);
        StateMenu stateMenu1 = new StateMenu();
        stateMenu1.setMenuRowNumber(1);
        stateMenu1.setState(zone.getZoneState().getStates().get(0));
        stateMenu1.setScanner(scanner);
        StateMenu stateMenu2 = new StateMenu();
        stateMenu2.setMenuRowNumber(2);
        stateMenu2.setState(zone.getZoneState().getStates().get(1));
        stateMenu2.setScanner(scanner);
        stateMenus.add(stateMenu1);
        stateMenus.add(stateMenu2);


        return stateMenus;
    }

    public List<ConsoleUIMenu> getZoneMenusByChapterNumber(int chapterNumber) throws ZoneNotFoundException {
        List<ConsoleUIMenu> zoneMenus = new ArrayList<>();
        Chapter chapter = chapterManager.getChapterByNumber(chapterNumber);
        for (int i = 0; i < chapter.getZones().size(); i++) {
            ZoneMenu zoneMenu = new ZoneMenu();
            zoneMenu.setZoneName("ZM " + (i + 1));
            zoneMenu.setScanner(scanner);
            zoneMenu.setChapterManager(chapterManager);
            zoneMenu.setSubMenus(getStateMenusByZoneNumber(i + 1));
            zoneMenus.add(zoneMenu);
        }

        return zoneMenus;
    }

    public List<ConsoleUIMenu> getChapterMenus() throws ZoneNotFoundException {
        List<ConsoleUIMenu> chapterMenus = new ArrayList<>();

        chapterManager.initChaptersFromDevice();

        List<Chapter> chapters = chapterManager.getAvailableChapters();
        for (int i = 0; i < chapters.size(); i++) {
            ChapterMenu chapterMenu = new ChapterMenu();
            chapterMenu.setChapterMenuName("CM " + (i + 1));
            chapterMenu.setChapterMenuNumber(i + 1);
            chapterMenu.setChapterManager(chapterManager);
            chapterMenu.setScanner(scanner);
            chapterMenu.setSubMenus(getZoneMenusByChapterNumber(i + 1));
            chapterMenus.add(chapterMenu);
        }

        return chapterMenus;
    }

    public StartMenu getStartMenu() {
        StartMenu startMenu = new StartMenu("Главное меню");
        startMenu.setScanner(getScanner());
        startMenu.getSubMenus().add(getRead___Menu());
        startMenu.getSubMenus().add(getAvail___Menu());

        return startMenu;
    }

    public ConsoleUIMenu getRead___Menu() {
        CommonMenu read___Menu = new CommonMenu("Read from dev_");
        read___Menu.setScanner(getScanner());
        read___Menu.getSubMenus().add(getRead2___Menu());

        return read___Menu;
    }

    public ReadZonesFromDeviceMenu getRead2___Menu() {
        ReadZonesFromDeviceMenu read___Menu = new ReadZonesFromDeviceMenu("Read 0007 from dev_");
        read___Menu.setScanner(getScanner());
        read___Menu.setChapterManager(getChapterManager());

        return read___Menu;
    }

    public ConsoleUIMenu getAvail___Menu() {
        CommonMenu avail___Menu = new CommonMenu("Avail chaps");
        avail___Menu.setScanner(getScanner());

        return avail___Menu;
    }



    public void initMenus() throws ZoneNotFoundException {
        StartMenu startMenu = getStartMenu();
        startMenu.processMenu();

    }

}
