package com.personiv.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.personiv.config.filter.JwtAuthenticationTokenFilter;


@Configurable
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	CustomUserDetailsService userDetailsService;
	
	@Autowired
	private DataSource dataSource;
	// This method is for overriding the default AuthenticationManagerBuilder.
	// We can specify how the user details are kept in the application. It may
	// be in a database, LDAP or in memory.
	
	
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }
    
  

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder());
	}

	// This method is for overriding some configuration of the WebSecurity
	// If you want to ignore some request or request patterns then you can
	// specify that inside this method
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	// This method is used for override HttpSecurity of the web Application.
	// We can specify our authorization criteria inside this method.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
			// starts authorizing configurations
		http.authorizeRequests()
			// ignore the "/" and "/index.html"
			.antMatchers("/", 
						"/favicon.ico",
						"/authenticate",
						"/token-test",
						"/styles.168e3c619ea6c34a43fa.css",
						"/runtime.16f45ed35237485cfb7c.js",
						"/polyfills.6a3a3e8e2a36202b9f2e.js",
						"/main.3e0727e97a869ecbf43d.js",
						"/common.6fdf806cbe5a800580a1.js",
						"/fontawesome-webfont.af7ae505a9eed503f8b8.woff2",
						"/fontawesome-webfont.fee66e712a8a08eef580.woff",
						"/1.15dfdae9386d88acf74a.js",
						"/2.4fb75762bd1d16af6a37.js",
						"/testBatch",
						"/password",
						"/vendor/**",
						"/client/**",
						 "/suppliers/**",
						 "/users/**",
						 "/upload/**",
						 "/production_assets/**",
						 "/it_assets/**",
						 "/training_assets/**",
						 "/uploads/**",
						 "/software/**",
						 "/system-units/**",
						 "/accountability/download/**",
						 "/non_prod_assets",
						 "/accountability/downloadAttachment/**").permitAll()
			// authenticate all remaining URLS
			.anyRequest().fullyAuthenticated().and()
			//.anyRequest().permitAll().and()
			// enabling the basic authentication
			.httpBasic().disable()
			// configuring the session as state less. Which means there is
			// no session in the server
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.cors().and()
			// disabling the CSRF - Cross Site Request Forgery
			.csrf().disable();
		
		  //JWT Custom filter
		//http.addFilterBefore(corsFilterBean(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	
		// disable page caching
		http.headers().cacheControl();
		
   
		
	}
	
	

	
}
