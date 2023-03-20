package ru.chislab.fireSystemTester;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.chislab.fireSystemTester.consoleUserInterfaces.ConsoleUIManager;
import ru.chislab.fireSystemTester.consoleUserInterfaces.StartMenu;
import ru.chislab.fireSystemTester.modbus.ModbusDataSource;

@SpringBootApplication
public class SpringBootRunner {

	private static final String PORT = "COM2";

	private static final int SLAVE_ID = 1;

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SpringBootRunner.class, args);

		ConsoleUIManager consoleUIManager = context.getBean(ConsoleUIManager.class);

		consoleUIManager.setContext(context);

		StartMenu startMenu = consoleUIManager.getStartMenu();

		startMenu.setConsoleUIManager(consoleUIManager);

		startMenu.processMenu();
	}

	@Bean
	public ModbusDataSource modbusDataSource() {
		return new ModbusDataSource(PORT, SLAVE_ID);
	}
}
