/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/07/01
 */
package com.learning;

import org.apache.camel.builder.DeadLetterChannelBuilder;
import org.apache.camel.processor.RedeliveryPolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CamelApplication {
    public static void main(String[] args) {
        SpringApplication.run(CamelApplication.class, args);
    }

    @Bean
    public DeadLetterChannelBuilder myDeadLetterErrorHandler() {
        DeadLetterChannelBuilder deadLetterChannelBuilder = new DeadLetterChannelBuilder();
        deadLetterChannelBuilder.setDeadLetterUri("direct:error");
        deadLetterChannelBuilder.setRedeliveryPolicy(new RedeliveryPolicy().disableRedelivery());
        deadLetterChannelBuilder.useOriginalMessage();
        return deadLetterChannelBuilder;
    }
}
