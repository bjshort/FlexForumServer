package com.brendex.flex.server.Unit.Resources;
import com.brendex.flex.server.dao.MemberDAO;
import com.brendex.flex.server.domains.Member;
import com.brendex.flex.server.resources.MembersResource;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.fest.assertions.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

public class MemberResourceTest {

    private static final MemberDAO memberDAO = mock(MemberDAO.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberResourceTest.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new MembersResource(memberDAO))
            .build();

    private final Member member = new Member("Bobby", "Latham", "000", "bob@latham.com", "bobby");

    @Before
    public void setup() {
        when(memberDAO.findById(eq(1l))).thenReturn(member);
        // we have to reset the mock after each test because of the
        // @ClassRule, or use a @Rule as mentioned below.
        reset(memberDAO);
    }

    @Test
    public void testGetMember() {
        WebResource resource = resources.client().resource("/members/1");
        LOGGER.info("RESOURCE: " + resource);
        assertThat(resource.get(Member.class))
                .isEqualTo(member);
        verify(memberDAO).findById(1l);
    }
}
