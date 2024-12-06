package interfaces;

import models.Song;

public interface ICommand {
    void execute(Song song);
}
