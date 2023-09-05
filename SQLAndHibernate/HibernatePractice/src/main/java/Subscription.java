import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "subscriptions")
public class Subscription implements Serializable {

    @EmbeddedId
    private SubscriptionKey id;

    @Column(name = "student_id", nullable = false, insertable=false, updatable=false)
    private int studentId;

    @Column(name = "course_id", nullable = false, insertable=false, updatable=false)
    private int courseId;

    @Column(name = "subscription_date", nullable = false)
    private LocalDateTime subscriptionDate;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)
    private Student studentByStudentId;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)
    private Course courseByCourseId;

    public SubscriptionKey getId() {
        return id;
    }

    public void setId(SubscriptionKey id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public LocalDateTime getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDateTime subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Student getStudentsByStudentId() {
        return studentByStudentId;
    }

    public void setStudentsByStudentId(Student studentByStudentId) {
        this.studentByStudentId = studentByStudentId;
    }

    public Course getCoursesByCourseId() {
        return courseByCourseId;
    }

    public void setCoursesByCourseId(Course courseByCourseId) {
        this.courseByCourseId = courseByCourseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return studentId == that.studentId && courseId == that.courseId && Objects.equals(id, that.id) && Objects.equals(subscriptionDate, that.subscriptionDate) && Objects.equals(studentByStudentId, that.studentByStudentId) && Objects.equals(courseByCourseId, that.courseByCourseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, courseId, subscriptionDate, studentByStudentId, courseByCourseId);
    }

    @Override
    public String toString() {
        return "Subscriptions{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", subscriptionDate=" + subscriptionDate +
                ", studentsByStudentId=" + studentByStudentId +
                ", coursesByCourseId=" + courseByCourseId +
                '}';
    }
}
