package com.brendex.flex.server.resources;
import com.brendex.flex.server.App;
import com.brendex.flex.server.dao.MemberDAO;
import com.brendex.flex.server.domains.Member;
import com.codahale.metrics.annotation.Timed;
import com.sun.jersey.api.Responses;
import io.dropwizard.hibernate.UnitOfWork;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;


@Path("/members")
@Produces(MediaType.APPLICATION_JSON)
public class MembersResource {

    private final MemberDAO memberDAO;
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public MembersResource(MemberDAO dao) {
        memberDAO = dao;
    }

    @GET
    @UnitOfWork
    @Path("/{id}")
    public Response getMember(@PathParam("id") long id){
        System.out.println("-- Finding with id: " + id);
        Member member = memberDAO.findById(id);
        System.out.println("-- Memmber found : " + member);
        if(member != null){
            return Response
                    .ok(member)
                    .build();
        } else {
            return Response.status(Responses.NOT_FOUND).build();
        }
    }

    @POST
    @Timed
    @UnitOfWork
    public Response createMember(Member member) throws URISyntaxException {
        try {
            long newContactId = memberDAO.create(member);
            return Response.created(new URI(String.valueOf(newContactId))).build();
        } catch (Exception e){
            LOGGER.error("There was an error adding that member: " + e.getMessage());
            return Response.serverError().build();
        }
    }

    @DELETE
    @UnitOfWork
    @Path("/{id}")
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
