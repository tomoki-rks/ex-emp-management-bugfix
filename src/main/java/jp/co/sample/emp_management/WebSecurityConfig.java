package jp.co.sample.emp_management;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * セキュリティの設定をするクラス.
 * 
 * @author tomoki.hirobe
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity http) throws Exception {
        // 暫定的に、すべてのユーザにすべてのページのアクセス許可
        http.authorizeRequests().anyRequest().permitAll();
    }
}
