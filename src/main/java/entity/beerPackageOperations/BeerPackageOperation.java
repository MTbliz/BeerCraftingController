package entity.beerPackageOperations;

import entity.beerComponents.BeerComponent;
import entity.beerpackages.BeerPackage;

import java.util.List;

public abstract class BeerPackageOperation {

    String operationName;

    Long program_id;

    public String getOperationName() {
        return operationName;
    }

    public Long getProgram_id() {
        return program_id;
    }

    public void setProgram_id(Long program_id) {
        this.program_id = program_id;
    }

    public abstract List<BeerPackage> runProgram(BeerComponent beerComponent, double liquidVolume) throws  InterruptedException;
}

