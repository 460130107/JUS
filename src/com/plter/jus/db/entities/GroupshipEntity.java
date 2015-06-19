package com.plter.jus.db.entities;

import javax.persistence.*;

/**
 * Created by plter on 6/17/15.
 */
@Entity
@Table(name = "groupship", schema = "", catalog = "jus")
public class GroupshipEntity {
    private long id;
    private long groupid;
    private long userid;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "groupid", nullable = false, insertable = true, updatable = true)
    public long getGroupid() {
        return groupid;
    }

    public void setGroupid(long groupid) {
        this.groupid = groupid;
    }

    @Basic
    @Column(name = "userid", nullable = false, insertable = true, updatable = true)
    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupshipEntity that = (GroupshipEntity) o;

        if (id != that.id) return false;
        if (groupid != that.groupid) return false;
        if (userid != that.userid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (groupid ^ (groupid >>> 32));
        result = 31 * result + (int) (userid ^ (userid >>> 32));
        return result;
    }
}
