package jv.jpatpl;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class Spring {
	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(Spring.class, args);
	}

	@Bean
	@ConfigurationProperties(prefix = "app.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
}
