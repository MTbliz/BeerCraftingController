package entity.temperatureOperations;

import entity.beerComponents.BeerComponent;
import service.TemperatureService;
import service.temperatureServiceImplementation.TemperatureServiceImplementation;

public class HeatingTemperatureOperation extends TemperatureOperation {
    Long id;
    private TemperatureService temperatureService = new TemperatureServiceImplementation();
    private String operationName = "Heating";
    private double finalTemperature;
    private double speed;
    String operationType;
    Long program_id;

    public HeatingTemperatureOperation(){}

    public HeatingTemperatureOperation(double finalTemperature, double speed, String operationType) {
        this.finalTemperature = finalTemperature;
        this.speed = speed;
        this.operationType = operationType;
    }

    public HeatingTemperatureOperation(Long id, double finalTemperature, double speed, String operationType, Long program_id) {
        this.id = id;
        this.finalTemperature = finalTemperature;
        this.speed = speed;
        this.operationType = operationType;
        this.program_id = program_id;
    }

    public void setProgram_id(Long program_id) {
        this.program_id = program_id;
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

    @Override
    public String getOperationName() {
        return operationName;
    }

    @Override
    public BeerComponent runProgram(BeerComponent beerComponent) throws InterruptedException {
        return temperatureService.heating(beerComponent, this.getFinalTemperature(), this.getSpeed());
    }

    @Override
    public String toString() {
        return "HeatingOperation{" +
                "operationName='" + operationName + '\'' +
                ", finalTemperature=" + finalTemperature +
                ", speed=" + speed +
                '}';
    }
}
