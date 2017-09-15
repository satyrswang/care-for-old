package com.OCare.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fordevelopment on 15/11/18.
 */
@Entity
public class Contract {
    private int id;
    private int company_id;
    private String elder_id;
    private Date start_time;
    private Date end_time;
    private int status;
    private String folder_name;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    @Column(name = "elder_id", nullable = false, insertable = true, updatable = true, length = 45)
    public String getElder_id() {
        return elder_id;
    }

    public void setElder_id(String elder_id) {
        this.elder_id = elder_id;
    }

    @Basic
    @Column(name = "start_time", nullable = false, insertable = true, updatable = true)
    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    @Basic
    @Column(name = "end_time", nullable = false, insertable = true, updatable = true)
    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
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
    @Column(name = "folder_name", nullable = false, insertable = true, updatable = true, length = 45)
    public String getFolder_name() {
        return folder_name;
    }

    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contract contract = (Contract) o;

        if (id != contract.id) return false;
        if (company_id != contract.company_id) return false;
        if (status != contract.status) return false;
        if (elder_id != null ? !elder_id.equals(contract.elder_id) : contract.elder_id != null) return false;
        if (start_time != null ? !start_time.equals(contract.start_time) : contract.start_time != null) return false;
        if (end_time != null ? !end_time.equals(contract.end_time) : contract.end_time != null) return false;
        if (folder_name != null ? !folder_name.equals(contract.folder_name) : contract.folder_name != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + company_id;
        result = 31 * result + (elder_id != null ? elder_id.hashCode() : 0);
        result = 31 * result + (start_time != null ? start_time.hashCode() : 0);
        result = 31 * result + (end_time != null ? end_time.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (folder_name != null ? folder_name.hashCode() : 0);
        return result;
    }


}
