import interfaces.IComponent;

public class File implements IComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("File : " + name);
    }
}
