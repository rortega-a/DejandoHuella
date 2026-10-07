package com.iesalixar.servidor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
 * CLASE DONDE ESTABLECEREMOS LA CONFIGURACION DE
 * AUTENTIFICACION - CÓMO ACCEDO
 * AUTORIZACION - A QUÉ PUEDO ACCEDER
 * MÉTODO DE ENCRIPTACIÓN DE LAS CONTRASEÑAS
 *
 * AUTENTIFICACION: Spring Security usa automáticamente el único UserDetailsService
 * (JPAUserDetailsService) junto al PasswordEncoder definido abajo.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	/*
	 * MÉTODO PARA ESTABLECER AUTORIZACION - A QUÉ PUEDO ACCEDER
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/").permitAll()
				.requestMatchers("/registroUsuarios").anonymous()
				.requestMatchers("/registroCentros").anonymous()
				.requestMatchers("/usuario/**").hasRole("USER")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/centro/**").hasRole("CENTRO")
				.requestMatchers("/logoutPage").authenticated()
				// Spring Security 6 deniega por defecto lo no listado: mantenemos el
				// comportamiento anterior (recursos estáticos, /fotos, /login, /error...)
				.anyRequest().permitAll())
			.formLogin(form -> form
				.loginPage("/login")
				.permitAll())
			.logout(logout -> logout
				.logoutSuccessUrl("/"));

		return http.build();
	}

	/*
	 * ESTABLECEMOS EL PASSWORD ENCODER. FUERZA 15 (de 4 a 31)
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(15);
	}

}
