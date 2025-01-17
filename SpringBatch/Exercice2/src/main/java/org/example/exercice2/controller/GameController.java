package org.example.exercice2.controller;

import org.example.exercice2.entity.Game;
import org.example.exercice2.service.GameService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/game")
public class GameController {

    private final GameService gameService;
    private final JobLauncher jobLauncher;
    private final Job importJob;

    public GameController(JobLauncher jobLauncher, Job importJob, GameService gameService) {
        this.jobLauncher = jobLauncher;
        this.importJob = importJob;
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getAllGames() {
        return this.gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable("id") int id) {
        return this.gameService.getGameById(id);
    }

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return this.gameService.addGame(game);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteGame(@PathVariable int id) {
        return this.gameService.deleteGameById(id);
    }

    @GetMapping("/uploadCSV")
    public void saveCSV () throws Exception{
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(importJob,jobParameters);
    }
}
