package factories;

import interfaces.IToy;
import models.Ball;

public class BallFactory extends ToyFactory{

    @Override
    public IToy createToy(String name) {
        return new Ball(name);
    }
}
