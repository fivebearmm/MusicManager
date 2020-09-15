package com.starnet.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_phone")
public class Phone {

    @Id
    @GeneratedValue
    private Long id;
    private String phonename;
    private String phonestatus;
    private String phonemd;

    @ManyToMany(mappedBy = "phones")
    private List<Linux> linuxs = new ArrayList<>();

    @ManyToMany(mappedBy = "phones2")
    private List<Music> music = new ArrayList<>();

    public Phone() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhonename() {
        return phonename;
    }

    public void setPhonename(String phonename) {
        this.phonename = phonename;
    }

    public String getPhonestatus() {
        return phonestatus;
    }

    public void setPhonestatus(String phonestatus) {
        this.phonestatus = phonestatus;
    }

    public String getPhonemd() {
        return phonemd;
    }

    public void setPhonemd(String phonemd) {
        this.phonemd = phonemd;
    }

    public List<Linux> getLinuxs() {
        return linuxs;
    }

    public void setLinuxs(List<Linux> linuxs) {
        this.linuxs = linuxs;
    }

    public List<Music> getMusic() {
        return music;
    }

    public void setMusic(List<Music> music) {
        this.music = music;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phonename='" + phonename + '\'' +
                ", phonestatus='" + phonestatus + '\'' +
                ", phonemd='" + phonemd + '\'' +
                '}';
    }
}
