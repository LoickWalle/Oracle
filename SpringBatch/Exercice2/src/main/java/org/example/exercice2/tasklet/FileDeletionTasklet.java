package org.example.exercice2.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileDeletionTasklet implements Tasklet {

    private final String filePath;

    public FileDeletionTasklet() {
        this.filePath = "games.csv";
    }
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        File file = new File(filePath);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                throw new RuntimeException("Failed to delete file: " + filePath);
            }
        }
        return RepeatStatus.FINISHED;
    }
}
