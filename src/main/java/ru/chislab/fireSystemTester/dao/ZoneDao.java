package ru.chislab.fireSystemTester.dao;

import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.chislab.fireSystemTester.zones.Zone;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ZoneDao {

    private final SessionFactory sessionFactory;

//    private final List<Zone> zonesStorage;

//    public ZoneDao() {
//        zonesStorage = new ArrayList<>();
//    }

    public void saveZonesToStorage(List<Zone> zones) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        for (Zone zone : zones) {
            session.merge(zone);
        }
        session.getTransaction().commit();
    }

    public List<Zone> getZonesFromStorage() {
        Session session = sessionFactory.openSession();

        session.beginTransaction();



        List<Zone> zones = session.
                session.getTransaction().commit();
        return zonesStorage;
    }

}
