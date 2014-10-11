package com.brendex.flex.server.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "members")
public class Member {
    @Id
    private final int id;

    @Column(name = "`firstName`")
    private final String firstName;

    @Column(name = "`lastName`")
    private final String lastName;

    private final String phone;

    private final String email;

    public Member() {
        this.id= 0;
        this.firstName = null;
        this.lastName = null;
        this.phone = null;
        this.email = null;
    }

    public Member(int id, String firstName, String lastName, String phone, String email) {
        this.id= id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
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
}
