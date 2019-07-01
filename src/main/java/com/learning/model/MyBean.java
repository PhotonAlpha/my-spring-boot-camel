/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/07/01
 */
package com.learning.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("myBean")
public class MyBean {

    @Value("${greeting}")
    private String say;

    public String saySomething() {
        return say;
    }

}
