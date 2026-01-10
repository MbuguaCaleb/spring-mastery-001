package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "repositories")
public class ProjectConfig {

    //configuring a datasource, a connection to my DB
    @Bean
    public DataSource dataSource(){
        //everytime I am making a new configuration
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/nfc_merchant_onboarding");
        dataSource.setUsername("ncf");
        dataSource.setPassword("nfcpassword");
        return dataSource;
    }

    //Configuring JDBC Template to easily write my Queries
    //My JDBC Template will take in my DataSource
    //JDBC Template will be based on my datasource bean
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}
