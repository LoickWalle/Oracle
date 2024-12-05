import decorators.BlueDecorator;
import decorators.GreenDecorator;
import decorators.RedDecorator;
import factories.BallFactory;
import factories.NerfGunFactory;
import factories.RadioCarFactory;
import factories.ToyFactory;
import interfaces.IToy;
import models.Lutin;
import models.LutinCommCenter;

public class Main {
    public static void main(String[] args) {
        LutinCommCenter lCC = new LutinCommCenter();
        Lutin lutin1 = new Lutin("Bob");
        Lutin lutin2 = new Lutin("Isabeau");
        Lutin lutin3 = new Lutin("Gaspar");

        lCC.addLutin(lutin1);
        lCC.addLutin(lutin2);
        lCC.addLutin(lutin3);

        ToyFactory ballFactory = new BallFactory();
        IToy ball = ballFactory.createToy("Basket Ball");
        lutin1.makeToy(ball, lCC);

        ToyFactory nerfGunFactory = new NerfGunFactory();
        IToy nerfGun = nerfGunFactory.createToy("Nerf Minigun");
        lutin2.makeToy(nerfGun, lCC);

        ToyFactory radioCarFactory = new RadioCarFactory();
        IToy radioCar = radioCarFactory.createToy("Formule 1");
        lutin3.makeToy(radioCar, lCC);

        System.out.println(ball.getDescription());
        IToy redBall = new RedDecorator(ball);
        System.out.println(redBall.getDescription());

        System.out.println(nerfGun.getDescription());
        IToy greenNerfGun = new GreenDecorator(nerfGun);
        System.out.println(greenNerfGun.getDescription());

        System.out.println(radioCar.getDescription());
        IToy blueRadioCar = new BlueDecorator(radioCar);
        System.out.println(blueRadioCar.getDescription());
    }
}