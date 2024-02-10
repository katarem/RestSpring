package aed.rest.RestSpring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "genres", schema = "pitchfork", catalog = "")
public class GenresEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "genreid")
    private int genreid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reviewid")
    @JsonBackReference
    private ReviewsEntity review;

    @Basic
    @Column(name = "genre")

    private String genre;

    public ReviewsEntity getReview() {
        return review;
    }

    public void setReview(ReviewsEntity review) {
        this.review = review;
    }

    public int getGenreid() {
        return genreid;
    }

    public void setGenreid(int genreid) {
        this.genreid = genreid;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenresEntity that = (GenresEntity) o;
        return genreid == that.genreid && Objects.equals(review, that.review) && Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreid, review, genre);
    }
}
