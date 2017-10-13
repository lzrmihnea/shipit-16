package eu.accesa.shopit.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile({"!integration-test"})
@MapperScan(basePackages = "eu.accesa.shopit.repository")
public class PostgreSqlDbConfig {

    @Value("${spring.datasource.url}")
    protected String dbUrl;

    @Value("${spring.datasource.username}")
    protected String dbUser;

    @Value("${spring.datasource.password}")
    protected String dbPass;

    @Value("${spring.datasource.dbdriver}")
    protected String dbDriver;

    @Bean
    public DataSource dataSource() {
        return this.getDataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        return this.getSqlSessionFactory();
    }

    protected DataSource getDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setJdbcUrl(dbUrl);
        dataSource.setConnectionTestQuery("select 1");
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPass);
        return dataSource;
    }

    protected SqlSessionFactory getSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        return sessionFactory.getObject();
    }

}
