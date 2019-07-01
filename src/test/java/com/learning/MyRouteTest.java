/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/07/01
 */
package com.learning;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.DisableJmx;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertNotNull;

// @ActiveProfiles("test")
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = CamelApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DisableJmx(true)
@MockEndpoints
public class MyRouteTest {

    public static final String MY_HEADER = "testHeader";
    @Autowired
    private CamelContext camelContext;


    @EndpointInject(uri = "direct:myEndpoint")
    private ProducerTemplate endpoint;

    @Before
    public void setup() throws Exception {
        camelContext.getRouteDefinitions().get(0)
                .autoStartup(true)
                .adviceWith(camelContext, new RouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        onException(Exception.class).maximumRedeliveries(0);
                    }
                });
    }

    @Test
    public void shouldSucceed() throws Exception {
        assertNotNull(camelContext);
        assertNotNull(endpoint);

        String expectedValue = "expectedValue";
        MockEndpoint mock = camelContext.getEndpoint("mock:myEndpoint:put", MockEndpoint.class);
        mock.expectedMessageCount(1);
        mock.allMessages().body().isEqualTo(expectedValue);
        mock.allMessages().header(MY_HEADER).isEqualTo("testHeader");
        endpoint.sendBodyAndHeader("test", MY_HEADER, "testHeader");

        mock.assertIsSatisfied();
    }
}

