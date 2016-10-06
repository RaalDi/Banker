package com.raaldi.banker.service;

import com.raaldi.banker.model.Member;
import com.raaldi.banker.repository.MemberRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Member service provides access to the member repository. */
@NoArgsConstructor
@Service("memberService")
@Transactional
public class MemberService implements ModelService<Member> {

  /** The member repository. */
  private MemberRepository repository;

  @Autowired
  public void setRepository(final MemberRepository repository) {
    this.repository = repository;
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
  public Iterable<Member> findAll() {
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
