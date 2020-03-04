package com.myforce.auth.security.config;

import com.myforce.auth.common.Appconfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration

public class OAuth2ServerConfiguration {




    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        private final JwtAccessTokenConverter jwtAccessTokenConverter;

        @Autowired
        private  TokenStore tokenStore;




        public ResourceServerConfiguration(JwtAccessTokenConverter jwtAccessTokenConverter, UserDetailsService userDetailsService) {
            this.jwtAccessTokenConverter = jwtAccessTokenConverter;

        }

        @Override
        public void configure(final ResourceServerSecurityConfigurer resources) {
            System.out.println("tessss");
            resources
                    .tokenStore(tokenStore);
//                    .tokenStore(new JwtTokenStore(jwtAccessTokenConverter));

        }

        @Override
        public void configure(final HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated();
        }

    }

    @Configuration
    @EnableAuthorizationServer

    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        private final JwtAccessTokenConverter jwtAccessTokenConverter;

        private final BCryptPasswordEncoder passwordEncoder;

        private final AuthenticationManager authenticationManager;

        private final UserDetailsService userDetailsService;

        @Autowired
        private RedisConnectionFactory redisConnectionFactory;








        public AuthorizationServerConfiguration(JwtAccessTokenConverter jwtAccessTokenConverter,
                                                BCryptPasswordEncoder passwordEncoder,
                                                AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
            this.jwtAccessTokenConverter = jwtAccessTokenConverter;
            this.passwordEncoder = passwordEncoder;
            this.authenticationManager = authenticationManager;
            this.userDetailsService = userDetailsService;
        }
//         .tokenStore(new JwtTokenStore(jwtAccessTokenConverter))
        @Override
        public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .tokenStore(tokenStore())
                    .authenticationManager(authenticationManager)
                    .userDetailsService(userDetailsService)
                    .accessTokenConverter(jwtAccessTokenConverter)
                    .reuseRefreshTokens(false);
        }

        @Override
        public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .inMemory()
                    .withClient("client")
                    .secret(passwordEncoder.encode("secret"))
                    .authorizedGrantTypes("password", "refresh_token")
                    .scopes("read", "write")
                    .accessTokenValiditySeconds(600);

        }

        @Bean
        public TokenStore tokenStore() {
            RedisTokenStore redis = new RedisTokenStore(redisConnectionFactory);
            return redis;
        }
//
//        @Bean
//        JedisConnectionFactory jedisConnectionFactory() {
//            System.out.println("TES"+ redisHostName);
//            JedisConnectionFactory jedisConFactory
//                    = new JedisConnectionFactory();
//            jedisConFactory.setHostName(redisHostName);
//            jedisConFactory.setPort(6379);
//            return jedisConFactory;
////            return new JedisConnectionFactory();
//        }
//
//
//        @Bean
//        public RedisTemplate<String, Object> redisTemplate() {
//            final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
//            template.setConnectionFactory(jedisConnectionFactory());
//            template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
//            return template;
//        }


    }
}
