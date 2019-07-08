/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/07/05
 */
package com.learning;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CamelApplication.class);
    }
}
