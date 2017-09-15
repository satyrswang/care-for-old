package com.OCare.entity;

import javax.persistence.*;

/**
 * Created by fordevelopment on 15/11/18.
 */
@Entity
public class Task {
    private int id;
    private String content;
    private Employee employeeByFrom;
    private Employee employeeByTo;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content", nullable = false, insertable = true, updatable = true, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (content != null ? !content.equals(task.content) : task.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "from", referencedColumnName = "id", nullable = false)
    public Employee getEmployeeByFrom() {
        return employeeByFrom;
    }

    public void setEmployeeByFrom(Employee employeeByFrom) {
        this.employeeByFrom = employeeByFrom;
    }

    @ManyToOne
    @JoinColumn(name = "to", referencedColumnName = "id", nullable = false)
    public Employee getEmployeeByTo() {
        return employeeByTo;
    }

    public void setEmployeeByTo(Employee employeeByTo) {
        this.employeeByTo = employeeByTo;
    }
}
