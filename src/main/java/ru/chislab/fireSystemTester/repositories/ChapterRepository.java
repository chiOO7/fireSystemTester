package ru.chislab.fireSystemTester.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.chislab.fireSystemTester.chapters.Chapter;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
//    void saveAll(List<Chapter> chapters);

//    @Modifying
    @Query("select c from Chapter c")
    List<Chapter> getAll();

//    void merge(Chapter chapter);
}
