package com.example.haw_app.schnitzeljagd.http;


import java.io.Serializable;

public class Ziel implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Double longitude;
    private Double latitude;
    private String code;
    private Double radius;
    private Schnitzeljagd schnitzeljagdId;

    public Ziel() {
    }

    public Ziel(Integer id) {
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getRadius() {
        return radius;
    }
    
    public void setRadius(Double radius) {
        this.radius = radius;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Schnitzeljagd getSchnitzeljagdId() {
        return schnitzeljagdId;
    }

    public void setSchnitzeljagdId(Schnitzeljagd schnitzeljagdId) {
        this.schnitzeljagdId = schnitzeljagdId;
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
        if (!(object instanceof Ziel)) {
            return false;
        }
        Ziel other = (Ziel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.hawserver.schnitzeljagd.Ziel[ id=" + id + " ]";
    }
    
}