/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/07/01
 */
package com.learning.router;

import org.apache.camel.builder.RouteBuilder;

// @Component
public class MyRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:foo")
                .to("log:bar");
    }
}
