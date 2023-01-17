package ru.chislab.fireSystemTester.testMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TestRunner {

    static Scanner scanner = new Scanner(System.in);
    static TestMenu menu;
    public static void main(String[] args) {

        initMenu();

        menu.processMenu();

        StartMenu startMenu = new StartMenu("s", scanner);

    }

    private static void initMenu() {
        for (int mN = 0; mN < 1; mN++) {
            List<TestAbstractMenu> subMenus = new ArrayList<>();
            for (int sMN = 0; sMN < 3; sMN++) {
                List<TestAbstractMenu> subSubMenus = new ArrayList<>();
                for (int sSMN = 0; sSMN < 3; sSMN++) {
                    List<String> punkts = new ArrayList<>();
                    for (int pN = 0; pN < 3; pN++) {
                        String punkt = "Состояние " + (pN + 1);
                        punkts.add(punkt);
                    }
                    TestSubSubMenu subSubMenu = new TestSubSubMenu(scanner, Collections.emptyList(), "Состояние зоны " + (sMN + 1) + "." + (sSMN + 1));
                    subSubMenu.setPunkts(punkts);
                    subSubMenus.add(subSubMenu);
                }
                TestAbstractMenu subMenu = new TestSubMenu(scanner, subSubMenus, "Зона " + (sMN + 1));
                subMenus.add(subMenu);
            }
            menu = new TestMenu(scanner, subMenus, "Раздел " + (mN + 1));
        }
    }

    private static void initMenu1() {
        for (int mN = 0; mN < 1; mN++) {
            List<TestAbstractMenu> subMenus = new ArrayList<>();
            for (int sMN = 0; sMN < 3; sMN++) {
                List<TestAbstractMenu> subSubMenus = new ArrayList<>();
                for (int sSMN = 0; sSMN < 3; sSMN++) {
                    List<String> punkts = new ArrayList<>();
                    for (int pN = 0; pN < 3; pN++) {
                        String punkt = "Состояние " + (pN + 1);
                        punkts.add(punkt);
                    }
                    TestSubSubMenu subSubMenu = new TestSubSubMenu(scanner, Collections.emptyList(), "Состояние зоны " + (sMN + 1) + "." + (sSMN + 1));
                    subSubMenu.setPunkts(punkts);
                    subSubMenus.add(subSubMenu);
                }
                TestAbstractMenu subMenu = new TestSubMenu(scanner, subSubMenus, "Зона " + (sMN + 1));
                subMenus.add(subMenu);
            }
            menu = new TestMenu(scanner, subMenus, "Раздел " + (mN + 1));
        }
    }


}
