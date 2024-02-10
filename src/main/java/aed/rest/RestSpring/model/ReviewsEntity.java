package aed.rest.RestSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "reviews", schema = "pitchfork", catalog = "")
public class ReviewsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "reviewid")
    private Integer reviewid;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "artist")
    private String artist;
    @Basic
    @Column(name = "url")
    private String url;
    @Basic
    @Column(name = "score")
    private BigDecimal score;
    @Basic
    @Column(name = "best_new_music")
    private Byte bestNewMusic;
    @Basic
    @Column(name = "author")
    private String author;
    @Basic
    @Column(name = "author_type")
    private String authorType;
    @Basic
    @Column(name = "pub_date")
    private String pubDate;
    @Basic
    @Column(name = "pub_weekday")
    private Byte pubWeekday;
    @Basic
    @Column(name = "pub_day")
    private Byte pubDay;
    @Basic
    @Column(name = "pub_month")
    private Byte pubMonth;
    @Basic
    @Column(name = "pub_year")
    private Short pubYear;

    @OneToMany(mappedBy = "review")
    @Column(name = "genres")
    private List<GenresEntity> genres;

    public List<GenresEntity> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresEntity> genres) {
        this.genres = genres;
    }

    public Integer getReviewid() {
        return reviewid;
    }

    public void setReviewid(Integer reviewid) {
        this.reviewid = reviewid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Byte getBestNewMusic() {
        return bestNewMusic;
    }

    public void setBestNewMusic(Byte bestNewMusic) {
        this.bestNewMusic = bestNewMusic;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorType() {
        return authorType;
    }

    public void setAuthorType(String authorType) {
        this.authorType = authorType;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Byte getPubWeekday() {
        return pubWeekday;
    }

    public void setPubWeekday(Byte pubWeekday) {
        this.pubWeekday = pubWeekday;
    }

    public Byte getPubDay() {
        return pubDay;
    }

    public void setPubDay(Byte pubDay) {
        this.pubDay = pubDay;
    }

    public Byte getPubMonth() {
        return pubMonth;
    }

    public void setPubMonth(Byte pubMonth) {
        this.pubMonth = pubMonth;
    }

    public Short getPubYear() {
        return pubYear;
    }

    public void setPubYear(Short pubYear) {
        this.pubYear = pubYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewsEntity that = (ReviewsEntity) o;
        return Objects.equals(reviewid, that.reviewid) && Objects.equals(title, that.title) && Objects.equals(artist, that.artist) && Objects.equals(url, that.url) && Objects.equals(score, that.score) && Objects.equals(bestNewMusic, that.bestNewMusic) && Objects.equals(author, that.author) && Objects.equals(authorType, that.authorType) && Objects.equals(pubDate, that.pubDate) && Objects.equals(pubWeekday, that.pubWeekday) && Objects.equals(pubDay, that.pubDay) && Objects.equals(pubMonth, that.pubMonth) && Objects.equals(pubYear, that.pubYear) && Objects.equals(genres, that.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewid, title, artist, url, score, bestNewMusic, author, authorType, pubDate, pubWeekday, pubDay, pubMonth, pubYear, genres);
    }
}
