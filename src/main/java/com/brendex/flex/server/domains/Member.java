package com.brendex.flex.server.domains;

/**
 * Created by brendanshort on 04/10/2014.
 */
public class Member {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String phone;

    public Member() {
        this.id= 0;
        this.firstName = null;
        this.lastName = null;
        this.phone = null;
    }

    public Member(int id, String firstName, String lastName,String phone) {
        this.id= id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
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
