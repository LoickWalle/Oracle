package org.example.exercice3.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job dailySave(JobRepository jobRepository, Step savePhoneCall) {
        return new JobBuilder("dailySaveJob",jobRepository)
                .start(savePhoneCall)
                .build();
    }

    @Bean
    public Job init(JobRepository jobRepository, Step initStep) {
        return new JobBuilder("initJob",jobRepository)
                .start(initStep)
                .build();
    }
}
