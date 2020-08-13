package entity.beerPackageOperations;

import entity.beerComponents.BeerComponent;
import entity.beerpackages.BeerPackage;

import java.util.List;

public abstract class BeerPackageOperation {

    String operationName;

    public String getOperationName() {
        return operationName;
    }

    public abstract List<BeerPackage> runProgram(BeerComponent beerComponent, double liquidVolume) throws  InterruptedException;
}

