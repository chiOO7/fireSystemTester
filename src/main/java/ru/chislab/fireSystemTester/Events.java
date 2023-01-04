package ru.chislab.fireSystemTester;

public enum Events {
    EMPTY_EVENT,
    //1
    RESTORING_THE_220V_NETWORK,
    //2
    NETWORK_220V_FAILURE,
    //3
    INTRUSION_ALERT,
    //4
    HINDRANCE,
    //6
    HINDRANCE_ELIMINATED,
    //7
    MANUAL_ACTIVATION_OF_THE_ACTUATOR,
    //8
    MANUAL_DEACTIVATION_OF_THE_ACTUATOR,
    //9
    UDP_ACTIVATION,
    //10
    UDP_RESTORATION,
    //14
    CODE_SELECTION,
    //15
    DOOR_IS_OPEN,
    //17
    BAD_ARMING,
    //18
    ENFORCEMENT_CODE_PRESENTED,
    //19
    TEST,
    //20
    TEST_MODE_ENABLED,
    //21
    TEST_MODE_DISABLED,
    //22
    CONTROL_RESTORATION,
    //23
    ARMING_DELAY,
    //24
    ARMING_INPUT,
    //25
    ACCESS_CLOSED,
    //26
    ACCESS_DENIED,
    //27
    DOOR_IS_BROKEN,
    //28
    ACCESS_GRANTED,
    //29
    ACCESS_BANNED,
    //30
    ACCESS_RESTORATION,
    //31
    DOOR_IS_CLOSED,
    //32
    PASS,
    //33
    DOOR_IS_LOCKED,
    //34
    IDENTIFICATION,
    //35
    TECH_INPUT_RESTORATION,
    //36
    TECH_INPUT_VIOLATION,
    //37
    FIRE,
    //38
    TECH_INPUT_2_VIOLATION,
    //39
    NORM_OF_EQUIPMENT_RESTORATION,
    //40
    FIRE_2,
    //41
    EQUIPMENT_MALFUNCTION,
    //42
    UNKNOWN_DEVICE,
    //44
    ATTENTION,
    //45
    INPUT_BREAKAGE,
    //46
    BREAKAGE_OF_A_TWCL,
    //47
    RESTORATION_OF_A_TWCL,
    //58
    SILENT_ALARM,


}