package com.raaldi.banker.model;

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
public abstract class Model implements Serializable {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", insertable = true, updatable = false)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", insertable = false, updatable = true)
	private Date updated;

	@NotNull
	@Column(name = "created_uid", insertable = true, updatable = false)
	private Long createdUid;
	
	@Column(name = "updated_uid", insertable = false, updatable = true)
	private Long updatedUid;
	
	@Version
	@Column(name = "optlock", nullable = false)
    private Long version = 0L;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		
		if(updatedUid == null){
			throw new NullPointerException("updatedUid may not be nulll when updating the Entity");
		}
		
		this.updated = updated;
	}

	public Long getCreatedUid() {
		return createdUid;
	}

	public void setCreatedUid(Long createdUid) {
		this.createdUid = createdUid;
	}

	public Long getUpdatedUid() {
		return updatedUid;
	}

	public void setUpdatedUid(Long updatedUid) {
		this.updatedUid = updatedUid;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
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
