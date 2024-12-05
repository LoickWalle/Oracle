package decorators;

import interfaces.IToy;

public class RedDecorator extends ToyDecorator{
    public RedDecorator(IToy toy) {
        super(toy);
    }

    @Override
    public String getDescription() {
        return toy.getDescription() + " The toy is red !";
    }
}
