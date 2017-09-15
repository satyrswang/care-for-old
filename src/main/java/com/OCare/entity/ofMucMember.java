package com.OCare.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by fordevelopment on 15/11/18.
 */
@Entity
public class ofMucMember {
    private long roomId;
    private String jid;
    private String nickname;
    private String firstName;
    private String lastName;
    private String url;
    private String email;
    private String faqentry;
    private String relation;
    private String ofMucMembercol;
    private int id;

    @Basic
    @Column(name = "roomID", nullable = false, insertable = true, updatable = true)
    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "jid", nullable = false, insertable = true, updatable = true, length = 65535)
    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    @Basic
    @Column(name = "nickname", nullable = true, insertable = true, updatable = true, length = 255)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "firstName", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName", nullable = true, insertable = true, updatable = true, length = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "url", nullable = true, insertable = true, updatable = true, length = 100)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "email", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "faqentry", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFaqentry() {
        return faqentry;
    }

    public void setFaqentry(String faqentry) {
        this.faqentry = faqentry;
    }

    @Basic
    @Column(name = "relation", nullable = true, insertable = true, updatable = true, length = 45)
    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    @Basic
    @Column(name = "ofMucMembercol", nullable = true, insertable = true, updatable = true, length = 45)
    public String getOfMucMembercol() {
        return ofMucMembercol;
    }

    public void setOfMucMembercol(String ofMucMembercol) {
        this.ofMucMembercol = ofMucMembercol;
    }

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ofMucMember that = (ofMucMember) o;

        if (roomId != that.roomId) return false;
        if (id != that.id) return false;
        if (jid != null ? !jid.equals(that.jid) : that.jid != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (faqentry != null ? !faqentry.equals(that.faqentry) : that.faqentry != null) return false;
        if (relation != null ? !relation.equals(that.relation) : that.relation != null) return false;
        if (ofMucMembercol != null ? !ofMucMembercol.equals(that.ofMucMembercol) : that.ofMucMembercol != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (roomId ^ (roomId >>> 32));
        result = 31 * result + (jid != null ? jid.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (faqentry != null ? faqentry.hashCode() : 0);
        result = 31 * result + (relation != null ? relation.hashCode() : 0);
        result = 31 * result + (ofMucMembercol != null ? ofMucMembercol.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
