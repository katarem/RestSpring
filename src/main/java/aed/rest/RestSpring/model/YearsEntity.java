package aed.rest.RestSpring.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "years", schema = "pitchfork", catalog = "")
public class YearsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "yearid")
    private int yearid;
    @Basic
    @Column(name = "reviewid")
    private Integer reviewid;
    @Basic
    @Column(name = "year")
    private String year;

    public int getYearid() {
        return yearid;
    }

    public void setYearid(int yearid) {
        this.yearid = yearid;
    }

    public Integer getReviewid() {
        return reviewid;
    }

    public void setReviewid(Integer reviewid) {
        this.reviewid = reviewid;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YearsEntity that = (YearsEntity) o;
        return yearid == that.yearid && Objects.equals(reviewid, that.reviewid) && Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yearid, reviewid, year);
    }
}
