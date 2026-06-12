package com.ayush.settleUp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.ayush.settleUp.config.*;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class SettleUpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettleUpApplication.class, args);
	}

}
