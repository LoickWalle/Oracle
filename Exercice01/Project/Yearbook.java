package Project; 

import Project.Person; 
import java.util.List;
import java.util.ArrayList;

public class Yearbook{
    List<Person> allPersons; 

    public Yearbook(){
        allPersons = new ArrayList<>(); 
    }

    public List<Person> getAllPersons(){
        return this.allPersons; 
    }

    public void addPerson(Person newPerson){
        allPersons.add(newPerson);  
    }

    public String toString(){
        String allPersonsDisplay = "";
        for(Person person : allPersons){
            allPersonsDisplay += person.toString(); 
        }

        return  allPersonsDisplay; 
    }
}