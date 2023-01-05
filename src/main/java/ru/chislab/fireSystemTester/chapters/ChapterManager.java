package ru.chislab.fireSystemTester.chapters;

import ru.chislab.fireSystemTester.ModbusDataSource;

public class ChapterManager {

    private static final int CHAPTERS_COUNT = 64;


    private Chapter[] chapters;
    private ModbusDataSource modbusDataSource;

    public ChapterManager(ModbusDataSource modbusDataSource) {

        this.modbusDataSource = modbusDataSource;
        chapters = new Chapter[CHAPTERS_COUNT];
    }



    public Chapter getChapterByNumber(int number) {
        return chapters[number - 1];
    }

    public Chapter[] getChapters() {return chapters;}

//    public void defineChaptersID() {
//        ChapterConfiguration[] configurations = modbusDataSource.getModbusChaptersConfigurations();
//        for (int i = 0; i < configurations.length; i++) {
//            Chapter chapter = new Chapter();
//            chapter.setConfiguration(configurations[i]);
//            chapters[i] = chapter;
//        }
//    }
}
