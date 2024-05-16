package com.simsasookbak.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync // 비동기 작업을 사용할 수 있도록 활성화
public class AsyncConfig {
    private static final int BASIC_THREAD_POOL_COUNT = 10;
    private static final int MAX_THREAD_POOL_COUNT = 20;
    private static final int QUEUE_CAPACITY = 40;
    private static final String THREAD_PREFIX = "async-task";

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(BASIC_THREAD_POOL_COUNT); // 기본 스레드 개수
        executor.setMaxPoolSize(MAX_THREAD_POOL_COUNT); // 최대 스레드 개수
        executor.setQueueCapacity(QUEUE_CAPACITY); // 작업 대기 큐 크기
        executor.setThreadNamePrefix(THREAD_PREFIX); // 스레드 이름 접두사
        executor.initialize();
        return executor;
    }
}