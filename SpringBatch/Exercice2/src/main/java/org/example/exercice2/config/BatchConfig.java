package org.example.exercice2.config;

import org.example.exercice2.entity.Game;
import org.example.exercice2.tasklet.FileDeletionTasklet;
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

    private final FileDeletionTasklet fileCleaningTasklet;

    public BatchConfig(FileDeletionTasklet fileCleaningTasklet) {
        this.fileCleaningTasklet=fileCleaningTasklet;
    }

    @Bean
    public Job importJob(JobRepository jobRepository, Step step,Step stepCleaning) {
        return new JobBuilder("importGameJob",jobRepository)
                .start(step)
                .next(stepCleaning)
                .build();
    }

    @Bean
    public FlatFileItemReader<Game> reader() {
        return new FlatFileItemReaderBuilder<Game>()
                .name("gameItemReader")
                .resource(new ClassPathResource("games.csv"))
                .linesToSkip(1)
                .delimited()
                .names("name", "gender", "price")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Game.class);
                }})
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Game> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Game>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO game (name, gender, price) VALUES (:name, :gender, :price)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Step step(ItemReader<Game> reader,
                     ItemWriter<Game> writer, JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step",jobRepository)
                .<Game, Game>chunk(10,transactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public Step stepCleaning (JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("DeletingStep",jobRepository)
                .tasklet(fileCleaningTasklet)
                .transactionManager(transactionManager)
                .build();
    }

}
