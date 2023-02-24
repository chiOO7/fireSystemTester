package ru.chislab.fireSystemTester.dao;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.chislab.fireSystemTester.chapters.Chapter;


import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Repository
public class ChapterDao {

//    private static final int CHAPTERS_COUNT = 64;

//    private final SessionFactory sessionFactory;

   // public final Chapter[] chapters = new Chapter[CHAPTERS_COUNT];

    public void saveChaptersToStorage(List<Chapter> chapters) {

//        Session session = sessionFactory.openSession();
//
//        session.beginTransaction();

//        for (Chapter chapter : chapters) {
//                session.merge(chapter);
//        }
//        session.getTransaction().commit();
    }


    public List<Chapter> getChaptersFromStorage() {
//        Session session = sessionFactory.openSession();
//
//        session.beginTransaction();
//
//        List<Chapter> chaptersFromDb = session.createQuery("select c from Chapter c join fetch c.zones z", Chapter.class).getResultList();
//
//        session.getTransaction().commit();
//
//        return chaptersFromDb;
        return new ArrayList<>();
    }

    public void updateChapter(Chapter chapter) {
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.merge(chapter);
//        session.getTransaction().commit();
    }
}
