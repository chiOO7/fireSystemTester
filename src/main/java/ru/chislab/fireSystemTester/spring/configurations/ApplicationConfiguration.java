package ru.chislab.fireSystemTester.spring.configurations;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.chislab.fireSystemTester.consoleUserInterfaces.ConsoleUIManager;
import ru.chislab.fireSystemTester.consoleUserInterfaces.StartMenu;
import ru.chislab.fireSystemTester.modbus.ModbusDataSource;

@Configuration
@ComponentScan(basePackages = "ru.chislab.fireSystemTester")
public class ApplicationConfiguration {

    private static final String PORT = "COM2";

    private static final int SLAVE_ID = 1;

    @Autowired
    private ConsoleUIManager consoleUIManager;

    @Bean
    public SessionFactory sessionFactory() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure();

        return configuration.buildSessionFactory();
    }

    @Bean
    public ModbusDataSource modbusDataSource() {
        ModbusDataSource modbusDataSource = new ModbusDataSource(PORT, SLAVE_ID);

        return modbusDataSource;
    }

    @Bean
    public StartMenu startMenu() {
        return consoleUIManager.getStartMenu();
    }
}
