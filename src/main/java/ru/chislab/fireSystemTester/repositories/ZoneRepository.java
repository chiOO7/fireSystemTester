package ru.chislab.fireSystemTester.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.chislab.fireSystemTester.zones.Zone;



@Repository
public interface ZoneRepository extends JpaRepository<Zone, Integer> {
    @Modifying
    @Query("update Zone z set z.zoneName = ?2 where z.modbusZoneNumber = ?1")
    void update(Integer id, String zoneName);
}

