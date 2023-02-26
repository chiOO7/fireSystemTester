package ru.chislab.fireSystemTester.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.chislab.fireSystemTester.chapters.Chapter;
import ru.chislab.fireSystemTester.zones.Zone;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Integer> {
    @Modifying
    @Query("update Zone z set z.zoneName = ?1")
    void update(String zoneName);

//    @Query("select z from Zone z")
//    List<Zone> findAllById(Integer modbusChapterNumber);
}

