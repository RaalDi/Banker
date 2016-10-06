package com.raaldi.banker.model;

import com.raaldi.banker.util.model.AbstractModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "RolePermission")
@Table(name = "role_permission", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "role_id",
    "permission_id" }))
@NamedQueries({ @NamedQuery(name = "RolePermission.findAll", query = "SELECT c FROM RolePermission c") })
@Data
@EqualsAndHashCode(callSuper = true)
public class RolePermission extends AbstractModel {

  private static final long serialVersionUID = -91786390688045690L;

  @Id
  @SequenceGenerator(name = "role-permission-seq-gen", sequenceName = "role_permission_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role-permission-seq-gen")
  private long id;

  @NonNull
  @NotNull
  @OneToOne
  @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
  private User user;

  @NonNull
  @NotNull
  @OneToOne
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  @NonNull
  @NotNull
  @OneToOne
  @JoinColumn(name = "permission_id", nullable = false)
  private Permission permission;

}
