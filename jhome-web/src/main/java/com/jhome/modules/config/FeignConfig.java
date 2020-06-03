package com.jhome.modules.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;

@Configuration
public class FeignConfig {
    @Bean
    public Retryer feignRetryer(){
            //重试次数
        return new Retryer.Default(1000, TimeUnit.SECONDS.toMillis(1),2);
    }
        /*        @Bean
        public Decoder feignDecoder() {
                HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(objectMapper());
                ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
                return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
        }
        @Bean
        public Encoder feignEncoder(){
                HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(objectMapper());
                ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
                return new SpringEncoder(objectFactory);
        }
        public ObjectMapper objectMapper(){
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
                return objectMapper;
        }*/
}
