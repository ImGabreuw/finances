package br.com.gabreuw.finances.announcement_search_engine.application.config.bean;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakerConfig {

    @Bean
    public Faker faker() {
        return Faker.instance();
    }

}
