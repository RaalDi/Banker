package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.Member;

@Repository("memberDAO")
public class MemberDAO extends ModelDAO<Member, Long> {

	private static final long serialVersionUID = 1L;

	public MemberDAO(Class<Member> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
