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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Play.findAll", query = "SELECT c FROM Play c"), })
@Data
@EqualsAndHashCode(callSuper = false)
public class Play extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "play-seq-gen", sequenceName = "play_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "play-seq-gen")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "short_name", nullable = false, unique = true)
    private String shortName;

    @NotNull
    @Column(name = "active")
    private boolean active = true;

}
