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

import java.io.IOException;

@Component
public class StudentProcessor extends SimpleProcessor<User, String> {

    @Override
    public String execute(Exchange exchange) throws IOException {
        preVelocityConvertIntoBody(exchange, "templates/MyRequest.vm");
        String result = "{\n" +
                "\t\"id\": 20,\n" +
                "\t\"name\": \"response\"\n" +
                "}";
        afterVelocityConvertIntoBody(exchange, result, "templates/MyRequest.vm");
        return exchange.getIn().getBody(String.class);
    }
}
