package org.example.exercice3.config;

import org.example.exercice3.entity.Message;
import org.example.exercice3.entity.PhoneCall;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
public class WriterConfig {

    @Bean
    public FlatFileItemWriter<PhoneCall> phoneCallWriter() {
        String outputPath = "phoneCall.csv";
        DelimitedLineAggregator<PhoneCall> lineAggregator = new DelimitedLineAggregator<>();

        BeanWrapperFieldExtractor<PhoneCall> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] { "id", "customer_id", "duration"});

        lineAggregator.setFieldExtractor(fieldExtractor);

        return new FlatFileItemWriterBuilder<PhoneCall>()
                .name("phoneCallWriter")
                .resource(new FileSystemResource(outputPath))
                .lineAggregator(lineAggregator)
                .append(false)
                .headerCallback(writer -> writer.write("id,customer_id,duration"))
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Message> messageWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Message>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO message (customer_id, content) VALUES (:customer_id, :content)")
                .dataSource(dataSource)
                .build();
    }
}
