package org.example.exercice13.service;

import org.example.exercice13.utils.SongGenre;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class PlaylistServiceTest {
    private final PlaylistService playlistService = new PlaylistService();

    @Test
    public void testGetPlaylist_WhenGenreExists_AndIsRock_ReturnsPlaylist() {
        StepVerifier.create(playlistService.getPlaylist("rock"))
                .expectSubscription()
                .expectNext("Hotel California", "Stairway to Heaven", "Sweet Child O' Mine", "Back in Black", "Smoke on the Water")
                .verifyComplete();
    }

    @Test
    public void testGetPlaylist_WhenGenreExists_AndIsMetal_ReturnsMetalSongs() {
        StepVerifier.create(playlistService.getPlaylist("metal"))
                .expectSubscription()
                .expectNext("Enter Sandman", "Iron Man", "Master of Puppets", "Painkiller", "The Trooper")
                .verifyComplete();
    }


    @Test
    public void testGetPlaylist_WhenWrongGenre_ReturnsException() {
        StepVerifier.create(playlistService.getPlaylist("casserole"))
                .expectSubscription()
                .expectErrorMatches(throwable -> throwable instanceof IllegalArgumentException
                        && throwable.getMessage().equalsIgnoreCase("Genre incorrect"))
                .verify();
    }

}
