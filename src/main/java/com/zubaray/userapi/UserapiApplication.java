package com.zubaray.userapi;

import com.zubaray.userapi.entity.RoleApi;
import com.zubaray.userapi.entity.UserApi;
import com.zubaray.userapi.repository.UserApiRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
@EnableJpaAuditing
public class UserapiApplication {

	public static void main(String[] args) {SpringApplication.run(UserapiApplication.class, args);}

	@Bean
	public CommandLineRunner demoData(UserApiRepository repository) {
		return args -> {
			repository.save(
					UserApi.builder()
							.name("Admin")
							.email("admin@mail.com")
							.password("$4Password")
							.active(true)
							.role(RoleApi.ADMIN)
							.createdAt(new Date())
							.lastLogin(LocalDateTime.now())
							.build());
		};
	}


}
