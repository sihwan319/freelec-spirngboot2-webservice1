package com.sihwan.book.springboot.config.auth;

import com.sihwan.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정 활성화.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()//h2-console 화면 사용을 위해 해당 옵션들을 disable 합니다.
                .and()
                .authorizeRequests() //URL별 권한 관리를 설정하는 옵션의 시작점.
                .antMatchers("/", "/css/**", "/images/**", //권한 관리 대상을 지정하는 옵션.
                        "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()//설정된 값들 이외 나머지 URL, 인증된 사용자
                .and()
                .logout().logoutSuccessUrl("/") // 로그아웃 기능에 대한 설정 진입점.
                .and()
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);

    }

}
