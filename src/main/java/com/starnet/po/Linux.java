package com.starnet.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_linux")

public class Linux {

    @Id
    @GeneratedValue
    private Long id;
    private String linuxname;
    private String linuxstatus;
    private String linxad;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Phone> phones = new ArrayList<>();

    public Linux() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinuxname() {
        return linuxname;
    }

    public void setLinuxname(String linuxname) {
        this.linuxname = linuxname;
    }

    public String getLinuxstatus() {
        return linuxstatus;
    }

    public void setLinuxstatus(String linuxstatus) {
        this.linuxstatus = linuxstatus;
    }

    public String getLinxad() {
        return linxad;
    }

    public void setLinxad(String linxad) {
        this.linxad = linxad;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Linux{" +
                "id=" + id +
                ", linuxname='" + linuxname + '\'' +
                ", linuxstatus='" + linuxstatus + '\'' +
                ", linxad='" + linxad + '\'' +
                '}';
    }
}
