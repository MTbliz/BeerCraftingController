package entity.beerPackageOperations;

import entity.beerComponents.BeerComponent;
import entity.beerpackages.BeerPackage;
import service.KeggingService;
import service.keggingServiceImplementation.KeggingServiceImplementation;

import java.util.List;

public class FillKeg extends BeerPackageOperation {

    private KeggingService keggingService = new KeggingServiceImplementation();
    private String operationName = "Bottling";
    private double volume;

    public FillKeg(KegVolumeEnum kegVolumeEnum) {
        this.volume = kegVolumeEnum.getVolume();
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
        List<BeerPackage> beerPackages = keggingService.Kegging(this.getVolume(), liquidVolume);
        return beerPackages;
    }

    @Override
    public String toString() {
        return "FillKeg{" +
                "operationName='" + operationName + '\'' +
                ", volume=" + volume +
                '}';
    }
}
