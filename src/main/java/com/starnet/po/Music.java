package com.starnet.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_music")
public class Music {

    @Id
    @GeneratedValue
    private Long id;
    private String musicname;
    private String info;
    private String singer;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatetime;
    private String musicstaus;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Phone> phones2 = new ArrayList<>();


    public Music(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getMusicname() {
        return musicname;
    }

    public void setMusicname(String musicname) {
        this.musicname = musicname;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getMusicstaus() {
        return musicstaus;
    }

    public void setMusicstaus(String musicstaus) {
        this.musicstaus = musicstaus;
    }

    public List<Phone> getPhones2() {
        return phones2;
    }

    public void setPhones2(List<Phone> phones2) {
        this.phones2 = phones2;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", musicname='" + musicname + '\'' +
                ", info='" + info + '\'' +
                ", singer='" + singer + '\'' +
                ", updatetime=" + updatetime +
                ", musicstaus='" + musicstaus + '\'' +
                '}';
    }
}
