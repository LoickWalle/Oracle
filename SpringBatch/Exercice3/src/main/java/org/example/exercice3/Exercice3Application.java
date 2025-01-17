package org.example.exercice3;

import jakarta.annotation.PostConstruct;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class Exercice3Application {

    private final JobLauncher jobLauncher;
    private final Job dailySave;
    private final Job init;

    public Exercice3Application(JobLauncher jobLauncher, Job dailySave, Job init) {
        this.jobLauncher = jobLauncher;
        this.dailySave = dailySave;
        this.init = init;
    }

    public static void main(String[] args) {
        SpringApplication.run(Exercice3Application.class, args);
    }

    @PostConstruct
    public void performInitialJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(init,jobParameters);
    }

    @Scheduled(fixedRate = 20000)
    public void performDailySaveJob() throws Exception{
        System.out.println("daily save job");
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(dailySave,jobParameters);
    }
}
