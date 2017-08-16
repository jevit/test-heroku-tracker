package jv.jpatpl;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Spring {

	public static void main(String[] args) {
		SpringApplication.run(Spring.class, args);
	}

	@Bean
	@ConfigurationProperties(prefix = "app.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
}
