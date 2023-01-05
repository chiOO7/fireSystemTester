package ru.chislab.fireSystemTester.chapters;

import ru.chislab.fireSystemTester.ModbusDataSource;

public class ChapterManager {

    private static final int CHAPTERS_COUNT = 64;

    private static Chapter[] chapters = new Chapter[CHAPTERS_COUNT];
    private ModbusDataSource modbusDataSource;

    public ChapterManager(ModbusDataSource modbusDataSource) {
        this.modbusDataSource = modbusDataSource;
    }



    public static Chapter getChapterByNumber(int number) {
        return chapters[number - 1];
    }

    public void defineChaptersID() {
        long[] chaptersIDs = modbusDataSource.getModbusChaptersIDs();
        for (int i = 0; i < chaptersIDs.length; i++) {
            chapters[i].getConfiguration().setChapterID(chaptersIDs[i]);
        }
    }
}
