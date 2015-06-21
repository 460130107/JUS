package com.plter.jus.db.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by plter on 6/17/15.
 */
@Entity
@Table(name = "users", schema = "", catalog = "jus")
public class UsersEntity {
    private long id;
    private String name;
    private String pass;
    private String email;
    private String nicename;
    private String url;
    private int status;
    private Timestamp regtime;
    private Timestamp lastlogtime;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "pass", nullable = false, insertable = true, updatable = true, length = 64)
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Basic
    @Column(name = "email", nullable = false, insertable = true, updatable = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "nicename", nullable = true, insertable = true, updatable = true, length = 50)
    public String getNicename() {
        return nicename;
    }

    public void setNicename(String nicename) {
        this.nicename = nicename;
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
    @Column(name = "status", nullable = false, insertable = true, updatable = true)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "regtime", nullable = true, insertable = true, updatable = true)
    public Timestamp getRegtime() {
        return regtime;
    }

    public void setRegtime(Timestamp regtime) {
        this.regtime = regtime;
    }

    @Basic
    @Column(name = "lastlogtime", nullable = true, insertable = true, updatable = true)
    public Timestamp getLastlogtime() {
        return lastlogtime;
    }

    public void setLastlogtime(Timestamp lastlogtime) {
        this.lastlogtime = lastlogtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pass != null ? !pass.equals(that.pass) : that.pass != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (nicename != null ? !nicename.equals(that.nicename) : that.nicename != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (regtime != null ? !regtime.equals(that.regtime) : that.regtime != null) return false;
        if (lastlogtime != null ? !lastlogtime.equals(that.lastlogtime) : that.lastlogtime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (nicename != null ? nicename.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (regtime != null ? regtime.hashCode() : 0);
        result = 31 * result + (lastlogtime != null ? lastlogtime.hashCode() : 0);
        return result;
    }
}
