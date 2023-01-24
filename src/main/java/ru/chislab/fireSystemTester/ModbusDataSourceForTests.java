package ru.chislab.fireSystemTester;

import com.intelligt.modbus.jlibmodbus.data.DataHolder;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.serial.SerialPortException;
import ru.chislab.fireSystemTester.enums.States;
import ru.chislab.fireSystemTester.enums.ZoneTypes;
import ru.chislab.fireSystemTester.zones.ZoneConfigurationDto;
import ru.chislab.fireSystemTester.zones.ZoneState;

import java.util.ArrayList;
import java.util.List;

public class ModbusDataSourceForTests extends ModbusDataSource {

    private final int ZONE_COUNT = 10;

    @Override
    public List<ZoneConfigurationDto> getModbusZoneConfigurations() {
        List<ZoneConfigurationDto> configurations = new ArrayList<>();

        List<ZoneState> states = new ArrayList<>();

        for (int i = 0; i < ZONE_COUNT / 2; i++) {
            ZoneConfigurationDto configuration = new ZoneConfigurationDto();
            configuration.setModbusZoneNumber(i + 1);
            configuration.setDeviceAddress(2);
            configuration.setSignalLineNumber(i + 1);
            configuration.setModbusChapterNumber(1);
            configuration.setZoneType(ZoneTypes.SIGNAL_LINE_STATE);
            configurations.add(configuration);

            States event1 = States.values()[i];
            States event2 = States.values()[i + 1];
            List<States> events = new ArrayList<>();
            events.add(event1);
            events.add(event2);
            ZoneState state = new ZoneState();
            state.setStates(events);
            states.add(state);
        }

        for (int i = ZONE_COUNT / 2; i < ZONE_COUNT; i++) {
            ZoneConfigurationDto configuration = new ZoneConfigurationDto();
            configuration.setModbusZoneNumber(i + 1);
            configuration.setDeviceAddress(3);
            configuration.setSignalLineNumber(i + 1);
            configuration.setModbusChapterNumber(2);
            configuration.setZoneType(ZoneTypes.SIGNAL_LINE_STATE);
            configurations.add(configuration);

            States event1 = States.values()[i];
            States event2 = States.values()[i + 1];
            List<States> events = new ArrayList<>();
            events.add(event1);
            events.add(event2);
            ZoneState state = new ZoneState();
            state.setStates(events);
            states.add(state);
        }

        return configurations;
    }

    @Override
    public ZoneState getZoneStateByModbusZoneNumber(int number) {
        ZoneState state = new ZoneState();

        States state1 = States.FIRE;
        States state2 = States.BAD_START;

        List<States> states = new ArrayList<>();
        states.add(state1);
        states.add(state2);

        state.setStates(states);

        return state;
    }
}
