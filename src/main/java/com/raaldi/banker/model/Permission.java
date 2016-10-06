package com.raaldi.banker.model;

import com.raaldi.banker.util.model.AbstractModel;

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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "permission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Permission")
@NamedQueries({ @NamedQuery(name = "Permission.findAll", query = "SELECT c FROM Permission c") })
@Data
@EqualsAndHashCode(callSuper = true)
public class Permission extends AbstractModel {

  private static final long serialVersionUID = 638834021669541247L;

  @Id
  @SequenceGenerator(name = "permission-seq-gen", sequenceName = "permission_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission-seq-gen")
  private long id;

  @NonNull
  @NotNull
  @Column(name = "name", nullable = false, unique = true)
  private String name;

}
