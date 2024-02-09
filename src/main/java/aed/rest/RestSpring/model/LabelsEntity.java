package aed.rest.RestSpring.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "labels", schema = "pitchfork", catalog = "")
public class LabelsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "labelid")
    private int labelid;
    @Basic
    @Column(name = "reviewid")
    private Integer reviewid;
    @Basic
    @Column(name = "label")
    private String label;

    public int getLabelid() {
        return labelid;
    }

    public void setLabelid(int labelid) {
        this.labelid = labelid;
    }

    public Integer getReviewid() {
        return reviewid;
    }

    public void setReviewid(Integer reviewid) {
        this.reviewid = reviewid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabelsEntity that = (LabelsEntity) o;
        return labelid == that.labelid && Objects.equals(reviewid, that.reviewid) && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labelid, reviewid, label);
    }
}
