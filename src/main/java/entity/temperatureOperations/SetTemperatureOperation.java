package entity.temperatureOperations;

import entity.beerComponents.BeerComponent;
import service.TemperatureService;

public class SetTemperatureOperation extends TemperatureOperation {
    Long id;
    private TemperatureService temperatureService;
    String operationName = "SetTemperature";
    double temperature;
    String operationType;
    Long program_id;

    public SetTemperatureOperation() {
    }

    public SetTemperatureOperation(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    public SetTemperatureOperation(double temperature, String operationType, TemperatureService temperatureService) {
        this.temperature = temperature;
        this.operationType = operationType;
        this.temperatureService = temperatureService;
    }

    public SetTemperatureOperation(Long id, double temperature, String operationType, Long program_id, TemperatureService temperatureService) {
        this.operationType = operationType;
        this.id = id;
        this.temperature = temperature;
        this.program_id = program_id;
        this.temperatureService = temperatureService;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public void setProgram_id(Long program_id) {
        this.program_id = program_id;
    }

    public double getTemperature() {
        return temperature;
    }

    public Long getId() {
        return id;
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
        return temperatureService.setTemperature(beerComponent, this.getTemperature());
    }

    @Override
    public String toString() {
        return "SetTemperatureOperation{" +
                "operationName='" + operationName + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}
