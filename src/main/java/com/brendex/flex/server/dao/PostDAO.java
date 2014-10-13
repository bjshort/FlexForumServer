package com.brendex.flex.server.dao;

import com.brendex.flex.server.domains.Member;
import com.brendex.flex.server.domains.Post;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by brendanshort on 11/10/2014.
 */
public class PostDAO extends AbstractDAO<Post> {
    public PostDAO(SessionFactory factory) {
        super(factory);
    }

    public Post findById(Long id) {
        return get(id);
    }

    public long create(String message, Member member) {
        Post post = new Post(message, member);
        return persist(post).getId();
    }

    public Boolean deletePost(Long id){
        try {
            currentSession().beginTransaction();
            Post p = findById(id);
            currentSession().delete(p);
            currentSession().getTransaction().commit();
        } catch (HibernateException e){
            e.printStackTrace();
            currentSession().getTransaction().rollback();
            return false;
        }
        return true;
    }

    public List<Post> findAll() {
        return list(namedQuery("com.brendex.flex.server.Post.findAll"));
    }
}
