package br.com.victorlc.forum.config

import br.com.victorlc.forum.security.JWTAuthenticationFilter
import br.com.victorlc.forum.security.JWTLoginFilter
import br.com.victorlc.forum.services.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfiguration(private val userService: UserService, private val jwtUtil: JWTUtil) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/topics").hasAuthority("WRITE_READ")
            .requestMatchers("/login").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .authenticationProvider(authenticationProvider())
            .authenticationManager(authenticationManager())
            .addFilterBefore(
                JWTLoginFilter(authManager = authenticationManager(), jwtUtil = jwtUtil),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .addFilterBefore(
                JWTAuthenticationFilter(jwtUtil = jwtUtil),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        return http.build()
    }

    private fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    private fun authenticationProvider(): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(userService)
        provider.setPasswordEncoder(passwordEncoder())
        return provider
    }

    private fun authenticationManager(): AuthenticationManager {
        return AuthenticationManager { authentication ->
            authenticationProvider().authenticate(authentication)
        }
    }
}