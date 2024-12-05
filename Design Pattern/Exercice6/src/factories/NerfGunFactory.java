package factories;

import interfaces.IToy;
import models.NerfGun;

public class NerfGunFactory extends ToyFactory{
    @Override
    public IToy createToy(String name) {
        return new NerfGun(name);
    }
}
