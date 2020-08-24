package entity.temperatureOperations;

import entity.beerComponents.BeerComponent;
import service.TemperatureService;

public class CoolingTemperatureOperation extends TemperatureOperation {
    Long id;
    TemperatureService temperatureService;
    String operationName = "Cooling";
    double finalTemperature;
    double speed;
    String operationType;
    Long program_id;

    public CoolingTemperatureOperation() {
    }

    public CoolingTemperatureOperation(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    public CoolingTemperatureOperation(double finalTemperature, double speed, String operationType, TemperatureService temperatureService) {
        this.finalTemperature = finalTemperature;
        this.speed = speed;
        this.operationType = operationType;
        this.temperatureService = temperatureService;
    }

    public CoolingTemperatureOperation(Long id, double finalTemperature, double speed, String operationType, Long program_id, TemperatureService temperatureService) {
        this.operationType = operationType;
        this.id = id;
        this.finalTemperature = finalTemperature;
        this.speed = speed;
        this.program_id = program_id;
        this.temperatureService = temperatureService;
    }

    public Long getId() {
        return id;
    }

    public double getFinalTemperature() {
        return finalTemperature;
    }

    public double getSpeed() {
        return speed;
    }

    public Long getProgram_id() {
        return program_id;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public void setProgram_id(Long program_id) {
        this.program_id = program_id;
    }

    @Override
    public String getOperationName() {
        return operationName;
    }

    @Override
    public BeerComponent runProgram(BeerComponent beerComponent) throws InterruptedException {
        return temperatureService.cooling(beerComponent, this.getFinalTemperature(), this.getSpeed());
    }

    @Override
    public String toString() {
        return "CoolingOperation{" +
                "operationName='" + operationName + '\'' +
                ", finalTemperature=" + finalTemperature +
                ", speed=" + speed +
                '}';
    }
}
