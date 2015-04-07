package com.brendex.flex.server.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "members_id_seq1")
    @SequenceGenerator(name="members_id_seq1", sequenceName="members_id_seq1", allocationSize=1)
    private long id;

    @Column(name = "`firstName`")
    private String firstName;

    @Column(name = "`lastName`")
    private String lastName;

    private String phone;

    @Column(unique = true)
    private String email;

    private String password;

    @JsonIgnore
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private Set<Post> posts;

    public Member() {
        this.id = 0;
        this.status = "ACTIVE";
    }

    public Member(String firstName, String lastName, String phone, String email, String password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
