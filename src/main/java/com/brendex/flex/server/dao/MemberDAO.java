package com.brendex.flex.server.dao;

import com.brendex.flex.server.dao.mapper.MemberMapper;
import com.brendex.flex.server.domains.Member;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * Created by brendanshort on 05/10/2014.
 */
public interface MemberDAO {

//    @SqlUpdate("create table something (id int primary key, name varchar(100))")
//    void createSomethingTable();
//
//    @SqlUpdate("insert into something (id, name) values (:id, :name)")
//    void insert(@Bind("id") int id, @Bind("name") String name);

    @Mapper(MemberMapper.class)
    @SqlQuery("select * from members where id= :id")
    Member findMemberById(@Bind("id") int id);
}

