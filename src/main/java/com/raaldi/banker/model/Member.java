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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Member")
@NamedQueries({@NamedQuery(name = "Member.findAll", query = "SELECT c FROM Member c")})
@Data
@EqualsAndHashCode(callSuper = true)
public final class Member extends AbstractModel {

    private static final long serialVersionUID = -5572520186586998185L;

    @Id
    @SequenceGenerator(name = "member-seq-gen", sequenceName = "member_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member-seq-gen")
    private long id;

    @NonNull
    @NotNull
    @Size(min = 1, max = 25, message = "1-25 letters and spaces")
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    @Column(name = "full_name")
    private String fullName;

    @NonNull
    @NotNull
    @Size(min = 10, max = 12, message = "10-12 Numbers")
    @Digits(fraction = 0, integer = 12, message = "Not valid")
    @Column(name = "phone_number")
    private String phoneNumber;
}
