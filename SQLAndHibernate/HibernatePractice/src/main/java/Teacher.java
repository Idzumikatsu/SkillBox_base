import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "teachers")
public class Teacher implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;


    @Column(name = "name", nullable = false, length = 45)
    private String name;


    @Column(name = "salary", nullable = true)
    private int salary;

    @Column(name = "age", nullable = true)
    private int age;

    @OneToMany(mappedBy = "teacher")
    private Collection<Course> courseById;

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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Collection<Course> getCourseById() {
        return courseById;
    }

    public void setCourseById(Collection<Course> courseById) {
        this.courseById = courseById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return id == teacher.id && salary == teacher.salary && age == teacher.age && Objects.equals(name, teacher.name) && Objects.equals(courseById, teacher.courseById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary, age, courseById);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", courseById=" + courseById +
                '}';
    }
}
