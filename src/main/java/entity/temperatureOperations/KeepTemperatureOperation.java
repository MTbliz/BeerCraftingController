package entity.temperatureOperations;

import entity.beerComponents.BeerComponent;
import service.TemperatureService;
import service.temperatureServiceImplementation.TemperatureServiceImplementation;

public class KeepTemperatureOperation extends TemperatureOperation {
    Long id;
    private TemperatureService temperatureService = new TemperatureServiceImplementation();
    private String operationName = "KeepTemperature";
    private int minutes;
    String operationType;
    Long program_id;

    public KeepTemperatureOperation() {
    }

    public KeepTemperatureOperation(Long id, int minutes, String operationType, Long program_id) {
        this.operationType = operationType;
        this.id = id;
        this.minutes = minutes;
        this.program_id = program_id;
    }

    public KeepTemperatureOperation(int minutes, String operationType) {
        this.minutes = minutes;
        this.operationType = operationType;
    }

    public void setProgram_id(Long program_id) {
        this.program_id = program_id;
    }

    public Long getId() {
        return id;
    }

    public int getMinutes() {
        return minutes;
    }

    public Long getProgram_id() {
        return program_id;
    }

    public String getOperationType() {
        return operationType;
    }

    @Override
    public String getOperationName() {
        return operationName;
    }

    @Override
    public BeerComponent runProgram(BeerComponent beerComponent) throws InterruptedException {
        return temperatureService.keepTemperature(beerComponent, this.getMinutes());
    }

    @Override
    public String toString() {
        return "KeepTemperatureOperation{" +
                "operationName='" + operationName + '\'' +
                ", minutes=" + minutes +
                '}';
    }
}
