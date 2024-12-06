package models;

import enums.HouseType;
import enums.RoofType;

public class House {
    private int floorNumber;
    private HouseType houseType;
    private RoofType roofType;
    private boolean havePool;

    public House() {
    }

    public House(int floorNumber, HouseType houseType, RoofType roofType, boolean havePool) {
        this.floorNumber = floorNumber;
        this.houseType = houseType;
        this.roofType = roofType;
        this.havePool = havePool;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public RoofType getRoofType() {
        return roofType;
    }

    public boolean isHavePool() {
        return havePool;
    }

    @Override
    public String toString() {
        return "This house is a " + houseType.toString().toLowerCase() + " type.\n"
                + "It's have " + floorNumber + " floor and have a "
                + roofType.toString().toLowerCase() + " roof type.\n"
                + "This house is giving " + (isHavePool() ? "with" : "without") + " pool.\n";
    }

    public static class ModernBuilder {
        private int floorNumber;
        private RoofType roofType;
        private boolean havePool;

        public ModernBuilder() {
        }

        public ModernBuilder setFloorNumber(int floorNumber) {
            this.floorNumber = floorNumber;
            return this;
        }

        public ModernBuilder setRoofType(RoofType roofType) {
            this.roofType = roofType;
            return this;
        }

        public ModernBuilder setHavePool(boolean havePool) {
            this.havePool = havePool;
            return this;
        }

        public House build(){
            return new House(this.floorNumber, HouseType.MODERN, this.roofType, this.havePool);
        }
    }

    public static class TraditionalBuilder {
        private int floorNumber;
        private RoofType roofType;
        private boolean havePool;

        public TraditionalBuilder() {
        }

        public TraditionalBuilder setFloorNumber(int floorNumber) {
            this.floorNumber = floorNumber;
            return this;
        }

        public TraditionalBuilder setRoofType(RoofType roofType) {
            this.roofType = roofType;
            return this;
        }

        public TraditionalBuilder setHavePool(boolean havePool) {
            this.havePool = havePool;
            return this;
        }

        public House build(){
            return new House(this.floorNumber, HouseType.TRADITIONAL, this.roofType, this.havePool);
        }
    }
}
