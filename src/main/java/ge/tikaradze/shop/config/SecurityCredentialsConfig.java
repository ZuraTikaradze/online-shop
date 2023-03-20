package ge.tikaradze.shop.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityCredentialsConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtConfig jwtConfig;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig))
                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui.html/**", "/v2/api-docs", "/webjars/**", "/swagger-resources/**", "/configuration/**").permitAll()// swagger ი რომ ჩატვირთოს .

                .antMatchers(HttpMethod.POST, "/api/generateJWT/**").permitAll()

                // ALL Access
                .antMatchers("/api/login/**").permitAll()
                .antMatchers("/api/sign-up/**").permitAll()
                //Admin Access
                .antMatchers("/api/app-user/**").hasRole("ADMINISTRATOR")
                .antMatchers("/api/category/**").hasRole("ADMINISTRATOR")
                .antMatchers("/api/role/**").hasRole("ADMINISTRATOR")
                .antMatchers("/api/order/**").hasRole("ADMINISTRATOR")
                .antMatchers("/api/product/**").hasRole("ADMINISTRATOR")
                // client access
                .antMatchers("/api/order/**").hasRole("CLIENT")
                // seller access
                .antMatchers("/api/product/**").hasRole("SELLER")

                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }

}
