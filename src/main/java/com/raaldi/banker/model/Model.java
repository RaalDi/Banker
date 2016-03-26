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

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper=false)
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
	
	public void setUpdated(Date updated) {
		
		if(updatedUid == null){
			throw new NullPointerException("updatedUid may not be nulll when updating the Entity");
		}
		
		this.updated = updated;
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
