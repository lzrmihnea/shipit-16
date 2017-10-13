package eu.accesa.shopit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "eu.accesa.shopit")
@SpringBootApplication(scanBasePackages = "eu.accesa.shopit")
@MapperScan(basePackages = "eu.accesa.shopit.repository")
public class ShopItServer {

    public static void main(String[] args) {
        SpringApplication.run(ShopItServer.class, args);
    }
}
