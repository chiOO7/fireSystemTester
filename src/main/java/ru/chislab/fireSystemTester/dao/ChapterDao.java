package ru.chislab.fireSystemTester.dao;


import lombok.Data;
import ru.chislab.fireSystemTester.chapters.Chapter;

@Data

public class ChapterDao {

    private static final int CHAPTERS_COUNT = 64;

    public final Chapter[] chapters = new Chapter[CHAPTERS_COUNT];

    public void saveChaptersToStorage(Chapter[] chapters) {
        System.arraycopy(chapters, 0, this.chapters, 0, CHAPTERS_COUNT);
    }
}
