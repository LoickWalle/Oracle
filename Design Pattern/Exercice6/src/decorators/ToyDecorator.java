package decorators;

import interfaces.IToy;

public abstract class ToyDecorator implements IToy {
    protected IToy toy;

    public ToyDecorator(IToy toy){
        this.toy = toy;
    }

    @Override
    public void makeSound() {
        toy.makeSound();
    }

    @Override
    public String getDescription() {
        return toy.getDescription();
    }
}
