package br.com.mentorama.apiFilmes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

@Configuration
public class AsyncConfiguration {

    @Bean
    public Executor executorService(){
        return ForkJoinPool.commonPool();
    }

    @Bean
    public Executor customThreadPool(){
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(500);
        return executor;
    }


}
