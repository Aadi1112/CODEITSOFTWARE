package com.codeIt.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.codeIt.service.CustomTrainerDetailsService;
import com.codeIt.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	
	@Autowired
	CustomTrainerDetailsService customTrainerDetailsService;
	
	
		

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	               .userDetailsService(userDetailsService())
	             
	               .passwordEncoder(NoOpPasswordEncoder.getInstance())
	               .and()
	               .build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailsService);
		
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

		return provider;
	}
//	@Bean
//	@Primary
//	public AuthenticationManager trainerauthenticationManager(HttpSecurity http) throws Exception {
//	    return http.getSharedObject(AuthenticationManagerBuilder.class)
//	               .userDetailsService(trainerDetailsService())
//	             
//	               .passwordEncoder(NoOpPasswordEncoder.getInstance())
//	               .and()
//	               .build();
//	}
	
//	@Bean
//	public CustomTrainerDetailsService trainerDetailsService() {
//		
//		return new CustomTrainerDetailsService();
//	}
	
	@Bean
	public CustomUserDetailsService userDetailsService() {
	    return new CustomUserDetailsService(); // The service that loads user from database
	}
	

	
//	
//	
//
//	@Bean
//	public AuthenticationProvider trainerauthenticationProvider() {
//
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(customTrainerDetailsService);
//		
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//
//		return provider;
//	}

//	@Bean
//	public SecurityFilterChain chain(HttpSecurity security) throws Exception {
//		
//		return security.csrf(csrf->csrf.disable())
//				
//				.authorizeHttpRequests(auth-> auth
//						
//						.requestMatchers("/login1", "/register","/registerstudent","/","/home","logo.jpg","pmam.jpg","styles.css","/loginform","/dashboard").permitAll()
//						
//						.anyRequest().authenticated()
//			
//						
//						).build();
//			
//	}

//	 @Bean
//	    public SecurityFilterChain security(HttpSecurity httpSecurity) throws Exception {
//	        return 
//	        		
//	        		
//	        		httpSecurity.csrf().disable()
//	            .authorizeRequests(auth -> 
//	                auth.requestMatchers("error","/login1","/student", "/register","/registerstudent","/","/home","logo.jpg","pmam.jpg","styles.css","java.png","/loginform","/loginstudent","android.jpg","datascience.png","net.jpg","python.jpg","angular.jpg").permitAll() // Allow access to login/register
//	                .requestMatchers("/dashboard").permitAll() 
//	                .anyRequest().authenticated() // Require authentication for all other requests
//	            )
//	            .formLogin(form -> 
//	              form
//	              .loginPage("/login1")
//	              .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login1").invalidateHttpSession(true) // Invalidate session
//	              .deleteCookies("JSESSIONID") // Remove cookies
//	              .and()
//	              .sessionManagement()
//	                  .maximumSessions(1)
//	                  .expiredUrl("/login1")
////	              .loginProcessingUrl("/loginform")
////	              .defaultSuccessUrl("/dashboard")
//	                .disable() // Use the default login page provided by Spring Security
//	            )
//	           
//	    }
	 
//	 @SuppressWarnings("removal")
//	@Bean
//	
//	 public SecurityFilterChain security(HttpSecurity httpSecurity) throws Exception {
//	     return httpSecurity
//                 .csrf(csrf -> csrf.disable()) // Disable CSRF for development (enable in production)
//                 .authorizeRequests(auth -> auth
//                                 // Publicly accessible paths
//                                 .requestMatchers(
//                                		 "/registertrainer",
//                                		 "/trainerlogin",
//                                         "/error", "/login1", "/student", "/register",
//                                         "/registerstudent", "/", "/home", "/loginform",
//                                         "/loginstudent", "logo.jpg", "pmam.jpg", "styles.css",
//                                         "java.png", "android.jpg", "datascience.png",
//                                         "net.jpg", "python.jpg", "angular.jpg"
//                                 ).permitAll()
//                                 // Protect dashboard - require authentication
//                                 .requestMatchers("/dashboard").hasAnyRole("USER", "ADMIN", "TRAINER")                                  // Require authentication for all other paths
//                                 .requestMatchers("/trainers").hasRole("TRAINER") // Only trainers can access trainer-related pages
//
//                                 .anyRequest().authenticated()
//                 )
//                 .formLogin(form -> form
//                                 .loginPage("/login1") // Custom login page
//                                 .loginProcessingUrl("/loginstudent")
//                                 .usernameParameter("username") // Ensure that this matches the 'username' in your form
//                                 .passwordParameter("password")// Form's action attribute URL
//                                 .defaultSuccessUrl("/dashboard", true)
// // Redirect after successful login
//                                 .failureUrl("/login1?error=true")
//                                 .permitAll() // Allow access to login endpoints
//               
//                                 
//                		 )
//                 .formLogin(form->form
//                		 
//                		 
//                		 .loginPage("/trainerlogin") // Trainer login page
//                         .loginProcessingUrl("/trainerlogin") // Trainer login processing URL
//                         .usernameParameter("email") // Trainer login form uses email
//                         .passwordParameter("password")
//                         .defaultSuccessUrl("/trainerdashboard", true) // Redirect to trainer-specific page after successful login
//                         .failureUrl("/logintrainer?error=true")
//                         .permitAll() // Allow access to trai
//                		 
//                		 
//                		 
//                		 
//                		 
//                		 )
//                 
//                 
//                 
//                 .logout(logout -> logout
//                                 .logoutUrl("/logout") // Logout URL
//                                 .logoutSuccessUrl("/login1") // Redirect to login after logout
//                                 .invalidateHttpSession(true) // Invalidate the session
//                                 .deleteCookies("JSESSIONID") // Delete cookies
//                                 .addLogoutHandler((request, response, authentication) -> {
//                                     // Handle logout redirection based on role
//                                     if (authentication != null) {
//                                         if (authentication.getAuthorities().stream()
//                                             .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_TRAINER"))) {
//                                             // Redirect to trainer login if trainer
//                                             try {
//												response.sendRedirect("/logintrainer");
//											} catch (IOException e) {
//												// TODO Auto-generated catch block
//												e.printStackTrace();
//											}
//                                         } else {
//                                             // Redirect to student login if student
//                                             try {
//												response.sendRedirect("/login1");
//											} catch (IOException e) {
//												// TODO Auto-generated catch block
//												e.printStackTrace();
//											}
//                                         }
//                                     }
//                                 })
//                 )
//                 .sessionManagement(session -> session
//                                 .maximumSessions(1) // Limit to one session per user
//                                 .expiredUrl("/login1") // Redirect if session expires
//                 )
//                 .build();
//	 }
//	 

	@Bean
    @Order(1) // Higher priority for trainer security filter
    public SecurityFilterChain trainerSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .securityMatcher("/trainerlogin", "/trainerdashboard", "/trainers/**","/logout","/trainerlogin?error=true") // Applies to trainer-specific URLs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/trainerlogin").permitAll() // Publicly accessible paths for trainers
                        .anyRequest().hasRole("TRAINER") // Require TRAINER role for all other paths
                )
                .formLogin(form -> form
                        .loginPage("/trainerlogin")
                        .loginProcessingUrl("/trainerlogin")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/trainerdashboard", true)
                        .failureUrl("/trainerlogin?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            if (authentication != null) {
                                if (authentication.getAuthorities().stream()
                                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_TRAINER"))) {
                                    response.sendRedirect("/trainerlogin");
                                } else {
                                    response.sendRedirect("/login1");
                                }
                            } else {
                                response.sendRedirect("/login1");
                            }
                        })
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .build();
    }

    @Bean
    @Order(2) // Lower priority for student security filter
    public SecurityFilterChain studentSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .securityMatcher("/loginstudent", "/dashboard", "/student/**","/logout","/login1?error=true") // Applies to student-specific URLs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login1", "/registerstudent", "/", "/home").permitAll() // Publicly accessible paths for students
                        .anyRequest().hasRole("USER") // Require USER role for all other paths
                )
                .formLogin(form -> form
                        .loginPage("/login1")
                        .loginProcessingUrl("/loginstudent")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login1?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            if (authentication != null) {
                                if (authentication.getAuthorities().stream()
                                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_TRAINER"))) {
                                    response.sendRedirect("/trainerlogin");
                                } else {
                                    response.sendRedirect("/login1");
                                }
                            } else {
                                response.sendRedirect("/login1");
                            }
                        })
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .build();
    }

}
