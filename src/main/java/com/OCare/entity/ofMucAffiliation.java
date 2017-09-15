package com.OCare.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by fordevelopment on 15/11/18.
 */
@Entity
public class ofMucAffiliation {
    private long roomId;
    private String jid;
    private byte affiliation;
    private int relation;
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
    @Column(name = "affiliation", nullable = false, insertable = true, updatable = true)
    public byte getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(byte affiliation) {
        this.affiliation = affiliation;
    }

    @Basic
    @Column(name = "relation", nullable = false, insertable = true, updatable = true)
    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
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

        ofMucAffiliation that = (ofMucAffiliation) o;

        if (roomId != that.roomId) return false;
        if (affiliation != that.affiliation) return false;
        if (relation != that.relation) return false;
        if (id != that.id) return false;
        if (jid != null ? !jid.equals(that.jid) : that.jid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (roomId ^ (roomId >>> 32));
        result = 31 * result + (jid != null ? jid.hashCode() : 0);
        result = 31 * result + (int) affiliation;
        result = 31 * result + relation;
        result = 31 * result + id;
        return result;
    }
}
