//package com.alibou.coding.mongodemo.user;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    // Configure HTTP security
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers( "/**","/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**")  // Allow Swagger UI
//                                .permitAll()  // Allow unauthenticated access to Swagger-related resources
//                                .anyRequest()  // Any other request requires authentication
//                                .authenticated()
//                );// Disable CSRF protection if you don't need it for your app
//
//        return http.build();
//    }
//}
//

package com.alibou.coding.mongodemo.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configure HTTP security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                // Allow unauthenticated access to swagger-related resources
                                .requestMatchers("/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**")
                                .permitAll()
                                // Allow unauthenticated access to POST API endpoints used for record creation
                                .requestMatchers("/api/your-endpoint/**")  // Add your actual endpoints here
                                .permitAll()
                                // Any other request requires authentication
                                .anyRequest().authenticated()
                )
                .csrf().disable(); // Disable CSRF protection if not required
        return http.build();
    }
}
