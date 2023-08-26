import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry)
                .getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

//        ================= Transaction case ===================
//        Transaction transaction = session.beginTransaction();
//        Course course = session.get(Course.class, 1);
//        List<Student> studentList = course.getStudents();
//        studentList.forEach(s -> System.out.println(s.getName()));
//        transaction.commit();

        //HQL Experiments
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);

        //Equals simple SELECT * FROM Courses
        //query.select(root);
        //List<Course> courseList = session.createQuery(query).getResultList();
        //courseList.forEach(c -> System.out.println(c.getName() + " - " + c.getTeacher().getName()));

        //WHERE & ORDER BY
//        query.select(root)
//                .where(builder.greaterThan(root.<Integer>get("price"), 100_000))
//                .orderBy(builder.desc(root.get("price")));
//        List<Course> courseList = session.createQuery(query).setMaxResults(5).getResultList();
//        courseList.forEach(c -> System.out.println(c.getName() + " - " + c.getPrice()));

        String hql = "From " + Course.class.getSimpleName() + " Where price > 120000 order by price desc ";
        List<Course> courseList = session.createQuery(hql).getResultList();
        courseList.forEach(c -> System.out.println(c.getName() + " - " + c.getPrice()));
        sessionFactory.close();
    }
}