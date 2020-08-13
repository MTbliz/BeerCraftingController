package entity.temperatureOperations;

import entity.beerComponents.BeerComponent;
import service.TemperatureService;
import service.temperatureServiceImplementation.TemperatureServiceImplementation;

public class CoolingTemperatureOperation extends TemperatureOperation {

    TemperatureService temperatureService = new TemperatureServiceImplementation();
    String operationName = "Cooling";
    double finalTemperature;
    double speed;

    public CoolingTemperatureOperation(double finalTemperature, double speed) {
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
