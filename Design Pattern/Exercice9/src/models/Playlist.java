package models;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    List<Song> playlist = new ArrayList<>();

    public void addSong(Song song){
        playlist.add(song);
    }

    public void removeSong(Song song){
        playlist.remove(song);
    }

    public void displayAllSongs(){
        for(Song song : playlist){
            System.out.println(song);
        }
    }
}
