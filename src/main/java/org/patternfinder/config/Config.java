package org.patternfinder.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class Config {

    private Integer corePoolSize;
    private Integer maxPoolSize;
    private String threadNamePrefix;

    public Config(
            @Value("${patternfinder.taskexecutor.CorePoolSize}") Integer corePoolSize,
            @Value("${patternfinder.taskexecutor.MaxPoolSize}") Integer maxPoolSize,
            @Value("${patternfinder.taskexecutor.ThreadNamePrefix}") String threadNamePrefix) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.threadNamePrefix = threadNamePrefix;
    }

    @Bean(name = "findTaskExecutor")
    public Executor findTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(0);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.initialize();
        return executor;
    }

}
