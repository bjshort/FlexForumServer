package com.brendex.flex.server.resources;
import com.brendex.flex.server.dao.MemberDAO;
import com.brendex.flex.server.domains.Member;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;


@Path("/members")
@Produces(MediaType.APPLICATION_JSON)
public class MembersResource {

    private final MemberDAO memberDAO;

    public MembersResource(MemberDAO dao) {
        memberDAO = dao;
    }

    @GET
    @Path("/{id}")
    public Response getMember(@PathParam("id") long id){
        Member member = memberDAO.findById(id);

        if(member != null){
            return Response
                    .ok(member)
                    .build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Path("/create")
    @Timed
    @UnitOfWork
    public Response createMember(Member member) throws URISyntaxException {
        long newContactId = memberDAO.create(member);
        return Response.created(new URI(String.valueOf(newContactId))).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteContact(@PathParam("id") Long id) {
        // delete the contact with the provided id
        memberDAO.deleteMember(id);
        return Response.noContent().build();
    }

//    @PUT
//    @Path("/update/{id}")
//    public Response updateContact(@PathParam("id") int id, Member member) {
//        // update the contact with the provided ID
//        memberDAO.updateContact(id, member.getFirstName(),
//                member.getLastName(), member.getPhone());
//        return Response.ok(
//                new Member(id, member.getFirstName(), member.getLastName(),
//                        member.getPhone())).build();
//    }

}
