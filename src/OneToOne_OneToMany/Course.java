package OneToOne_OneToMany;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course implements Serializable{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)  //One to many uni-directional
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    //constructors
    public Course() {
    }

    public Course(String title) {
        this.title = title;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course title: " +title;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    // adding Review to Reviews list
    public void addReview(Review review){
        if(review == null)
            throw new NullPointerException("Review = null");
        // if current Course has no Reviews
        if(reviews == null)
            reviews = new ArrayList<Review>();
        reviews.add(review);
    }
}
