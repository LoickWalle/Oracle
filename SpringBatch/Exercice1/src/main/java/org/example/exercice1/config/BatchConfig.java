package org.example.exercice1.config;

import org.example.exercice1.entity.Dinosaurs;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job importJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("modifyAgeJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository,
                     PlatformTransactionManager transactionManager,
                     ItemReader<Dinosaurs> itemReader,
                     ItemProcessor<Dinosaurs, Dinosaurs> processor,
                     ItemWriter<Dinosaurs> itemWriter) {
        return new StepBuilder("step", jobRepository)
                .<Dinosaurs, Dinosaurs>chunk(10, transactionManager)
                .reader(itemReader)
                .processor(processor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public FlatFileItemReader<Dinosaurs> reader() {
        return new FlatFileItemReaderBuilder<Dinosaurs>()
                .name("dinosaursItemReader")
                .resource(new ClassPathResource("dinosaurs.csv"))
                .linesToSkip(1)
                .delimited()
                .names("id","name","species","age_million_years")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Dinosaurs>() {{
                    setTargetType(Dinosaurs.class);
                }})
                .build();
    }

    @Bean
    public ItemProcessor<Dinosaurs, Dinosaurs> processor() {
        return dinosaurs -> {
            dinosaurs.setAge_century_years(dinosaurs.getAge_million_years() * 10000f);
            return dinosaurs;
        };
    }

    @Bean
    public JdbcBatchItemWriter<Dinosaurs> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Dinosaurs>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO dinosaurs (id,name,species,age_million_years,age_century_years) VALUES (:id,:name,:species,:age_million_years,:age_century_years)")
                .dataSource(dataSource).build();
    }
}
