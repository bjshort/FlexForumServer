package com.brendex.flex.server.resources;
import com.brendex.flex.server.dao.MemberDAO;
import com.brendex.flex.server.domains.Member;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * Created by brendanshort on 04/10/2014.
 */

@Path("/members")
@Produces(MediaType.APPLICATION_JSON)
public class MembersResource {

    private final MemberDAO memberDAO;

    public MembersResource(DBI jdbi) {
        memberDAO = jdbi.onDemand(MemberDAO.class);
    }

    @GET
    @Path("/{id}")
    public Response getMember(@PathParam("id") int id){
        Member member = memberDAO.findMemberById(id);

        if(member != null){
            return Response
                    .ok(member)
                    .build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    public Response createMember(
            @FormParam("name") String name,
            @FormParam("phone") String phone) {
        // store the new contact
        // ...
        return Response
                .created(null)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMember(@PathParam("id") int id) {
        // delete the contact with the provided id
        // ...
        return Response
                .noContent()
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateMember(
            @PathParam("id") int id,
            @FormParam("name") String name,
            @FormParam("phone") String phone) {
        // update the contact with the provided ID
        // ...
        return Response
                .ok("{Member_id: "+ id +", name: \""+ name +"\",phone: \""+ phone +"\" }")
                .build();
    }

}
