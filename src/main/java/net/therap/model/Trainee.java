package net.therap.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sadia.afroz
 * @since 4/18/21
 */
@Entity
@Table(name = "trainee")
public class Trainee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "enrollment_pair",
            joinColumns = @JoinColumn(name = "traineeId"),
            inverseJoinColumns = @JoinColumn(name = "courseId")
    )
    Set<Course> courses;

    public Trainee() {
        this.courses = new HashSet<>();
    }

    public Trainee(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.courses = new HashSet<>();
    }

    public Trainee(String name, String email) {
        this.name = name;
        this.email = email;
        this.courses = new HashSet<>();
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        boolean added = courses.add(course);
        if (added) {
            course.getTrainees().add(this);
        }
    }

    public void removeCourse(Course course) {
        boolean removed = courses.remove(course);
        if (removed) {
            course.getTrainees().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
