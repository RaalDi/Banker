package com.raaldi.banker.service;

import com.raaldi.banker.model.Payment;
import com.raaldi.banker.repository.AbstractModelRepository;
import com.raaldi.banker.repository.PaymentRepository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/** Payment service provides access to the payment repository. */
@NoArgsConstructor
@Service("paymentService")
@Transactional
public final class PaymentService implements ModelService<Payment> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The payment repository. */
  private AbstractModelRepository<Payment, Long> repository;

  /** Initializes the payment repository. */
  @PostConstruct
  public void postConstruct() {
    repository = new PaymentRepository(Payment.class, entityManager);
  }

  @Override
  public void save(final Payment model) {
    repository.save(model);
  }

  @Override
  public Payment findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public List<Payment> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Payment model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }

}
