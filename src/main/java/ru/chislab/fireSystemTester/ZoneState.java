package ru.chislab.fireSystemTester;

public enum ZoneState {
    SIGNAL_LINE_DISARMING(109),
    SIGNAL_LINE_ARMING(24),
    AUTOMATIC_OFF(142),
    AUTOMATIC_ON(148),
    ASPT_START_RESET(143),
    ASPT_START(146),
    SIGNAL_LINE_CONTROL_OFF(112),
    SIGNAL_LINE_CONTROL_ON(111);

    private int stateCode;
    ZoneState(int stateCode) {
        this.stateCode = stateCode;
    }
}
