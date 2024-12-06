package models;

public class Song {
    private String name;
    private String groupName;

    public Song(String name, String groupName) {
        this.name = name;
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "This song's name is " + name + " and it's group is "+ groupName;
    }
}
