package com.habitjournal.habit_journal_api.infrastructure.config;


import com.habitjournal.habit_journal_api.infrastructure.web.client.GamificationHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }

    @Bean
    public GamificationHttpClient inventoryClient(WebClient.Builder builder){

        WebClient webclient = builder
                .baseUrl("http://localhost:8081")
                .build();

        // Crea una fábrica que generará clientes HTTP basados en interfaces
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webclient)).build();

        // Crea una implementación de InventoryClient usando esa fábrica
        return factory.createClient(GamificationHttpClient.class);
    }
}
