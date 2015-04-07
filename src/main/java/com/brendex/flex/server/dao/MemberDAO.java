package com.brendex.flex.server.dao;

import com.brendex.flex.server.domains.Member;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class MemberDAO extends AbstractDAO<Member> {
    public MemberDAO(SessionFactory factory) {
        super(factory);
    }

    public Member findById(Long id) {
        return get(id);
    }

    public Member findByEmail(String email){
        Criteria c = currentSession().createCriteria(Member.class);
        c.add(Restrictions.eq("email", email).ignoreCase());
        return (Member) c.uniqueResult();
    }

    public long create(Member member) {
        return persist(member).getId();
    }

    public Boolean deleteMember(Long id){
        try {
            Member m = findById(id);
            currentSession().delete(m);
        } catch (HibernateException e){
            e.printStackTrace();
            currentSession().getTransaction().rollback();
            return false;
        }
        return true;
    }

    public List<Member> findAll() {
        Criteria c = currentSession().createCriteria(Member.class);
        return c.list();
    }
}

