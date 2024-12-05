package factories;

import interfaces.IToy;
import models.RadioCar;

public class RadioCarFactory extends ToyFactory{
    @Override
    public IToy createToy(String name) {
        return new RadioCar(name);
    }
}
