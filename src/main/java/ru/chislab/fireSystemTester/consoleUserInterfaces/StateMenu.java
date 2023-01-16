package ru.chislab.fireSystemTester.consoleUserInterfaces;

import lombok.Getter;
import ru.chislab.fireSystemTester.enums.Events;

import java.util.Objects;

@Getter
public class StateMenu extends ConsoleUIMenu{

    private int number;

    private Events state;
    public StateMenu(int number, Events state) {
        super("Состояние " + number);
        this.number = number;
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateMenu stateMenu = (StateMenu) o;
        return number == stateMenu.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

}
