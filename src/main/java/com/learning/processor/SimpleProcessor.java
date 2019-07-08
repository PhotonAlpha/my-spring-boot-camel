/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/07/08
 */
package com.learning.processor;

import com.learning.model.User;
import org.apache.camel.Exchange;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class SimpleProcessor<T> extends AbstractProcessor {
    public <T>T processed(Exchange exchange) {
        Type[] params = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        Class<T> clazz = (Class) params[0];
        T result = exchange.getIn().getBody(clazz);
        return result;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        T processed = processed(exchange);
        User result = postProcess(processed);
        exchange.getIn().setBody(result);

    }

    public User postProcess(T result) {
        return (User)result;
    }
}
