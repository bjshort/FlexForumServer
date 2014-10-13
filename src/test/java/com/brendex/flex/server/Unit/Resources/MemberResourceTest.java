package com.brendex.flex.server.Unit.Resources;
import com.brendex.flex.server.App;
import com.brendex.flex.server.ServerConfiguration;
import com.brendex.flex.server.dao.MemberDAO;
import com.brendex.flex.server.domains.Member;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;

import static org.fest.assertions.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

public class MemberResourceTest {

    private static final MemberDAO memberDAO = mock(MemberDAO.class);

    @ClassRule
    public static final DropwizardAppRule<ServerConfiguration> RULE =
            new DropwizardAppRule<ServerConfiguration>(App.class, "config.yaml");

    private final Member testMember = new Member("Bobby", "Latham", "000", "bob@latham.com", "bobby");
    private Client client;

    @Before
    public void setup() {
        when(memberDAO.findById(anyLong())).thenReturn(testMember);
        reset(memberDAO);

        client = new Client();
    }

    @Test
    public void testCreateAndRetrieveMember() {
        WebResource contactResource = client.resource("http://localhost:8080/members");

        String createdLocation = createTestMember(contactResource);
        retrieveTestMember(createdLocation);
    }

    private String createTestMember(WebResource resource){
        ClientResponse response = resource
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, testMember);

        assertThat(response.getStatus()).isEqualTo(201);
        return response.getHeaders().get("Location").get(0);
    }

    private void retrieveTestMember(String location){
        WebResource newContactResource = client.resource(location);
        Member member = newContactResource.get(Member.class);

        // Check that it has the same properties as the initial one
        assertThat(member.getFirstName()).isEqualTo(testMember.getFirstName());
        assertThat(member.getLastName()).isEqualTo(testMember.getLastName());
        assertThat(member.getPhone()).isEqualTo(testMember.getPhone());
        assertThat(member.getEmail()).isEqualTo(testMember.getEmail());
        assertThat(member.getPassword()).isEqualTo(testMember.getPassword());
    }
}
