/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/07/02
 */
package com.learning.router;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class GlobalErrorHandler implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("process::" +exchange.getException().getMessage());
    }
}
