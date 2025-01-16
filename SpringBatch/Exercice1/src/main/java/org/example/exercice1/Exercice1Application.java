package org.example.exercice1;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Exercice1Application {
	private final JobLauncher jobLauncher;
	private final Job importJob;

	public Exercice1Application(JobLauncher jobLauncher, Job importJob) {
		this.jobLauncher = jobLauncher;
		this.importJob = importJob;
	}
	public static void main(String[] args) {
		SpringApplication.run(Exercice1Application.class, args);
	}

	@Scheduled(fixedRate = 20000)
	public void performJob() throws Exception{
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("time",System.currentTimeMillis())
				.toJobParameters();

		jobLauncher.run(importJob,jobParameters);
	}
}
