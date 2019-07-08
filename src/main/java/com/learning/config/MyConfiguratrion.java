/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/07/05
 */
package com.learning.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
public class MyConfiguratrion {
    @Controller
    class SwaggerWelcome {
        @RequestMapping("/swagger-ui")
        public String redirectToUi() {
            return "redirect:/swagger-ui/index.html?url=/my-spring-boot-camel-1.0-SNAPSHOT/api/api-doc&validatorUrl=";
        }
    }
}
