/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/07/08
 */
package com.learning.processor;

import com.learning.model.User;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class Student2Processor extends SimpleProcessor<User, String> {

    @Override
    public void process(Exchange exchange) throws Exception {
        super.process(exchange);
    }
}
