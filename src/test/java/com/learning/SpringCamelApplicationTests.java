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
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
public class SpringCamelApplicationTests {
    @EndpointInject(uri = MOCK_RESULT)
    private MockEndpoint resultEndpoint;
    @Autowired
    private CamelContext camelContext;
    @EndpointInject(uri = MOCK_TIMER)
    private ProducerTemplate producer;
    private static final String MOCK_RESULT = "mock:result";
    private static final String MOCK_TIMER = "direct:mock-timer";


    @EndpointInject(uri = "direct:myEndpoint")
    private ProducerTemplate endpoint;

    @Before
    public void setup() throws Exception {
        camelContext.getRouteDefinition("TIMER_ROUTE")
                .autoStartup(true)
                .adviceWith(camelContext, new AdviceWithRouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        replaceFromWith(MOCK_TIMER);
                        interceptSendToEndpoint("log*")
                                .skipSendToOriginalEndpoint()
                                .to(MOCK_RESULT);
                    }
                });
    }
    @Test
    public void sendMessage() throws Exception {
        resultEndpoint.expectedMessageCount(1);
        producer.sendBody("A message");
        resultEndpoint.assertIsSatisfied();
    }

    @Test
    public void shouldSucceed() throws Exception {
        assertNotNull(camelContext);
        assertNotNull(endpoint);

        // String expectedValue = "expectedValue";
        // MockEndpoint mock = getMockEndpoint("mock:myEndpoint:put");
        // mock.expectedMessageCount(1);
        // mock.allMessages().body().isEqualTo(expectedValue);
        // mock.allMessages().header(MY_HEADER).isEqualTo("testHeader");
        // endpoint.sendBodyAndHeader("test", MY_HEADER, "testHeader");
        //
        // mock.assertIsSatisfied();
    }
}
