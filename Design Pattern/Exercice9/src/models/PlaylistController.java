package models;

import interfaces.ICommand;

public class PlaylistController {
    private ICommand command;

    public PlaylistController(ICommand command) {
        this.command = command;
    }

    public void songSelected(Song song){
        if(command != null){
           command.execute(song);
        }else {
            System.out.println("Aucune commande assigné à ce bouton.");
        }
    }
}
