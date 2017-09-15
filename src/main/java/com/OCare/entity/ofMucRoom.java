package com.OCare.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by fordevelopment on 15/11/18.
 */
@Entity
public class ofMucRoom {
    private int serviceId;

    @Basic
    @javax.persistence.Column(name = "serviceID", nullable = false, insertable = true, updatable = true)
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    private int roomId;

    @Basic
    @javax.persistence.Column(name = "roomID", nullable = false, insertable = true, updatable = true)
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    private String creationDate;

    @Basic
    @javax.persistence.Column(name = "creationDate", nullable = false, insertable = true, updatable = true, length = 15)
    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    private String modificationDate;

    @Basic
    @javax.persistence.Column(name = "modificationDate", nullable = false, insertable = true, updatable = true, length = 15)
    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    private String name;

    @Basic
    @javax.persistence.Column(name = "name", nullable = false, insertable = true, updatable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String naturalName;

    @Basic
    @javax.persistence.Column(name = "naturalName", nullable = false, insertable = true, updatable = true, length = 255)
    public String getNaturalName() {
        return naturalName;
    }

    public void setNaturalName(String naturalName) {
        this.naturalName = naturalName;
    }

    private String description;

    @Basic
    @javax.persistence.Column(name = "description", nullable = true, insertable = true, updatable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String lockedDate;

    @Basic
    @javax.persistence.Column(name = "lockedDate", nullable = false, insertable = true, updatable = true, length = 15)
    public String getLockedDate() {
        return lockedDate;
    }

    public void setLockedDate(String lockedDate) {
        this.lockedDate = lockedDate;
    }

    private String emptyDate;

    @Basic
    @javax.persistence.Column(name = "emptyDate", nullable = true, insertable = true, updatable = true, length = 15)
    public String getEmptyDate() {
        return emptyDate;
    }

    public void setEmptyDate(String emptyDate) {
        this.emptyDate = emptyDate;
    }

    private byte canChangeSubject;

    @Basic
    @javax.persistence.Column(name = "canChangeSubject", nullable = false, insertable = true, updatable = true)
    public byte getCanChangeSubject() {
        return canChangeSubject;
    }

    public void setCanChangeSubject(byte canChangeSubject) {
        this.canChangeSubject = canChangeSubject;
    }

    private int maxUsers;

    @Basic
    @javax.persistence.Column(name = "maxUsers", nullable = false, insertable = true, updatable = true)
    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    private byte publicRoom;

    @Basic
    @javax.persistence.Column(name = "publicRoom", nullable = false, insertable = true, updatable = true)
    public byte getPublicRoom() {
        return publicRoom;
    }

    public void setPublicRoom(byte publicRoom) {
        this.publicRoom = publicRoom;
    }

    private byte moderated;

    @Basic
    @javax.persistence.Column(name = "moderated", nullable = false, insertable = true, updatable = true)
    public byte getModerated() {
        return moderated;
    }

    public void setModerated(byte moderated) {
        this.moderated = moderated;
    }

    private byte membersOnly;

    @Basic
    @javax.persistence.Column(name = "membersOnly", nullable = false, insertable = true, updatable = true)
    public byte getMembersOnly() {
        return membersOnly;
    }

    public void setMembersOnly(byte membersOnly) {
        this.membersOnly = membersOnly;
    }

    private byte canInvite;

    @Basic
    @javax.persistence.Column(name = "canInvite", nullable = false, insertable = true, updatable = true)
    public byte getCanInvite() {
        return canInvite;
    }

    public void setCanInvite(byte canInvite) {
        this.canInvite = canInvite;
    }

    private String roomPassword;

    @Basic
    @javax.persistence.Column(name = "roomPassword", nullable = true, insertable = true, updatable = true, length = 50)
    public String getRoomPassword() {
        return roomPassword;
    }

    public void setRoomPassword(String roomPassword) {
        this.roomPassword = roomPassword;
    }

    private byte canDiscoverJid;

    @Basic
    @javax.persistence.Column(name = "canDiscoverJID", nullable = false, insertable = true, updatable = true)
    public byte getCanDiscoverJid() {
        return canDiscoverJid;
    }

    public void setCanDiscoverJid(byte canDiscoverJid) {
        this.canDiscoverJid = canDiscoverJid;
    }

    private byte logEnabled;

    @Basic
    @javax.persistence.Column(name = "logEnabled", nullable = false, insertable = true, updatable = true)
    public byte getLogEnabled() {
        return logEnabled;
    }

    public void setLogEnabled(byte logEnabled) {
        this.logEnabled = logEnabled;
    }

    private String subject;

    @Basic
    @javax.persistence.Column(name = "subject", nullable = true, insertable = true, updatable = true, length = 100)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    private byte rolesToBroadcast;

    @Basic
    @javax.persistence.Column(name = "rolesToBroadcast", nullable = false, insertable = true, updatable = true)
    public byte getRolesToBroadcast() {
        return rolesToBroadcast;
    }

    public void setRolesToBroadcast(byte rolesToBroadcast) {
        this.rolesToBroadcast = rolesToBroadcast;
    }

    private byte useReservedNick;

    @Basic
    @javax.persistence.Column(name = "useReservedNick", nullable = false, insertable = true, updatable = true)
    public byte getUseReservedNick() {
        return useReservedNick;
    }

    public void setUseReservedNick(byte useReservedNick) {
        this.useReservedNick = useReservedNick;
    }

    private byte canChangeNick;

    @Basic
    @javax.persistence.Column(name = "canChangeNick", nullable = false, insertable = true, updatable = true)
    public byte getCanChangeNick() {
        return canChangeNick;
    }

    public void setCanChangeNick(byte canChangeNick) {
        this.canChangeNick = canChangeNick;
    }

    private byte canRegister;

    @Basic
    @javax.persistence.Column(name = "canRegister", nullable = false, insertable = true, updatable = true)
    public byte getCanRegister() {
        return canRegister;
    }

    public void setCanRegister(byte canRegister) {
        this.canRegister = canRegister;
    }

    private int isOCareRoom;

    @Basic
    @javax.persistence.Column(name = "isOCareRoom", nullable = false, insertable = true, updatable = true)
    public int getIsOCareRoom() {
        return isOCareRoom;
    }

    public void setIsOCareRoom(int isOCareRoom) {
        this.isOCareRoom = isOCareRoom;
    }

    private int id;

    @Id
    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true)
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

        ofMucRoom ofMucRoom = (com.OCare.entity.ofMucRoom) o;

        if (serviceId != ofMucRoom.serviceId) return false;
        if (roomId != ofMucRoom.roomId) return false;
        if (canChangeSubject != ofMucRoom.canChangeSubject) return false;
        if (maxUsers != ofMucRoom.maxUsers) return false;
        if (publicRoom != ofMucRoom.publicRoom) return false;
        if (moderated != ofMucRoom.moderated) return false;
        if (membersOnly != ofMucRoom.membersOnly) return false;
        if (canInvite != ofMucRoom.canInvite) return false;
        if (canDiscoverJid != ofMucRoom.canDiscoverJid) return false;
        if (logEnabled != ofMucRoom.logEnabled) return false;
        if (rolesToBroadcast != ofMucRoom.rolesToBroadcast) return false;
        if (useReservedNick != ofMucRoom.useReservedNick) return false;
        if (canChangeNick != ofMucRoom.canChangeNick) return false;
        if (canRegister != ofMucRoom.canRegister) return false;
        if (isOCareRoom != ofMucRoom.isOCareRoom) return false;
        if (id != ofMucRoom.id) return false;
        if (creationDate != null ? !creationDate.equals(ofMucRoom.creationDate) : ofMucRoom.creationDate != null)
            return false;
        if (modificationDate != null ? !modificationDate.equals(ofMucRoom.modificationDate) : ofMucRoom.modificationDate != null)
            return false;
        if (name != null ? !name.equals(ofMucRoom.name) : ofMucRoom.name != null) return false;
        if (naturalName != null ? !naturalName.equals(ofMucRoom.naturalName) : ofMucRoom.naturalName != null)
            return false;
        if (description != null ? !description.equals(ofMucRoom.description) : ofMucRoom.description != null)
            return false;
        if (lockedDate != null ? !lockedDate.equals(ofMucRoom.lockedDate) : ofMucRoom.lockedDate != null) return false;
        if (emptyDate != null ? !emptyDate.equals(ofMucRoom.emptyDate) : ofMucRoom.emptyDate != null) return false;
        if (roomPassword != null ? !roomPassword.equals(ofMucRoom.roomPassword) : ofMucRoom.roomPassword != null)
            return false;
        if (subject != null ? !subject.equals(ofMucRoom.subject) : ofMucRoom.subject != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (serviceId ^ (serviceId >>> 32));
        result = 31 * result + (int) (roomId ^ (roomId >>> 32));
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (naturalName != null ? naturalName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (lockedDate != null ? lockedDate.hashCode() : 0);
        result = 31 * result + (emptyDate != null ? emptyDate.hashCode() : 0);
        result = 31 * result + (int) canChangeSubject;
        result = 31 * result + maxUsers;
        result = 31 * result + (int) publicRoom;
        result = 31 * result + (int) moderated;
        result = 31 * result + (int) membersOnly;
        result = 31 * result + (int) canInvite;
        result = 31 * result + (roomPassword != null ? roomPassword.hashCode() : 0);
        result = 31 * result + (int) canDiscoverJid;
        result = 31 * result + (int) logEnabled;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (int) rolesToBroadcast;
        result = 31 * result + (int) useReservedNick;
        result = 31 * result + (int) canChangeNick;
        result = 31 * result + (int) canRegister;
        result = 31 * result + isOCareRoom;
        result = 31 * result + id;
        return result;
    }
}
