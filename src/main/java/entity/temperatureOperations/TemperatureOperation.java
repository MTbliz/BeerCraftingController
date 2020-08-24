package entity.temperatureOperations;

import entity.beerComponents.BeerComponent;

public abstract class TemperatureOperation {

    Long id;
    String operationName;
    Long program_id;

    public String getOperationName() {
        return operationName;
    }

    public Long getId() {
        return id;
    }

    public Long getProgram_id() {
        return program_id;
    }

    public void setProgram_id(Long program_id) {
        this.program_id = program_id;
    }

    public abstract BeerComponent runProgram(BeerComponent beerComponent) throws InterruptedException;
}

