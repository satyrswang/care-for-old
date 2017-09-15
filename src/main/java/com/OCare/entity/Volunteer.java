package com.OCare.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by fordevelopment on 15/11/18.
 */
@Entity
public class Volunteer {
    private String id;
    private String name;
    private String phone;
    private String address;
    private String email;
    private int company_id;
    private String elder_id;
    private String password;
    private String image;
    private int status;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 45)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "email", nullable = false, insertable = true, updatable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "company_id", nullable = false, insertable = true, updatable = true)
    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
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

    @Basic
    @Column(name = "status", nullable = false, insertable = true, updatable = true)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Volunteer volunteer = (Volunteer) o;

        if (company_id != volunteer.company_id) return false;
        if (status != volunteer.status) return false;
        if (id != null ? !id.equals(volunteer.id) : volunteer.id != null) return false;
        if (name != null ? !name.equals(volunteer.name) : volunteer.name != null) return false;
        if (phone != null ? !phone.equals(volunteer.phone) : volunteer.phone != null) return false;
        if (address != null ? !address.equals(volunteer.address) : volunteer.address != null) return false;
        if (email != null ? !email.equals(volunteer.email) : volunteer.email != null) return false;
        if (elder_id != null ? !elder_id.equals(volunteer.elder_id) : volunteer.elder_id != null) return false;
        if (password != null ? !password.equals(volunteer.password) : volunteer.password != null) return false;
        if (image != null ? !image.equals(volunteer.image) : volunteer.image != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + company_id;
        result = 31 * result + (elder_id != null ? elder_id.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }
}
