package models;

import interfaces.ILutin;

import java.util.ArrayList;
import java.util.List;

public class LutinCommCenter {

    private List<ILutin> allLutins = new ArrayList<>();

    public void addLutin(ILutin lutin){
        allLutins.add(lutin);
    }

    public void removeLutin(ILutin lutin){
        allLutins.remove(lutin);
    }

    public void notifyOtherLutin(String message, Lutin lutinSending){
        for (ILutin lutin: allLutins){
            if(!(lutinSending == lutin))
                lutin.notification(message);
        }
    }
}
