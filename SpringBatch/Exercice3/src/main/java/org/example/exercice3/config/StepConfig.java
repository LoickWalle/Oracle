package org.example.exercice3.config;

import org.example.exercice3.entity.Message;
import org.example.exercice3.entity.PhoneCall;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class StepConfig {

    @Bean
    public Step savePhoneCall(ItemReader<PhoneCall> phoneCallReader,
                     ItemWriter<PhoneCall> phoneCallWriter, JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("savePhoneStep",jobRepository)
                .<PhoneCall, PhoneCall>chunk(10,transactionManager)
                .reader(phoneCallReader)
                .writer(phoneCallWriter)
                .build();
    }

    @Bean
    public Step initStep(ItemReader<Message> messageReader,
                     ItemWriter<Message> messageWriter, JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("initStep",jobRepository)
                .<Message, Message>chunk(10,transactionManager)
                .reader(messageReader)
                .writer(messageWriter)
                .build();
    }
}
