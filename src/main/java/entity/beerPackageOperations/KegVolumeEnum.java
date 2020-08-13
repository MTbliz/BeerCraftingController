package entity.beerPackageOperations;

public enum KegVolumeEnum {

    TWENTY_LITERS(20), THIRTY_LITERS(30);

    double volume;

    KegVolumeEnum(double volume) {
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }
}
