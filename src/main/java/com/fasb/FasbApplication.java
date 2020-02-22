package com.fasb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@SpringBootApplication
public class FasbApplication {


/*
	@Bean
	public WebSecurityConfigurerAdapter webSecurityConfig(DataSource dataSource) {
		return new WebSecurityConfigurerAdapter() {
			@Override
			protected void configure(HttpSecurity httpSecurity) throws Exception {
				httpSecurity
						.authorizeRequests().antMatchers("/console/**").permitAll();
				httpSecurity.csrf().disable();
				httpSecurity.headers().frameOptions().disable();
			}
		};
	}*/

	public static void main(String[] args) {
		SpringApplication.run(FasbApplication.class, args);
	}

}
