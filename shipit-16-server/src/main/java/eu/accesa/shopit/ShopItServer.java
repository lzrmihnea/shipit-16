package eu.accesa.shopit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
//@EnableJpaRepositories(basePackages = "eu.accesa.shopit", repositoryBaseClass = BaseRepositoryImpl.class)
@SpringBootApplication
public class ShopItServer {

	public static void main(String[] args) {
		SpringApplication.run(ShopItServer.class, args);
	}
}
