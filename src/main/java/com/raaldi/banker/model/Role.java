package com.raaldi.banker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

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
@NamedQueries({@NamedQuery(name = "Role.findAll", query = "SELECT c FROM Role c")})
@Data
@EqualsAndHashCode(callSuper = true)
public final class Role extends AbstractModel {

    private static final long serialVersionUID = 2062680389137922067L;

    @Id
    @SequenceGenerator(name = "role-seq-gen", sequenceName = "role_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role-seq-gen")
    private long id;

    @NonNull
    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
