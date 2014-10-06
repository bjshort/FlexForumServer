package com.brendex.flex.server.dao;

import com.brendex.flex.server.domains.Member;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.util.List;

public class MemberDAO extends AbstractDAO<Member> {
    public MemberDAO(SessionFactory factory) {
        super(factory);
    }

    public Member findById(Long id) {
        return get(id);
    }

    public long create(Member member) {
        return persist(member).getId();
    }

    public Boolean deleteMember(Long id){
        try {
            currentSession().beginTransaction();
            Member m = findById(id);
            currentSession().delete(m);
            currentSession().getTransaction().commit();
        } catch (HibernateException e){
            e.printStackTrace();
            currentSession().getTransaction().rollback();
            return false;
        }
        return true;
    }

    public List<Member> findAll() {
        return list(namedQuery("com.example.helloworld.core.Person.findAll"));
    }
}

