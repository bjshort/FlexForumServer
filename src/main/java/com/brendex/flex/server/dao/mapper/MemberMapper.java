package com.brendex.flex.server.dao.mapper;

import com.brendex.flex.server.domains.Member;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberMapper implements ResultSetMapper<Member> {
    public Member map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Member(r.getInt("id"), r.getString("firstName"), r.getString("lastName"), r.getString("phone"));
    }
}
