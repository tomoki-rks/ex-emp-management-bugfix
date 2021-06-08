package jp.co.sample.emp_management;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * セキュリティの設定をするクラス.
 * 
 * @author tomoki.hirobe
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/").loginProcessingUrl("/login").usernameParameter("mailAddress")
                .passwordParameter("password").defaultSuccessUrl("/employee/showList", true).permitAll();

        http.authorizeRequests().antMatchers("/css/**", "/img/**", "/js/**").permitAll();
        http.authorizeRequests().antMatchers("/toInsert", "/insert").permitAll();

        // 従業員ページを見られるやつ（DEBUG用）
        // http.authorizeRequests().antMatchers("/employee/**").permitAll();
        // http.authorizeRequests().antMatchers("/employee/**").hasRole("ADMIN").anyRequest().authenticated();

        http.authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
