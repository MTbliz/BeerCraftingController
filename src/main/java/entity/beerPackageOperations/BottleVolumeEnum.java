package entity.beerPackageOperations;

public enum BottleVolumeEnum {

    THREE_HUNDRED_MILLILITERS(0.3), FIVE_HUNDRED_MILLILITERS(0.5);

    double volume;

    BottleVolumeEnum(double volume) {
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }
}
