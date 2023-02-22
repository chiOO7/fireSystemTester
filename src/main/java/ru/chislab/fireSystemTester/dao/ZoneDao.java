package ru.chislab.fireSystemTester.dao;

//import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.chislab.fireSystemTester.zones.Zone;

//import java.util.List;

@RequiredArgsConstructor
public class ZoneDao {

    private final SessionFactory sessionFactory;

//    public void saveZonesToStorage(List<Zone> zones) {
//        Session session = sessionFactory.openSession();
//
//        session.beginTransaction();
//        for (Zone zone : zones) {
//            session.merge(zone);
//        }
//        session.getTransaction().commit();
//    }

//    public List<Zone> getZonesFromStorage() {
//        Session session = sessionFactory.openSession();
//
//        session.beginTransaction();
//
//        Query query = session.createQuery("select z from Zone z", Zone.class);
//
//        session.getTransaction().commit();
//
//        return query.getResultList();
//    }

    public void updateZone(Zone zone) {
        if (zone == null) return;

        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();

            session.beginTransaction();

            session.merge(zone);

            session.getTransaction().commit();
        }
    }
}
