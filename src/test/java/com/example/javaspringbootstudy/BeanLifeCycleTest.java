package com.example.javaspringbootstudy;

import com.example.javaspringbootstudy.model.NetworkClient;
import com.example.javaspringbootstudy.model.NetworkClientWithAnnotation;
import com.example.javaspringbootstudy.model.NetworkClientWithBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }

        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClientWithBean networkClientWithBean() {
            NetworkClientWithBean networkClient = new NetworkClientWithBean();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }

        @Bean
        public NetworkClientWithAnnotation networkClientWithAnnotation() {
            NetworkClientWithAnnotation networkClient = new NetworkClientWithAnnotation();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
