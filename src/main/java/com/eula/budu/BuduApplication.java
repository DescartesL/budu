package com.eula.budu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.eula.budu.mapper")
@ComponentScan(basePackages = {"com.eula.budu.*"})
public class BuduApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuduApplication.class, args);
	}

}
