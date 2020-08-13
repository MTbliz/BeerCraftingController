package entity.temperatureOperations;

import entity.beerComponents.BeerComponent;
import service.TemperatureService;
import service.temperatureServiceImplementation.TemperatureServiceImplementation;

public class KeepTemperatureTemperatureOperation extends TemperatureOperation {

    private TemperatureService temperatureService = new TemperatureServiceImplementation();
    private String operationName = "KeepTemperature";
    private int minutes;

    public KeepTemperatureTemperatureOperation(int minutes) {
        this.minutes = minutes;
    }

    public int getMinutes() {
        return minutes;
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
