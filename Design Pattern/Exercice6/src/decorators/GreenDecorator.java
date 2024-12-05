package decorators;

import interfaces.IToy;

public class GreenDecorator  extends ToyDecorator{
    public GreenDecorator(IToy toy) {
        super(toy);
    }

    @Override
    public String getDescription() {
        return toy.getDescription() + " The toy is green !";
    }
}