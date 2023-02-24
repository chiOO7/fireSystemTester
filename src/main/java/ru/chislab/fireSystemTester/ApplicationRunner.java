package ru.chislab.fireSystemTester;

import com.intelligt.modbus.jlibmodbus.Modbus;
import lombok.Cleanup;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.consoleUserInterfaces.ConsoleUIManager;
import ru.chislab.fireSystemTester.consoleUserInterfaces.StartMenu;
import ru.chislab.fireSystemTester.dao.ChapterDao;
import ru.chislab.fireSystemTester.dao.ZoneDao;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;
import ru.chislab.fireSystemTester.modbus.ModbusDataSource;
import ru.chislab.fireSystemTester.zones.ZoneManager;


public class ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);

    private static final String PORT = "COM2";

    private static final int SLAVE_ID = 1;

    public static void main(String[] args) throws ZoneNotFoundException {

        Modbus.setLogLevel(Modbus.LogLevel.LEVEL_WARNINGS);

        logger.info("Application start");

//        Configuration configuration = new Configuration();
//
//        configuration.configure();
//
//        @Cleanup
//        SessionFactory sessionFactory = configuration.buildSessionFactory();
//
//        ZoneDao zoneDao = new ZoneDao(sessionFactory);
//
//        ChapterDao chapterDao = new ChapterDao(sessionFactory);
//
//        ModbusDataSource modbusDataSource = new ModbusDataSource(PORT, SLAVE_ID);
////        ModbusDataSource modbusDataSource = new ModbusDataSourceForTests();
//        ZoneManager zoneManager = new ZoneManager(modbusDataSource, zoneDao);
//        ChapterManager chapterManager = new ChapterManager(zoneManager, chapterDao);
//        ConsoleUIManager consoleUIManager = new ConsoleUIManager(chapterManager);
//
//        StartMenu startMenu = consoleUIManager.getStartMenu();
//        startMenu.processMenu();





        logger.info("Application ends");
    }
}
