package com.raaldi.banker.repository;

import com.raaldi.banker.model.Member;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("memberRepository")
public interface MemberRepository extends CrudRepository<Member, Long> {
}
