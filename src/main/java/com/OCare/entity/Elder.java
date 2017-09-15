package com.OCare.entity;

import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by fordevelopment on 15/11/18.
 */
@Entity
public class Elder {
    private String id;
    private Integer company_id;
    private String name;
    private String phone;
    private String address;
    private String password;
    private String image;
    private Collection<ElderMonitor> elderMonitorsById;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 45)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "company_id", nullable = true, insertable = true, updatable = true)
    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "phone", nullable = false, insertable = true, updatable = true, length = 45)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "address", nullable = false, insertable = true, updatable = true, length = 45)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "password", nullable = false, insertable = true, updatable = true, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "image", nullable = false, insertable = true, updatable = true, length = 45)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Elder elder = (Elder) o;

        if (id != null ? !id.equals(elder.id) : elder.id != null) return false;
        if (company_id != null ? !company_id.equals(elder.company_id) : elder.company_id != null) return false;
        if (name != null ? !name.equals(elder.name) : elder.name != null) return false;
        if (phone != null ? !phone.equals(elder.phone) : elder.phone != null) return false;
        if (address != null ? !address.equals(elder.address) : elder.address != null) return false;
        if (password != null ? !password.equals(elder.password) : elder.password != null) return false;
        if (image != null ? !image.equals(elder.image) : elder.image != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (company_id != null ? company_id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "elderByElderId")
    @JsonManagedReference
    public Collection<ElderMonitor> getElderMonitorsById() {
        return elderMonitorsById;
    }

    public void setElderMonitorsById(Collection<ElderMonitor> elderMonitorsById) {
        this.elderMonitorsById = elderMonitorsById;
    }
}
