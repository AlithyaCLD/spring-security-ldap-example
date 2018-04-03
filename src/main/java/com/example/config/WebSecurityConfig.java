package com.example.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;


@Configuration
@ComponentScan(basePackages={"com.example"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${app.security.ldap.url}")
    private String ldapUrl;
    @Value("${app.security.ldap.base}")
    private String ldapBase;
    @Value("${app.security.ldap.user.dn-patterns}")
    private String ldapUserDnPatterns;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/static/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().defaultSuccessUrl("/app",true);
		//.antMatchers("/admin/**").hasRole("ADMIN") 
	}

@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication()
				.userDnPatterns(ldapUserDnPatterns)
                .contextSource(contextSource())
                .and()
                .inMemoryAuthentication()
                .withUser("admin").roles("ADMIN")
                .password("{noop}admin");		
	}


	@Bean
    public DefaultSpringSecurityContextSource contextSource() {
        return  new DefaultSpringSecurityContextSource(
                Collections.singletonList(ldapUrl), ldapBase);     
    }
	
}