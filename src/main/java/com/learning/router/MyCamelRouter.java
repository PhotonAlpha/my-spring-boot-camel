/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/07/01
 */
package com.learning.router;

import org.apache.camel.builder.RouteBuilder;

// @Component
public class MyCamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:hello?period={{timer.period}}").routeId("hello").routeGroup("hello-group")
                .transform().method("myBean", "saySomething")
                .filter(simple("${body} contains 'foo'"))
                .to("log:foo")
                .end()
                .to("stream:out");
    }

}
