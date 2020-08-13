package service.temperatureServiceImplementation;

import entity.beerComponents.BeerComponent;
import service.TemperatureService;

import java.time.LocalDateTime;

public class TemperatureServiceImplementation implements TemperatureService {

    @Override
    public BeerComponent heating(BeerComponent beerComponent, double finalTemperature, double speed) throws InterruptedException {
        double startTemperature = beerComponent.getTemperature();
        while (startTemperature <= finalTemperature) {
            startTemperature = startTemperature + speed / 3600;
            System.out.println(startTemperature);
            Thread.sleep(1000);
        }
        beerComponent.setTemperature(startTemperature);
        return beerComponent;
    }

    @Override
    public BeerComponent cooling(BeerComponent beerComponent, double finalTemperature, double speed) throws InterruptedException {
        double startTemperature = beerComponent.getTemperature();
        while (startTemperature >= finalTemperature) {
            startTemperature = startTemperature - speed / 3600;
            System.out.println(startTemperature);
            Thread.sleep(1000);
        }
        beerComponent.setTemperature(startTemperature);
        return beerComponent;
    }

    @Override
    public BeerComponent keepTemperature(BeerComponent beerComponent, int minutes) throws InterruptedException {
        double deviation = 0.5;
        double temperatureChange = 0.5;

        LocalDateTime startTime = LocalDateTime.now();
        double startTemperature = beerComponent.getTemperature();
        while (startTime.plusMinutes(minutes).isAfter(LocalDateTime.now())) {
            double currentTemperature = beerComponent.getTemperature();

            if (currentTemperature < startTemperature - deviation) {
                beerComponent.setTemperature(currentTemperature + temperatureChange);
                System.out.println("+ " + temperatureChange);
            } else if (currentTemperature > startTemperature + deviation) {
                beerComponent.setTemperature(currentTemperature - temperatureChange);
                System.out.println("- " + temperatureChange);
            }
            Thread.sleep(5000);
        }
        return beerComponent;
    }

    @Override
    public BeerComponent setTemperature(BeerComponent beerComponent, double temperature) throws InterruptedException {
        beerComponent.setTemperature(temperature);
        return beerComponent;
    }
}
