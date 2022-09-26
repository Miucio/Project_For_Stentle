package it.miucio.project_for_stentle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
public class ProjectForStentleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectForStentleApplication.class, args);
	}


}
