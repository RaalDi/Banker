package com.raaldi.banker.dao;

import com.raaldi.banker.model.Member;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("memberDAO")
public class MemberDAO extends ModelDao<Member, Long> {

    private static final long serialVersionUID = 1L;

    public MemberDAO(Class<Member> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
