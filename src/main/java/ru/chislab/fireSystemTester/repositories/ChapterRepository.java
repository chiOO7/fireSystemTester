package ru.chislab.fireSystemTester.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.chislab.fireSystemTester.chapters.Chapter;


@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
    @Modifying
    @Query("update Chapter c set c.chapterName = ?2 where c.modbusChapterNumber = ?1")
    void update(Integer id, String chapterName);
}
