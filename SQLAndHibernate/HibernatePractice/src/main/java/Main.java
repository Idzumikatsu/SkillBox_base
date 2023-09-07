import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<PurchaseList> list = session.createQuery("FROM " + PurchaseList.class.getSimpleName()).getResultList();

        Transaction tx = session.beginTransaction();

        for (PurchaseList elem: list){
            LinkedPurchaseList lpl = new LinkedPurchaseList();
            Student student = getStudentByName(elem.getStudentName(), session);
            Course course = getCourseByName(elem.getCourseName(), session);
            LinkedPurchaseListKey key = new LinkedPurchaseListKey();
            key.setStudentId(student.getId());
            key.setCourseId(course.getId());
            lpl.setId(key);
            lpl.setCourseId(key.getCourseId());
            lpl.setStudentId(key.getStudentId());
            session.merge(lpl);
        }

        tx.commit();
        session.close();
        HibernateUtil.shutdown();
    }

    public static Student getStudentByName(String name, Session session){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        query.select(root)
                .where(builder.equal(root.<String>get("name"),name));
        return session.createQuery(query).getSingleResult();
    }

    public static Course getCourseByName(String name, Session session){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);

        query.select(root)
                .where(builder.equal(root.<String>get("name"),name));
        return session.createQuery(query).getSingleResult();
    }




}