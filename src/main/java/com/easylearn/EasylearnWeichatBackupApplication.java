package com.easylearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EasylearnWeichatBackupApplication {

	public static void main(String[] args) {

		SpringApplication.run(EasylearnWeichatBackupApplication.class, args);
	}
}
