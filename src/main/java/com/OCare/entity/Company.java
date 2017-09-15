package com.OCare.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by fordevelopment on 15/11/18.
 */
@Entity
public class Company {
    private int id;
    private String name;
    private String legal_person_id;
    private String phone;
    private String address;


    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    private String url1;
    private String url2;

    /*
        status = 101 代表未审核
        status = 102 代表审核通过
        status = 103 代表审核通过
     */

    private int status;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    @Column(name = "legal_person_id", nullable = false, insertable = true, updatable = true, length = 45)
    public String getLegal_person_id() {
        return legal_person_id;
    }

    public void setLegal_person_id(String legal_person_id) {
        this.legal_person_id = legal_person_id;
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

        Company company = (Company) o;

        if (id != company.id) return false;
        if (status != company.status) return false;
        if (name != null ? !name.equals(company.name) : company.name != null) return false;
        if (legal_person_id != null ? !legal_person_id.equals(company.legal_person_id) : company.legal_person_id != null)
            return false;
        if (phone != null ? !phone.equals(company.phone) : company.phone != null) return false;
        if (address != null ? !address.equals(company.address) : company.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (legal_person_id != null ? legal_person_id.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }
}
