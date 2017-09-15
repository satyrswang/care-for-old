package com.OCare.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by fordevelopment on 15/11/18.
 */
@Entity
public class AlarmHistory {
    private Integer talk_type;
    private Integer talk_cate;
    private String picture_url;
    private Integer direction;
    private Double who_loc_log;
    private Double who_loc_lat;
    private String who_address;
    private String talk_at;
    private Integer talk_alarm;
    private String who_privateto;
    private Integer talk_time;
    private String audio_url;
    private String messageid;
    private long generateid;
    private String messagefrom;
    private String messageto;
    private Timestamp time;

    @Basic
    @Column(name = "talk_type", nullable = true, insertable = true, updatable = true)
    public Integer getTalk_type() {
        return talk_type;
    }

    public void setTalk_type(Integer talk_type) {
        this.talk_type = talk_type;
    }

    @Basic
    @Column(name = "talk_cate", nullable = true, insertable = true, updatable = true)
    public Integer getTalk_cate() {
        return talk_cate;
    }

    public void setTalk_cate(Integer talk_cate) {
        this.talk_cate = talk_cate;
    }

    @Basic
    @Column(name = "picture_url", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    @Basic
    @Column(name = "direction", nullable = true, insertable = true, updatable = true)
    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    @Basic
    @Column(name = "who_loc_log", nullable = true, insertable = true, updatable = true, precision = 6)
    public Double getWho_loc_log() {
        return who_loc_log;
    }

    public void setWho_loc_log(Double who_loc_log) {
        this.who_loc_log = who_loc_log;
    }

    @Basic
    @Column(name = "who_loc_lat", nullable = true, insertable = true, updatable = true, precision = 6)
    public Double getWho_loc_lat() {
        return who_loc_lat;
    }

    public void setWho_loc_lat(Double who_loc_lat) {
        this.who_loc_lat = who_loc_lat;
    }

    @Basic
    @Column(name = "who_address", nullable = true, insertable = true, updatable = true, length = 16777215)
    public String getWho_address() {
        return who_address;
    }

    public void setWho_address(String who_address) {
        this.who_address = who_address;
    }

    @Basic
    @Column(name = "talk_at", nullable = true, insertable = true, updatable = true, length = 64)
    public String getTalk_at() {
        return talk_at;
    }

    public void setTalk_at(String talk_at) {
        this.talk_at = talk_at;
    }

    @Basic
    @Column(name = "talk_alarm", nullable = true, insertable = true, updatable = true)
    public Integer getTalk_alarm() {
        return talk_alarm;
    }

    public void setTalk_alarm(Integer talk_alarm) {
        this.talk_alarm = talk_alarm;
    }

    @Basic
    @Column(name = "who_privateto", nullable = true, insertable = true, updatable = true, length = 64)
    public String getWho_privateto() {
        return who_privateto;
    }

    public void setWho_privateto(String who_privateto) {
        this.who_privateto = who_privateto;
    }

    @Basic
    @Column(name = "talk_time", nullable = true, insertable = true, updatable = true)
    public Integer getTalk_time() {
        return talk_time;
    }

    public void setTalk_time(Integer talk_time) {
        this.talk_time = talk_time;
    }

    @Basic
    @Column(name = "audio_url", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }

    @Basic
    @Column(name = "messageid", nullable = false, insertable = true, updatable = true, length = 32)
    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    @Id
    @Column(name = "generateid", nullable = false, insertable = true, updatable = true)
    public long getGenerateid() {
        return generateid;
    }

    public void setGenerateid(long generateid) {
        this.generateid = generateid;
    }

    @Basic
    @Column(name = "messagefrom", nullable = true, insertable = true, updatable = true, length = 64)
    public String getMessagefrom() {
        return messagefrom;
    }

    public void setMessagefrom(String messagefrom) {
        this.messagefrom = messagefrom;
    }

    @Basic
    @Column(name = "messageto", nullable = true, insertable = true, updatable = true, length = 64)
    public String getMessageto() {
        return messageto;
    }

    public void setMessageto(String messageto) {
        this.messageto = messageto;
    }

    @Basic
    @Column(name = "time", nullable = true, insertable = true, updatable = true)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlarmHistory that = (AlarmHistory) o;

        if (generateid != that.generateid) return false;
        if (talk_type != null ? !talk_type.equals(that.talk_type) : that.talk_type != null) return false;
        if (talk_cate != null ? !talk_cate.equals(that.talk_cate) : that.talk_cate != null) return false;
        if (picture_url != null ? !picture_url.equals(that.picture_url) : that.picture_url != null) return false;
        if (direction != null ? !direction.equals(that.direction) : that.direction != null) return false;
        if (who_loc_log != null ? !who_loc_log.equals(that.who_loc_log) : that.who_loc_log != null) return false;
        if (who_loc_lat != null ? !who_loc_lat.equals(that.who_loc_lat) : that.who_loc_lat != null) return false;
        if (who_address != null ? !who_address.equals(that.who_address) : that.who_address != null) return false;
        if (talk_at != null ? !talk_at.equals(that.talk_at) : that.talk_at != null) return false;
        if (talk_alarm != null ? !talk_alarm.equals(that.talk_alarm) : that.talk_alarm != null) return false;
        if (who_privateto != null ? !who_privateto.equals(that.who_privateto) : that.who_privateto != null)
            return false;
        if (talk_time != null ? !talk_time.equals(that.talk_time) : that.talk_time != null) return false;
        if (audio_url != null ? !audio_url.equals(that.audio_url) : that.audio_url != null) return false;
        if (messageid != null ? !messageid.equals(that.messageid) : that.messageid != null) return false;
        if (messagefrom != null ? !messagefrom.equals(that.messagefrom) : that.messagefrom != null) return false;
        if (messageto != null ? !messageto.equals(that.messageto) : that.messageto != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = talk_type != null ? talk_type.hashCode() : 0;
        result = 31 * result + (talk_cate != null ? talk_cate.hashCode() : 0);
        result = 31 * result + (picture_url != null ? picture_url.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + (who_loc_log != null ? who_loc_log.hashCode() : 0);
        result = 31 * result + (who_loc_lat != null ? who_loc_lat.hashCode() : 0);
        result = 31 * result + (who_address != null ? who_address.hashCode() : 0);
        result = 31 * result + (talk_at != null ? talk_at.hashCode() : 0);
        result = 31 * result + (talk_alarm != null ? talk_alarm.hashCode() : 0);
        result = 31 * result + (who_privateto != null ? who_privateto.hashCode() : 0);
        result = 31 * result + (talk_time != null ? talk_time.hashCode() : 0);
        result = 31 * result + (audio_url != null ? audio_url.hashCode() : 0);
        result = 31 * result + (messageid != null ? messageid.hashCode() : 0);
        result = 31 * result + (int) (generateid ^ (generateid >>> 32));
        result = 31 * result + (messagefrom != null ? messagefrom.hashCode() : 0);
        result = 31 * result + (messageto != null ? messageto.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
