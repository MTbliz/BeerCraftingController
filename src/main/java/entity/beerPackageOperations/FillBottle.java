package entity.beerPackageOperations;

import entity.beerComponents.BeerComponent;
import entity.beerpackages.BeerPackage;
import service.BottlingService;
import service.bottlingServiceImplementation.BottlingServiceImplementation;

import java.util.List;

public class FillBottle extends BeerPackageOperation {

    private BottlingService bottlingService = new BottlingServiceImplementation();
    private String operationName = "Bottling";
    private double volume;

    public FillBottle(BottleVolumeEnum bottleVolumeEnum) {
        this.volume = bottleVolumeEnum.getVolume();
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public String getOperationName() {
        return operationName;
    }

    @Override
    public List<BeerPackage> runProgram(BeerComponent beerComponent, double liquidVolume) throws InterruptedException {
      List<BeerPackage> beerPackages = bottlingService.bottling(this.getVolume(), liquidVolume);
        return beerPackages;
    }

    @Override
    public String toString() {
        return "FillBottle{" +
                "operationName='" + operationName + '\'' +
                ", volume=" + volume +
                '}';
    }
}
