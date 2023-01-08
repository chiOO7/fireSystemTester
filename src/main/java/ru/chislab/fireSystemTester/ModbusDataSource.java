package ru.chislab.fireSystemTester;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.serial.SerialParameters;
import com.intelligt.modbus.jlibmodbus.serial.SerialPort;
import com.intelligt.modbus.jlibmodbus.serial.SerialPortException;
import ru.chislab.fireSystemTester.chapters.ChapterManager;
import ru.chislab.fireSystemTester.enums.Events;
import ru.chislab.fireSystemTester.enums.ZoneTypes;
import ru.chislab.fireSystemTester.zones.Zone;
import ru.chislab.fireSystemTester.zones.ZoneConfiguration;
import ru.chislab.fireSystemTester.zones.ZoneManager;
import ru.chislab.fireSystemTester.zones.ZoneState;

import java.util.ArrayList;
import java.util.List;

public class ModbusDataSource {

    public long[] getModbusChaptersIDs() {
        return null;
    }


    public List<ZoneConfiguration> getModbusZoneConfigurations() {
        SerialParameters serialParameters = new SerialParameters();
        serialParameters.setDevice("COM1");
        serialParameters.setBaudRate(SerialPort.BaudRate.BAUD_RATE_9600);
        serialParameters.setDataBits(8);
        serialParameters.setParity(SerialPort.Parity.NONE);
        serialParameters.setStopBits(1);
        List<ZoneConfiguration> zoneConfigurations = new ArrayList<>();
        try {
            ModbusMaster m = ModbusMasterFactory.createModbusMasterRTU(serialParameters);
            m.connect();

            int slaveId = 1;
            int offset = 0;
            int quantity = 4;

            ZoneConfiguration zoneConfiguration = new ZoneConfiguration();
            try {
                int[] registerValues = m.readInputRegisters(slaveId, offset, quantity);
                zoneConfiguration.setModbusZoneNumber(offset / 4 + 1);
                zoneConfiguration.setDeviceAddress(registerValues[0]);
                zoneConfiguration.setSignalLineNumber(registerValues[1]);
                zoneConfiguration.setModbusChapterNumber(registerValues[2]);
                zoneConfiguration.setZoneType(ZoneTypes.values()[registerValues[3]]);
                zoneConfigurations.add(zoneConfiguration);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    m.disconnect();
                } catch (ModbusIOException e1) {
                    e1.printStackTrace();
                }
            }

        } catch (SerialPortException e) {
            throw new RuntimeException(e);
        } catch (ModbusIOException e) {
            throw new RuntimeException(e);
        }

        return zoneConfigurations;
    }

    //Mock
    public ZoneState getZoneStateByModbusZoneNumber(int number) {
        ZoneState state = new ZoneState();
        List<Events> events = new ArrayList<>();
        events.add(Events.FIRE);
        events.add(Events.ACTUATOR_FAILURE);
        state.setState(events);

        return state;
    }

    public Integer getNumberOfNewestOrionEvent() {
        return null;
    }

    public Integer getNumberOfEldestOrionEvent() {
        return null;
    }

    public Integer getCountOfUnreadOrionEvent() {
        return null;
    }

    public void setOrionEventWasRead() {

    }

    public void cleanOrionEventBuffer() {

    }

    public OrionEvent getEldestUnreadOrionEvent() {
        return null;
    }

    public OrionEvent getOrionEventByNumber(int number) {
        return null;
    }

    public List<ZoneState> getZonesStates() {
        return null;
    }

    public List<ZoneConfiguration> getZonesConfigurations() {
        return null;
    }

}
