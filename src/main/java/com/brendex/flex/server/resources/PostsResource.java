package com.brendex.flex.server.resources;


import com.brendex.flex.server.dao.MemberDAO;
import com.brendex.flex.server.dao.PostDAO;
import com.brendex.flex.server.domains.Member;
import com.brendex.flex.server.domains.Post;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/posts")
@Produces(MediaType.APPLICATION_JSON)
public class PostsResource {
    private final PostDAO postDAO;

    public PostsResource(PostDAO dao) {
        postDAO = dao;
    }

    @GET
    @Path("/{id}")
    public Response getPost(@PathParam("id") long id){
        Post post = postDAO.findById(id);

        if(post != null){
            return Response
                    .ok(post)
                    .build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Timed
    @UnitOfWork
    public Response createPost(String message, Long memberId) throws URISyntaxException {
        Member member = new Member();
        long newContactId = postDAO.create(message, member);
        return Response.created(new URI(String.valueOf(newContactId))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") Long id) {
        // delete the contact with the provided id
        postDAO.deletePost(id);
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
