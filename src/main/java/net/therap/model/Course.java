package net.therap.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sadia.afroz
 * @since 4/18/21
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@Size(min = 1, max = 6, message = "${Size.course.title}")
    private String title;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    Set<Trainee> trainees;

    public Course() {
        this.trainees = new HashSet<>();
    }

    public Course(int id, String title) {
        this.id = id;
        this.title = title;
        this.trainees = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(Set<Trainee> trainees) {
        this.trainees = trainees;
    }

    public void addTrainee(Trainee trainee) {
        boolean added = trainees.add(trainee);
        if (added) {
            trainee.getCourses().add(this);
        }
    }

    public void removeTrainee(Trainee trainee) {
        boolean removed = trainees.remove(trainee);
        if (removed) {
            trainee.getCourses().remove(this);
        }
    }

    public void removeCourseFromTrainees() {
        for (Trainee trainee : trainees) {
            trainee.getCourses().remove(this);
        }
    }

    public boolean isNew() {
        if (this.id == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
