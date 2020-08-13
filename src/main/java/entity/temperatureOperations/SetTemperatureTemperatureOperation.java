package entity.temperatureOperations;

import entity.beerComponents.BeerComponent;
import service.TemperatureService;
import service.temperatureServiceImplementation.TemperatureServiceImplementation;

public class SetTemperatureTemperatureOperation extends TemperatureOperation {

    private TemperatureService temperatureService = new TemperatureServiceImplementation();
    String operationName = "SetTemperature";
    double temperature;

    public SetTemperatureTemperatureOperation(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
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
