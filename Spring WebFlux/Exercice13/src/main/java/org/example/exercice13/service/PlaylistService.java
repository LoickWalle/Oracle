package org.example.exercice13.service;

import org.example.exercice13.entity.Song;
import org.example.exercice13.utils.SongGenre;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class PlaylistService {
    private final List<Song> songs = new ArrayList<>();

    public PlaylistService() {
          initList();
    }

    public Flux<String> getPlaylist(String genre) {
        return Flux.fromIterable(songs)
                .filter(s -> s.getGenre().toString().equalsIgnoreCase(genre))
                .switchIfEmpty(Flux.error(new IllegalArgumentException("Genre incorrect")))
                .map(Song::getTitle);
    }

    private void initList(){
        songs.add(new Song("Hotel California", SongGenre.ROCK));
        songs.add(new Song("Stairway to Heaven", SongGenre.ROCK));
        songs.add(new Song("Sweet Child O' Mine", SongGenre.ROCK));
        songs.add(new Song("Back in Black", SongGenre.ROCK));
        songs.add(new Song("Smoke on the Water", SongGenre.ROCK));

        songs.add(new Song("Thriller", SongGenre.POP));
        songs.add(new Song("Like a Virgin", SongGenre.POP));
        songs.add(new Song("Billie Jean", SongGenre.POP));
        songs.add(new Song("Uptown Funk", SongGenre.POP));
        songs.add(new Song("Shape of You", SongGenre.POP));

        songs.add(new Song("Imagine", SongGenre.CLASSIC));
        songs.add(new Song("Let It Be", SongGenre.CLASSIC));
        songs.add(new Song("Hey Jude", SongGenre.CLASSIC));
        songs.add(new Song("Yesterday", SongGenre.CLASSIC));
        songs.add(new Song("What a Wonderful World", SongGenre.CLASSIC));

        songs.add(new Song("Enter Sandman", SongGenre.METAL));
        songs.add(new Song("Iron Man", SongGenre.METAL));
        songs.add(new Song("Master of Puppets", SongGenre.METAL));
        songs.add(new Song("Painkiller", SongGenre.METAL));
        songs.add(new Song("The Trooper", SongGenre.METAL));
    }
}
