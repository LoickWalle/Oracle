package factories;

import interfaces.IToy;

public abstract class ToyFactory {
    public abstract IToy createToy(String name);
}
