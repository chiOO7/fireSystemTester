package ru.chislab.fireSystemTester;

import com.intelligt.modbus.jlibmodbus.Modbus;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chislab.fireSystemTester.enums.States;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;
import ru.chislab.fireSystemTester.zones.ZoneManager;

import java.util.ArrayList;
import java.util.List;


public class ApplicationRunner {
    private final static String LOG4J_CONFIGURATION_PATH = "log4j.properties";

    private final static Logger logger = LoggerFactory.getLogger(ApplicationRunner.class.getName());


    public static void main(String[] args) {
        BasicConfigurator.configure();

        PropertyConfigurator.configure(LOG4J_CONFIGURATION_PATH);

        logger.info("Application start");
        Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);

        ModbusDataSource dataSource = new ModbusDataSource();

        ZoneManager zoneManager = new ZoneManager(dataSource);
        zoneManager.readZoneConfigsFromDevice();
        zoneManager.updateZonesState();


        System.out.println("\nZones :");

        zoneManager.getZones().stream().map(zone -> zone.toString()).forEach(System.out::println);

        int number = 1;
        System.out.println("\nZone" + number + " :");
        zoneManager.updateZoneStateByZoneNumber(number);
        try {
            System.out.println(zoneManager.getZoneByZoneNumber(number).getZoneState());
        } catch (ZoneNotFoundException e) {
            logger.error(e.getMessage());
        }
        List<States> state = new ArrayList<>();
        state.add(States.FIRE);
        state.add(States.ACTUATOR_FAILURE);
        zoneManager.setZoneStateByZoneNumber(number, state);

        zoneManager.updateZoneStateByZoneNumber(number);
        try {
            System.out.println(zoneManager.getZoneByZoneNumber(number).getZoneState());
        } catch (ZoneNotFoundException e) {
            logger.error(e.getMessage());
        }
        logger.info("Application ends");
    }
}
