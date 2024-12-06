package models;

import interfaces.ICommand;

public class RemoveSong implements ICommand {
    private Playlist playlist;

    public RemoveSong(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public void execute(Song song) {
        playlist.removeSong(song);
    }
}
