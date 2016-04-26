package com.raaldi.banker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Role")
@NamedQueries({@NamedQuery(name = "Role.findAll", query = "SELECT c FROM Role c"),})
@Data
@EqualsAndHashCode(callSuper = false)
public final class Role extends Model {

    private static final long serialVersionUID = 2062680389137922067L;

    @Id
    @SequenceGenerator(name = "role-seq-gen", sequenceName = "role_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role-seq-gen")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
