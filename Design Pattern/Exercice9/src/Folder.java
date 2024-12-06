import interfaces.IComponent;

import java.util.ArrayList;
import java.util.List;

public class Folder implements IComponent {
    private String name;
    private List<IComponent> componentList = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(IComponent component){
        componentList.add(component);
    }

    public void remove(IComponent component){
        componentList.remove(component);
    }

    @Override
    public void operation() {
        System.out.println("Folder : " + name);
        for(IComponent iComponent : componentList){
            iComponent.operation();
        }
    }
}
