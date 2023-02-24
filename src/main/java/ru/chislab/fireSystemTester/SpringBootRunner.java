package ru.chislab.fireSystemTester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.chislab.fireSystemTester.consoleUserInterfaces.ConsoleUIManager;
import ru.chislab.fireSystemTester.consoleUserInterfaces.StartMenu;
import ru.chislab.fireSystemTester.exceptions.ZoneNotFoundException;
import ru.chislab.fireSystemTester.spring.configurations.ApplicationConfiguration;

@SpringBootApplication
@ComponentScan(basePackages = "ru.chislab.fireSystemTester")
public class SpringBootRunner implements CommandLineRunner {

	@Autowired
	private ConsoleUIManager consoleUIManager;

//	@Autowired
//	private ApplicationContextProvider applicationContextProvider;

	public static void main(String[] args) throws ZoneNotFoundException {

		SpringApplication.run(SpringBootRunner.class, args);

	}

	@Override
	public void run(String...args) throws Exception {

		StartMenu startMenu = consoleUIManager.getStartMenu();

		startMenu.processMenu();
	}
}
