package com.example.projectb2c.security;



import com.example.projectb2c.entity.Provider;
import com.example.projectb2c.security.auth.CustomOAuth2User;
import com.example.projectb2c.security.auth.CustomOAuth2UserService;
import com.example.projectb2c.security.auth.UserDetailServiceImpl;
import com.example.projectb2c.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecutityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    @Autowired
    CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailServiceImpl();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests()
                .antMatchers( "/","/login","/manager","/checkpage","/sendmail","/addcategory","/addpro","/forgot","/displayBarGraph","/displayPieChart","/upload/**","/css/**","/images/**"
                        ,"/js/**","/plugins/**","/scss/**","/update").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/checkpage")
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
                        String clientName=oAuth2User.getClienName();
                        Provider provider=Provider.LOCAL;
                        if(clientName.equals("Facebook")){
                            provider=Provider.FACEBOOK;
                        }else if(clientName.equals("Google")){
                            provider=Provider.GOOGLE;
                        }
                        userService.checkExistUserAuth(oAuth2User.getEmail(),provider);
                        response.sendRedirect("/checkpage");
                    }
                })
                .and()
                .logout().logoutSuccessUrl("/checkpage").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }

}
