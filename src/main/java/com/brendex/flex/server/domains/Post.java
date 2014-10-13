package com.brendex.flex.server.domains;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    private long id;

    private String message;

    private final Date dateCreated;

    @ManyToOne
    @JoinColumn(name="memberId")
    public Member member;

    public Post(){
        this.message = null;
        this.member = null;
        this.dateCreated = new Date();
    }

    public Post(String message, Member member){
        this.message = message;
        this.member = member;
        this.dateCreated = new Date();
    }

    public long getId() {
        return id;
    }
}
