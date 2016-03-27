package com.raaldi.banker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Shop.findAll", query = "SELECT c FROM Shop c"), })
@Data
@EqualsAndHashCode(callSuper = false)
public class Shop extends Model {

    private static final long serialVersionUID = -6096997265649544980L;

    @Id
    @SequenceGenerator(name = "shop-seq-gen", sequenceName = "shop_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shop-seq-gen")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "active")
    private boolean active = true;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<User> users;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<PlayOrder> playOrders;
}
