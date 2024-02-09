package aed.rest.RestSpring.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "genres", schema = "pitchfork", catalog = "")
public class GenresEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "genreid")
    private int genreid;
    @Basic
    @Column(name = "reviewid")
    private Integer reviewid;
    @Basic
    @Column(name = "genre")
    private String genre;

    public int getGenreid() {
        return genreid;
    }

    public void setGenreid(int genreid) {
        this.genreid = genreid;
    }

    public Integer getReviewid() {
        return reviewid;
    }

    public void setReviewid(Integer reviewid) {
        this.reviewid = reviewid;
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
        return genreid == that.genreid && Objects.equals(reviewid, that.reviewid) && Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreid, reviewid, genre);
    }
}
