package com.OCare.entity;

import org.codehaus.jackson.annotate.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fordevelopment on 15/11/18.
 */
@Entity
public class ElderCondition {
    private int id;
    private int status;
    private double latitude;
    private double longtitude;
    private Date time;
    private Elder elderByElderId;
    private String elder_id;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "status", nullable = false, insertable = true, updatable = true)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "latitude", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "longtitude", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    @Basic
    @Column(name = "time", nullable = false, insertable = true, updatable = true)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }



    @Basic
    @Column(name = "elder_id", nullable = false, insertable = true, updatable = true, length = 45)
    public String getElder_id() {
        return elder_id;
    }

    public void setElder_id(String elder_id) {
        this.elder_id = elder_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElderCondition that = (ElderCondition) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        if (Double.compare(that.latitude, latitude) != 0) return false;
        if (Double.compare(that.longtitude, longtitude) != 0) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + status;
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longtitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "elder_id" , referencedColumnName = "id",insertable = false,updatable = false)
    public Elder getElderByElderId() {
        return elderByElderId;
    }

    public void setElderByElderId(Elder elderByElderId) {
        this.elderByElderId = elderByElderId;
    }


}
