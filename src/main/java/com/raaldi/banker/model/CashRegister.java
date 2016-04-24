package com.raaldi.banker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.raaldi.banker.util.CashRegisterState;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cash_register")
@NamedQueries({@NamedQuery(name = "CashRegister.findAll", query = "SELECT c FROM CashRegister c"),})
@Data
@EqualsAndHashCode(callSuper = false)
public final class CashRegister extends Model {

    private static final long serialVersionUID = 7871256452762586374L;

    @Id
    @SequenceGenerator(name = "cash-register-seq-gen", sequenceName = "cash_register_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cash-register-seq-gen")
    private Long id;

    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "session_id", nullable = false, insertable = true, updatable = false)
    private Session session;

    @Enumerated(EnumType.STRING)
    private CashRegisterState state;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "opened", insertable = false, updatable = true)
    private Date opened;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "closed", insertable = false, updatable = true)
    private Date closed;

    @Column(name = "opened_amount", insertable = false, updatable = true)
    private BigDecimal openedAmount;

    @Column(name = "closed_amount", insertable = false, updatable = true)
    private BigDecimal closedAmount;

    public void setState(final CashRegisterState state) {
        switch (state) {
            case OPENING:
                this.setCreated(new Date());
                break;
            case OPENED:
                this.setOpened(new Date());
                break;
            case CLOSING:
                this.setUpdated(new Date());
                break;
            case CLOSED:
                this.setClosed(new Date());
                break;
        }

        this.state = state;
    }

    public void setOpened(final Date opened) {

        if (openedAmount == null) {
            throw new IllegalArgumentException(
                    "CashRegister.openedAmount may not be null when opening the cash register");
        }

        this.opened = opened != null ? new Date(opened.getTime()) : null;
    }

    public Date getOpened() {
        return opened != null ? new Date(opened.getTime()) : null;
    }

    public void setClosed(final Date closed) {

        if (this.closedAmount == null) {
            throw new IllegalArgumentException(
                    "CashRegister.closedAmount may not be null when closing the cash register");
        }

        this.closed = closed != null ? new Date(closed.getTime()) : null;
    }

    public Date getClosed() {
        return closed != null ? new Date(closed.getTime()) : null;
    }

    @Override
    @PrePersist
    public void onPersist() {
        this.setState(CashRegisterState.OPENING);
    }
}
