package ru.chislab.fireSystemTester;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.chislab.fireSystemTester.consoleUserInterfaces.StartMenu;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;
import ru.chislab.fireSystemTester.spring.configurations.ApplicationConfiguration;

public class SpringRunner {


    public static void main(String[] args) throws ZoneNotFoundException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        StartMenu startMenu = applicationContext.getBean(StartMenu.class);

        startMenu.processMenu();
    }
}
