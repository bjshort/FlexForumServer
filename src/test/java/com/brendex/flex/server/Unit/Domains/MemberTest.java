package com.brendex.flex.server.Unit.Domains;


import com.brendex.flex.server.AppTest;
import com.brendex.flex.server.domains.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.dropwizard.testing.FixtureHelpers.*;

public class MemberTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializeToJSON() throws Exception {
        try {
            final Member member = new Member("Bobby", "Latham", "000", "bob@latham.com", "bobby");
            final String fixtureMemberJSON = fixture("Fixtures/Members.json");

            JSONAssert.assertEquals(fixtureMemberJSON, MAPPER.writeValueAsString(member), false);
        } catch (Exception e){
            LOGGER.error("There was an error: " + e.getMessage());
        }

    }

}
