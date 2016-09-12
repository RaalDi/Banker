package com.raaldi.banker.repository;

import com.raaldi.banker.model.Member;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("memberDAO")
public final class MemberRepository extends AbstractModelRepository<Member, Long> {

  public MemberRepository(final Class<Member> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
