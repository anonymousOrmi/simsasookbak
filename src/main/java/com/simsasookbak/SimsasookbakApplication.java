package com.simsasookbak;

import com.simsasookbak.global.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@EnableAsync
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@SpringBootApplication
public class SimsasookbakApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimsasookbakApplication.class, args);
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}
