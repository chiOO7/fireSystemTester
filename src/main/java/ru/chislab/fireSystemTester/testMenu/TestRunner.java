package ru.chislab.fireSystemTester.testMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestRunner {

    static TestMenu menu;
    public static void main(String[] args) {

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
                    TestSubSubMenu subSubMenu = new TestSubSubMenu(Collections.emptyList(), "Состояние зоны " + (sMN + 1) + "." + (sSMN + 1));
                    subSubMenu.setPunkts(punkts);
                    subSubMenus.add(subSubMenu);
                }
                TestAbstractMenu subMenu = new TestSubMenu(subSubMenus, "Зона " + (sMN + 1));
                subMenus.add(subMenu);
            }
            menu = new TestMenu(subMenus, "Раздел " + (mN + 1));
        }

        TestMenuManager testMenuManager = new TestMenuManager();
        int level = 0;
        while (level > -1) {
            level = testMenuManager.somthingWithMenu(menu);
        }

    }


}
