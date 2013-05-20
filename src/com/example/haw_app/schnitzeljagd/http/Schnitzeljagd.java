package com.example.haw_app.schnitzeljagd.http;

import java.io.Serializable;
import java.util.List;
public class Schnitzeljagd implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private List<Ziel> zielList;

    public Schnitzeljagd() {
    }

    public Schnitzeljagd(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ziel> getZielList() {
        return zielList;
    }

    public void setZielList(List<Ziel> zielList) {
        this.zielList = zielList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schnitzeljagd)) {
            return false;
        }
        Schnitzeljagd other = (Schnitzeljagd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.hawserver.schnitzeljagd.Schnitzeljagd[ id=" + id + " ]";
    }
    
}