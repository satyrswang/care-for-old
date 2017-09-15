package com.OCare.entity;

import javax.persistence.*;

/**
 * Created by Ma on 2015/12/8.
 */
@Entity
@Table(name = "ofProperty", schema = "", catalog = "openfire")
public class OfPropertyEntity {
    private String name;
    private String propValue;

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "propValue")
    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfPropertyEntity that = (OfPropertyEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (propValue != null ? !propValue.equals(that.propValue) : that.propValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (propValue != null ? propValue.hashCode() : 0);
        return result;
    }
}
