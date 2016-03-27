package com.raaldi.banker.dao;

import com.raaldi.banker.model.Member;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("memberDAO")
public class MemberDAO extends AbstractModelDao<Member, Long> {

    public MemberDAO(final Class<Member> domainClass, final EntityManager em) {
        super(domainClass, em);
    }
}
