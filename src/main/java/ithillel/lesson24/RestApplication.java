package ithillel.lesson24;

import ithillel.lesson22.HeroFabric;
import ithillel.lesson22.HeroService;
import ithillel.lesson23.DBConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Bean
    public HeroService heroService() {
        return HeroFabric.createService(DBConnection.connectTo("test", "postgres", "12345678", new String[]{"localhost"}, new int[]{5432}));
    }
}
