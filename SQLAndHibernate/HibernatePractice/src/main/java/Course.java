import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "courses")
public class Course implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = true, length = 500)
    private String name;

    @Column(name = "duration", nullable = true)
    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum ('DESIGN','PROGRAMMING','MARKETING','MANAGEMENT','BUSINESS')",
            name = "type", nullable = false)
    private CourseType type;

    @Column(name = "description", nullable = true, length = 500)
    private String description;

    @Column(name = "students_count", nullable = true)
    private int studentsCount;

    @Column(name = "price", nullable = true)
    private int price;

    @Column(name = "price_per_hour", nullable = true, precision = 0)
    private float pricePerHour;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Teacher teacher;

    @OneToMany(mappedBy = "courseByCourseId")
    private Collection<Subscription> subscriptionById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Collection<Subscription> getSubscriptionsById() {
        return subscriptionById;
    }

    public void setSubscriptionsById(Collection<Subscription> subscriptionById) {
        this.subscriptionById = subscriptionById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && duration == course.duration && studentsCount == course.studentsCount && price == course.price && Float.compare(pricePerHour, course.pricePerHour) == 0 && Objects.equals(name, course.name) && type == course.type && Objects.equals(description, course.description) && Objects.equals(teacher, course.teacher) && Objects.equals(subscriptionById, course.subscriptionById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration, type, description, studentsCount, price, pricePerHour, teacher, subscriptionById);
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", studentsCount=" + studentsCount +
                ", price=" + price +
                ", pricePerHour=" + pricePerHour +
                ", teacher=" + teacher +
                ", subscriptionsById=" + subscriptionById +
                '}';
    }

}
