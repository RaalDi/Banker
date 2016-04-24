package com.raaldi.banker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@NamedQueries({@NamedQuery(name = "Permission.findAll", query = "SELECT c FROM Permission c"),})
@Data
@EqualsAndHashCode(callSuper = false)
public final class Permission extends Model {

    private static final long serialVersionUID = 638834021669541247L;

    @Id
    @SequenceGenerator(name = "permission-seq-gen", sequenceName = "permission_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission-seq-gen")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

}
