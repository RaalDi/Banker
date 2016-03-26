package com.raaldi.banker.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raaldi.banker.dao.MemberDAO;
import com.raaldi.banker.dao.ModelDAO;
import com.raaldi.banker.model.Member;

@Service("memberService")
@Transactional
public class MemberService implements ModelService<Member> {

	@PersistenceContext
	private EntityManager em;

	private ModelDAO<Member, Long> entityDAO;

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
