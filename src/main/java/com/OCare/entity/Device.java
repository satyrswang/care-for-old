package com.OCare.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by fordevelopment on 15/11/18.
 */
@Entity
public class Device {
    private String DeviceId;
    private String DeviceName;
    private String CommunicationType;
    private String Description;
    private String Image;
    private String ElderId;
    private String QRCode;

    @Id
    @Column(name = "DeviceId", nullable = false, insertable = true, updatable = true, length = 55)
    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    @Basic
    @Column(name = "DeviceName", nullable = false, insertable = true, updatable = true, length = 45)
    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    @Basic
    @Column(name = "CommunicationType", nullable = false, insertable = true, updatable = true, length = 45)
    public String getCommunicationType() {
        return CommunicationType;
    }

    public void setCommunicationType(String communicationType) {
        CommunicationType = communicationType;
    }

    @Basic
    @Column(name = "Description", nullable = false, insertable = true, updatable = true, length = 45)
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Basic
    @Column(name = "Image", nullable = false, insertable = true, updatable = true, length = 45)
    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Basic
    @Column(name = "ElderId", nullable = false, insertable = true, updatable = true, length = 45)
    public String getElderId() {
        return ElderId;
    }

    public void setElderId(String elderId) {
        ElderId = elderId;
    }

    @Basic
    @Column(name = "QRCode", nullable = false, insertable = true, updatable = true, length = 45)
    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (DeviceId != null ? !DeviceId.equals(device.DeviceId) : device.DeviceId != null) return false;
        if (DeviceName != null ? !DeviceName.equals(device.DeviceName) : device.DeviceName != null) return false;
        if (CommunicationType != null ? !CommunicationType.equals(device.CommunicationType) : device.CommunicationType != null)
            return false;
        if (Description != null ? !Description.equals(device.Description) : device.Description != null) return false;
        if (Image != null ? !Image.equals(device.Image) : device.Image != null) return false;
        if (ElderId != null ? !ElderId.equals(device.ElderId) : device.ElderId != null) return false;
        if (QRCode != null ? !QRCode.equals(device.QRCode) : device.QRCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = DeviceId != null ? DeviceId.hashCode() : 0;
        result = 31 * result + (DeviceName != null ? DeviceName.hashCode() : 0);
        result = 31 * result + (CommunicationType != null ? CommunicationType.hashCode() : 0);
        result = 31 * result + (Description != null ? Description.hashCode() : 0);
        result = 31 * result + (Image != null ? Image.hashCode() : 0);
        result = 31 * result + (ElderId != null ? ElderId.hashCode() : 0);
        result = 31 * result + (QRCode != null ? QRCode.hashCode() : 0);
        return result;
    }
}
