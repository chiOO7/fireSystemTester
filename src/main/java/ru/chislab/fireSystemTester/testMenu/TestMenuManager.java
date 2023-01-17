package ru.chislab.fireSystemTester.testMenu;

import java.util.Scanner;

public class TestMenuManager {
    static Scanner scanner = new Scanner(System.in);


   public int somthingWithMenu(TestAbstractMenu menu) {
//       System.out.println(menu.getLevel());
       menu.printMenus();
       int command = scanner.nextInt();
       if (command == 0) return menu.getLevel() - 1;
       menu.doSomething(command);
       if (menu.getMenuPunkts().isEmpty()) return 0;
       int level = somthingWithMenu(menu.getMenuPunkts().get(command - 1));
       return level;
   }

}
