package com.plter.jus.db.entities;

import javax.persistence.*;

/**
 * Created by plter on 6/23/15.
 */
@Entity
@Table(name = "funcship", schema = "", catalog = "jus")
public class FuncshipEntity {
    private long id;
    private String funcname;
    private long groupid;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "funcname", nullable = false, insertable = true, updatable = true, length = 256)
    public String getFuncname() {
        return funcname;
    }

    public void setFuncname(String funcname) {
        this.funcname = funcname;
    }

    @Basic
    @Column(name = "groupid", nullable = false, insertable = true, updatable = true)
    public long getGroupid() {
        return groupid;
    }

    public void setGroupid(long groupid) {
        this.groupid = groupid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FuncshipEntity that = (FuncshipEntity) o;

        if (id != that.id) return false;
        if (groupid != that.groupid) return false;
        if (funcname != null ? !funcname.equals(that.funcname) : that.funcname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (funcname != null ? funcname.hashCode() : 0);
        result = 31 * result + (int) (groupid ^ (groupid >>> 32));
        return result;
    }
}
