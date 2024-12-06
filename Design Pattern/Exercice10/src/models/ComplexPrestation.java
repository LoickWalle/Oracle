package models;

import interfaces.IPrestation;

import java.util.ArrayList;
import java.util.List;

public class ComplexPrestation implements IPrestation {
    private String name;
    private List<IPrestation> prestationList = new ArrayList<>();

    public ComplexPrestation(String name) {
        this.name = name;
    }

    public void addSimplePrestation(interfaces.IPrestation simplePrestation){
        prestationList.add(simplePrestation);
    }

    public void removeSimplePrestation(interfaces.IPrestation simplePrestation){
        prestationList.remove(simplePrestation);
    }

    @Override
    public void showDetails(int spaceNb) {
        for(int i = 0; i < spaceNb; i++){
            System.out.print("\t");
        }
        System.out.println("Complex prestation named "+ name + " including : ");
        for (interfaces.IPrestation prestation : prestationList){
            prestation.showDetails(spaceNb+1);
        }
    }
}
