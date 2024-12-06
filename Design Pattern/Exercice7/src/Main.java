import enums.RoofType;
import models.House;

public class Main {
    public static void main(String[] args) {

        House modernHouse = new House.ModernBuilder().setFloorNumber(3).setHavePool(true).setRoofType(RoofType.FLAT).build() ;

        System.out.println(modernHouse);

        House traditionalHouse = new House.TraditionalBuilder().setFloorNumber(1).setRoofType(RoofType.JAPANESE).build() ;

        System.out.println(traditionalHouse);
    }
}