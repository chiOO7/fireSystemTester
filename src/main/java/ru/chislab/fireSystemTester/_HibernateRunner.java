package ru.chislab.fireSystemTester;

//import lombok.Cleanup;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class _HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();

        configuration.configure();

//        @Cleanup
//        SessionFactory sessionFactory = configuration.buildSessionFactory();

//        @Cleanup
//        Session session = sessionFactory.openSession();

//        session.beginTransaction();

//        List<Zone> zones = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            Zone zone = Zone.builder()
//                    .modbusZoneNumber(i + 1)
//                    .zoneState(new ZoneState(List.of(States.ARMING_INPUT, States.ACCESS_CLOSED)))
//                    .deviceAddress(1)
//                    .zoneName("test")
//                    .zoneType(ZoneTypes.SIGNAL_LINE_STATE)
//                    .signalLineNumber(i + 1)
//                    .build();
//
//            zone.setModbusChapterNumber((i < 6) ? 1 : 2);
//
//
//
//            zones.add(zone);
//        }
//
//        Chapter chapter = Chapter.builder()
//                .chapterName("test1")
//                .deviceType(DeviceType.SIGNAL_20M)
//                .deviceAddress(1)
//                .zones(zones.subList(0, 5))
//                .modbusChapterNumber(1)
//                .build();
//
//        Chapter chapter2 = Chapter.builder()
//                .chapterName("test2")
//                .deviceType(DeviceType.SIGNAL_20M)
//                .deviceAddress(1)
//                .zones(zones.subList(6, 10))
//                .modbusChapterNumber(2)
//                .build();

//        for (Zone zone: zones) {
//            session.merge(zone);
//        }

//        session.persist(chapter);
//        session.persist(chapter2);
//        session.getTransaction().commit();

//        session.beginTransaction();
//        Chapter chapter1 = session.get(Chapter.class, 1);
//        session.getTransaction().commit();
//        System.out.println();
    }
}
