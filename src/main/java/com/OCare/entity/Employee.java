package com.OCare.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by fordevelopment on 15/11/18.
 */
@Entity
public class Employee {
    private String id;
    private int company_id;
    private String name;
    private String phone;
    private String address;
    private String department;
    private String position;
    private Date contract_start;
    private Date contract_end;
    private Integer status;
    private String password;
    private String image;
    private String superior;
    private String workExperience;
    private String workDetail;
    private Date lastUpdateTime;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 45)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "department", nullable = true, insertable = true, updatable = true, length = 45)
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Basic
    @Column(name = "position", nullable = true, insertable = true, updatable = true, length = 45)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "contract_start", nullable = true, insertable = true, updatable = true)
    public Date getContract_start() {
        return contract_start;
    }

    public void setContract_start(Date contract_start) {
        this.contract_start = contract_start;
    }

    @Basic
    @Column(name = "contract_end", nullable = true, insertable = true, updatable = true)
    public Date getContract_end() {
        return contract_end;
    }

    public void setContract_end(Date contract_end) {
        this.contract_end = contract_end;
    }

    @Basic
    @Column(name = "status", nullable = true, insertable = true, updatable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "password", nullable = true, insertable = true, updatable = true, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "image", nullable = true, insertable = true, updatable = true, length = 45)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "superior", nullable = true, insertable = true, updatable = true, length = 45)
    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }

    @Basic
    @Column(name = "workExperience", nullable = true, insertable = true, updatable = true, length = 255)
    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    @Basic
    @Column(name = "workDetail", nullable = true, insertable = true, updatable = true, length = 255)
    public String getWorkDetail() {
        return workDetail;
    }

    public void setWorkDetail(String workDetail) {
        this.workDetail = workDetail;
    }

    @Basic
    @Column(name = "lastUpdateTime", nullable = true, insertable = true, updatable = true)
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (company_id != employee.company_id) return false;
        if (id != null ? !id.equals(employee.id) : employee.id != null) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (phone != null ? !phone.equals(employee.phone) : employee.phone != null) return false;
        if (address != null ? !address.equals(employee.address) : employee.address != null) return false;
        if (department != null ? !department.equals(employee.department) : employee.department != null) return false;
        if (position != null ? !position.equals(employee.position) : employee.position != null) return false;
        if (contract_start != null ? !contract_start.equals(employee.contract_start) : employee.contract_start != null)
            return false;
        if (contract_end != null ? !contract_end.equals(employee.contract_end) : employee.contract_end != null)
            return false;
        if (status != null ? !status.equals(employee.status) : employee.status != null) return false;
        if (password != null ? !password.equals(employee.password) : employee.password != null) return false;
        if (image != null ? !image.equals(employee.image) : employee.image != null) return false;
        if (superior != null ? !superior.equals(employee.superior) : employee.superior != null) return false;
        if (workExperience != null ? !workExperience.equals(employee.workExperience) : employee.workExperience != null)
            return false;
        if (workDetail != null ? !workDetail.equals(employee.workDetail) : employee.workDetail != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(employee.lastUpdateTime) : employee.lastUpdateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + company_id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (contract_start != null ? contract_start.hashCode() : 0);
        result = 31 * result + (contract_end != null ? contract_end.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (superior != null ? superior.hashCode() : 0);
        result = 31 * result + (workExperience != null ? workExperience.hashCode() : 0);
        result = 31 * result + (workDetail != null ? workDetail.hashCode() : 0);
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        return result;
    }
}
