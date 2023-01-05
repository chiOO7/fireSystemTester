package ru.chislab.fireSystemTester.coils;

import ru.chislab.fireSystemTester.coils.Coil;

import java.util.List;

public class CoilManager {

    private static List<Coil> coils;

    public Coil getCoilByNumber(int number) {
        return coils.get(number - 1);
    }
}
