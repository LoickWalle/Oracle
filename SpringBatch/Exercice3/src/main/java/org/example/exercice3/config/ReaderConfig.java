package org.example.exercice3.config;

import org.example.exercice3.entity.Message;
import org.example.exercice3.entity.PhoneCall;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ReaderConfig {

    @Bean
    public ItemReader<PhoneCall> phoneCallReader() {
        return new FlatFileItemReaderBuilder<PhoneCall>()
                .name("phoneItemReader")
                .resource(new ClassPathResource("call.txt"))
                .linesToSkip(1)
                .delimited()
                .names("id","customer_id","duration")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(PhoneCall.class);
                }})
                .build();
    }

    @Bean
    public ItemReader<Message> messageReader() {
        return new FlatFileItemReaderBuilder<Message>()
                .name("messageItemReader")
                .resource(new ClassPathResource("message.csv"))
                .linesToSkip(1)
                .delimited()
                .names("customer_id", "content")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Message.class);
                }})
                .build();
    }
}
