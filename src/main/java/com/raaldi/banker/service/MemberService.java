package com.raaldi.banker.service;

import com.raaldi.banker.dao.AbstractModelDao;
import com.raaldi.banker.dao.MemberDAO;
import com.raaldi.banker.model.Member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("memberService")
@Transactional
public final class MemberService implements ModelService<Member> {

  @PersistenceContext
  private EntityManager em;

  private AbstractModelDao<Member, Long> entityDAO;

  @PostConstruct
  public void postConstruct() {
    entityDAO = new MemberDAO(Member.class, em);
  }

  @Override
  public void save(Member model) {
    entityDAO.save(model);
  }

  @Override
  public Member findOne(Long id) {
    return entityDAO.findOne(id);
  }

  @Override
  public List<Member> findAll() {
    return entityDAO.findAll();
  }

  @Override
  public boolean exists(Member model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(Long id) {
    return entityDAO.exists(id);
  }

}
