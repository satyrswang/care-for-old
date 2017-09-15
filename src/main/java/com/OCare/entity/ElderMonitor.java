package com.OCare.entity;

import org.codehaus.jackson.annotate.JsonBackReference;

import javax.persistence.*;

/**
 * Created by fordevelopment on 15/11/18.
 */
@Entity
public class ElderMonitor {
    private int id;
    private String elder_id;
    private String relative_id;
    private int type;
    private String togetherImg;
    private Elder elderByElderId;
    private Relative relativeByRelativeId;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "elder_id", nullable = false, insertable = true, updatable = true, length = 45)
    public String getElder_id() {
        return elder_id;
    }

    public void setElder_id(String elder_id) {
        this.elder_id = elder_id;
    }

    @Basic
    @Column(name = "relative_id", nullable = false, insertable = true, updatable = true, length = 45)
    public String getRelative_id() {
        return relative_id;
    }

    public void setRelative_id(String relative_id) {
        this.relative_id = relative_id;
    }

    @Basic
    @Column(name = "type", nullable = false, insertable = true, updatable = true)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "togetherImg", nullable = false, insertable = true, updatable = true, length = 45)
    public String getTogetherImg() {
        return togetherImg;
    }

    public void setTogetherImg(String togetherImg) {
        this.togetherImg = togetherImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElderMonitor that = (ElderMonitor) o;

        if (id != that.id) return false;
        if (type != that.type) return false;
        if (elder_id != null ? !elder_id.equals(that.elder_id) : that.elder_id != null) return false;
        if (relative_id != null ? !relative_id.equals(that.relative_id) : that.relative_id != null) return false;
        if (togetherImg != null ? !togetherImg.equals(that.togetherImg) : that.togetherImg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (elder_id != null ? elder_id.hashCode() : 0);
        result = 31 * result + (relative_id != null ? relative_id.hashCode() : 0);
        result = 31 * result + type;
        result = 31 * result + (togetherImg != null ? togetherImg.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "elder_id", referencedColumnName = "id",insertable = false,updatable = false)
    public Elder getElderByElderId() {
        return elderByElderId;
    }

    public void setElderByElderId(Elder elderByElderId) {
        this.elderByElderId = elderByElderId;
    }

    @ManyToOne
    @JoinColumn(name = "relative_id", referencedColumnName = "id",insertable = false,updatable = false)
    public Relative getRelativeByRelativeId() {
        return relativeByRelativeId;
    }

    public void setRelativeByRelativeId(Relative relativeByRelativeId) {
        this.relativeByRelativeId = relativeByRelativeId;
    }
}
