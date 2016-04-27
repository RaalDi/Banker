package com.raaldi.banker.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@MappedSuperclass
@Data
public abstract class AbstractModel implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", insertable = true, updatable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", insertable = false, updatable = true)
    private Date updated;

    @NotNull
    @Column(name = "created_uid", insertable = true, updatable = false)
    private long createdUid;

    @Column(name = "updated_uid", insertable = false, updatable = true)
    private long updatedUid;

    @Version
    @Column(name = "optlock", nullable = false)
    private long version;

    public void setUpdated(final Date updated) {

        if (updatedUid == 0) {
            throw new IllegalArgumentException(
                    "updatedUid may not be zero(0) when updating the Entity");
        }

        this.updated = updated == null ? null : new Date(updated.getTime());
    }

    public Date getUpdated() {
        return updated == null ? null : new Date(updated.getTime());
    }

    public void setCreated(final Date created) {
        this.created = created == null ? null : new Date(created.getTime());
    }

    public Date getCreated() {
        return created == null ? null : new Date(created.getTime());
    }

    @PrePersist
    public void onPersist() {
        this.setCreated(new Date());
    }

    @PreUpdate
    public void onUpdate() {
        this.setUpdated(new Date());
    }

}
