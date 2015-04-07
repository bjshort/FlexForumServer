package com.brendex.flex.server.Unit.Resources;
import com.brendex.flex.server.App;
import com.brendex.flex.server.ServerConfiguration;
import com.brendex.flex.server.domains.Member;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.*;

import javax.ws.rs.core.MediaType;

import static org.fest.assertions.api.Assertions.assertThat;


@FixMethodOrder
@Ignore
public class MemberResourceTest {

    @ClassRule
    public static final DropwizardAppRule<ServerConfiguration> RULE =
            new DropwizardAppRule<ServerConfiguration>(App.class, "config.yaml");

    private final Member[] testMember =
            {new Member("Bobby", "Latham", "000", "bob@latham.com", "bobby"),
                    new Member("Robbie", "Latham", "000", "rob@latham.com", "robby")};
    private Client client;

    @Before
    public void setup() {
        client = new Client();
    }

    @Test
    public void testCreateAndRetrieveMembers() {
        WebResource contactResource = client.resource("http://localhost:8080/members");

        createAllTestMembers(contactResource);
    }

    @Test
    public void deleteAMember() {
        WebResource contactResource = client.resource("http://localhost:8080/members/1");
        ClientResponse response = contactResource.type(MediaType.TEXT_PLAIN)
                .delete(ClientResponse.class);

        assertThat(response.getStatus()).isEqualTo(204);
    }

    private void createAllTestMembers(WebResource resource) {
        for(int x = 0; x < testMember.length; x++){
            String createdLocation = createTestMember(resource, x);
            retrieveTestMember(createdLocation, x);
        }
    }

    private String createTestMember(WebResource resource, int testMemberId){
        ClientResponse response = resource
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, testMember[testMemberId]);

        assertThat(response.getStatus()).isEqualTo(201);
        return response.getHeaders().get("Location").get(0);
    }

    private void retrieveTestMember(String location, int testMemberId){
        WebResource newContactResource = client.resource(location);
        Member member = newContactResource.get(Member.class);

        // Check that it has the same properties as the initial one
        assertThat(member.getFirstName()).isEqualTo(testMember[testMemberId].getFirstName());
        assertThat(member.getLastName()).isEqualTo(testMember[testMemberId].getLastName());
        assertThat(member.getPhone()).isEqualTo(testMember[testMemberId].getPhone());
        assertThat(member.getEmail()).isEqualTo(testMember[testMemberId].getEmail());
        assertThat(member.getPassword()).isEqualTo(testMember[testMemberId].getPassword());
    }
}
