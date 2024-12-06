import interfaces.ICommand;
import models.*;

public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        ICommand addSong = new AddSong(playlist);
        ICommand removeSong = new RemoveSong(playlist);

        Song song1 = new Song("One", "Metallica");
        Song song2 = new Song("Sinners", "Drowning pool");

        PlaylistController playlistAddController = new PlaylistController(addSong);
        playlistAddController.songSelected(song1);
        playlistAddController.songSelected(song2);

        playlist.displayAllSongs();

        PlaylistController playlistDeleteController = new PlaylistController(removeSong);
        playlistDeleteController.songSelected(song2);

        playlist.displayAllSongs();
    }
}