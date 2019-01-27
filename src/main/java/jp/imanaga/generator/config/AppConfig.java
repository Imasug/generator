package jp.imanaga.generator.config;

import java.util.ResourceBundle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	// TODO なぜかReloadableResourceBundleMessageSourceが使えない
	@Bean
	public ResourceBundle resourceBundle() {
		return ResourceBundle.getBundle("messages");
	}

}
