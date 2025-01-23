package org.example.exercice13.entity;

import org.example.exercice13.utils.SongGenre;

import java.util.UUID;

public class Song {
    private UUID id;
    private String title;
    private SongGenre genre;

    public Song() {
    }

    public Song(String title, SongGenre genre) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public SongGenre getGenre() {
        return genre;
    }
}
