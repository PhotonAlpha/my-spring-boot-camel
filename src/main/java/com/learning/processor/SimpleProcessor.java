/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/07/08
 */
package com.learning.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class SimpleProcessor<T, C> extends AbstractProcessor {
    @Autowired
    private VelocityEngine velocityEngine;

    public void preProcess(Exchange exchange) {

    }

    public void preVelocityConvertIntoBody(Exchange exchange, String templatePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(getExchangeBody(exchange));
        JsonNode jsonNode = mapper.readValue(jsonStr, JsonNode.class);

        Template t = velocityEngine.getTemplate(templatePath);
        VelocityContext context = new VelocityContext();
        context.put("body", jsonNode);
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        exchange.getIn().setBody(writer.toString());
    }

    public void afterVelocityConvertIntoBody(Exchange exchange,String content, String templatePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readValue(content, JsonNode.class);

        Template t = velocityEngine.getTemplate(templatePath);
        VelocityContext context = new VelocityContext();
        context.put("body", jsonNode);
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        exchange.getIn().setBody(writer.toString());
    }

    public T getExchangeBody(Exchange exchange) {
        Type[] params = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        Class<T> clazz = (Class) params[0];
        return exchange.getIn().getBody(clazz);
    }

    public C execute(Exchange exchange) throws IOException {
        Type[] params = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        Class<C> clazz = (Class) params[1];
        C result = exchange.getIn().getBody(clazz);
        return result;
    }


    @Override
    public void process(Exchange exchange) throws Exception {
        preProcess(exchange);
        C result = execute(exchange);
        postProcess(exchange, result);

    }

    public void postProcess(Exchange exchange, C result) {
        exchange.getIn().setBody(result);
    }
}
