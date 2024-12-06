package models;

import interfaces.ICommand;

public class AddSong implements ICommand {
    private Playlist playlist;

    public AddSong(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public void execute(Song song) {
        playlist.addSong(song);
    }
}