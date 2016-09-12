package com.raaldi.banker.service;

import com.raaldi.banker.model.Member;
import com.raaldi.banker.repository.AbstractModelRepository;
import com.raaldi.banker.repository.MemberRepository;

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

/** Member service provides access to the member repository. */
@NoArgsConstructor
@Service("memberService")
@Transactional
public final class MemberService implements ModelService<Member> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The member repository. */
  private AbstractModelRepository<Member, Long> repository;

  /** Initializes the member repository. */
  @PostConstruct
  public void postConstruct() {
    repository = new MemberRepository(Member.class, entityManager);
  }

  @Override
  public void save(final Member model) {
    repository.save(model);
  }

  @Override
  public Member findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public List<Member> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Member model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }

}
