package service;

import entity.beerComponents.BeerComponent;

public interface TemperatureService {

    BeerComponent heating(BeerComponent beerComponent, double finalTemperature, double speed) throws InterruptedException;

    BeerComponent cooling(BeerComponent beerComponent, double finalTemperature, double speed) throws InterruptedException;

    BeerComponent keepTemperature(BeerComponent beerComponent, int minutes) throws InterruptedException;

    BeerComponent setTemperature(BeerComponent beerComponent, double temperature);
}
