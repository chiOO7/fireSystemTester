package ru.chislab.fireSystemTester;

import com.intelligt.modbus.jlibmodbus.Modbus;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.consoleUserInterfaces.ConsoleUIManager;
import ru.chislab.fireSystemTester.consoleUserInterfaces.StartMenu;
import ru.chislab.fireSystemTester.modbus.ModbusDataSource;
import ru.chislab.fireSystemTester.zones.ZoneManager;


public class ApplicationRunner {
    private final static String LOG4J_CONFIGURATION_PATH = "log4j.properties";

    private final static Logger logger = LoggerFactory.getLogger(ApplicationRunner.class.getName());

    private static final String PORT = "COM2";

    private static final int SLAVE_ID = 1;


    public static void main(String[] args) {

        BasicConfigurator.configure();
        PropertyConfigurator.configure(LOG4J_CONFIGURATION_PATH);
        Modbus.setLogLevel(Modbus.LogLevel.LEVEL_WARNINGS);
        logger.info("Application start");
        ModbusDataSource modbusDataSource = new ModbusDataSource(PORT, SLAVE_ID);
//        ModbusDataSource modbusDataSource = new ModbusDataSourceForTests();
        ZoneManager zoneManager = new ZoneManager(modbusDataSource);
        ChapterManager chapterManager = new ChapterManager(zoneManager);
        ConsoleUIManager consoleUIManager = new ConsoleUIManager(chapterManager);

        StartMenu startMenu = consoleUIManager.getStartMenu();
        startMenu.processMenu();

        logger.info("Application ends");
    }
}
