package com.brendex.flex.server;

import com.brendex.flex.server.domains.Member;

import java.util.List;

/**
 * Created by brendanshort on 20/10/14.
 */
public class ApiResponse {

    private List<Member> members;
    //Getter and Setter
    //Public Constructor
    public ApiResponse(List<Member> m){
        this.members = m;
    }

    public List<Member> getMembers(){
        return members;
    }
}
