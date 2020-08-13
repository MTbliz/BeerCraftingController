package entity.temperatureOperations;

import entity.beerComponents.BeerComponent;

public abstract class TemperatureOperation {
    String operationName;

    public String getOperationName() {
        return operationName;
    }

    public abstract BeerComponent runProgram(BeerComponent beerComponent) throws  InterruptedException;
    }

