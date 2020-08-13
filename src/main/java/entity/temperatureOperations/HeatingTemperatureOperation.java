package entity.temperatureOperations;

import entity.beerComponents.BeerComponent;
import service.TemperatureService;
import service.temperatureServiceImplementation.TemperatureServiceImplementation;

public class HeatingTemperatureOperation extends TemperatureOperation {

    private TemperatureService temperatureService = new TemperatureServiceImplementation();
    private String operationName = "Heating";
    private double finalTemperature;
    private double speed;

    public HeatingTemperatureOperation(double finalTemperature, double speed) {
        this.finalTemperature = finalTemperature;
        this.speed = speed;
    }

    public double getFinalTemperature() {
        return finalTemperature;
    }

    public double getSpeed() {
        return speed;
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
