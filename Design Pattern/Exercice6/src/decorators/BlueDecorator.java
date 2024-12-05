package decorators;

import interfaces.IToy;

public class BlueDecorator extends ToyDecorator{
    public BlueDecorator(IToy toy) {
        super(toy);
    }

    @Override
    public String getDescription() {
        return toy.getDescription() + " The toy is blue !";
    }
}