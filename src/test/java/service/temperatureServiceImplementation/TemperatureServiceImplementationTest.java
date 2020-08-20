package service.temperatureServiceImplementation;

import entity.beerComponents.BeerComponent;
import entity.beerComponents.Mash;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.TemperatureService;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureServiceImplementationTest {

    private TemperatureService temperatureService = new TemperatureServiceImplementation();

    private BeerComponent beerComponent;

    @BeforeEach
    void setUp() {
        beerComponent = new Mash();
        beerComponent.setTemperature(50.0);
    }

    @Test
    void heating() throws InterruptedException {
        double finalTemperature = 51.0;
        double speed = 1000.0;
        Mash mash = new Mash();
        mash.setTemperature(51.0);

        BeerComponent result = temperatureService.heating(beerComponent, finalTemperature, speed);
        Assertions.assertEquals(mash.getTemperature(), result.getTemperature(), 0.5);
    }

    @Test
    void cooling() throws InterruptedException {
        double finalTemperature = 49.0;
        double speed = 1000.0;
        Mash mash = new Mash();
        mash.setTemperature(finalTemperature);
        BeerComponent result = temperatureService.cooling(beerComponent, finalTemperature, speed);
        Assertions.assertEquals(mash.getTemperature(), result.getTemperature(), 0.5);
    }

    @Test
    void setTemperatureFirstUse() throws InterruptedException {
        //clear temperature
        beerComponent.setTemperature(0.0);

        double temperature = 200.0;
        Mash mash = new Mash();
        mash.setTemperature(temperature);
        BeerComponent result = temperatureService.setTemperature(beerComponent, temperature);
        Assertions.assertEquals(mash.getTemperature(), result.getTemperature(), 0.5);
    }

    @Test
    void setTemperatureSecondUseDoesntChangeTemperature() throws InterruptedException {
        //clear temperature
        beerComponent.setTemperature(0.0);

        double firstTemperature = 100.0;
        double secondTemperature = 200.0;

        BeerComponent beerComponentAfterFirstUse = temperatureService.setTemperature(beerComponent, firstTemperature);
        BeerComponent beerComponentAfterSecondUse = temperatureService.setTemperature(beerComponentAfterFirstUse, secondTemperature);
        Assertions.assertEquals(firstTemperature, beerComponentAfterSecondUse.getTemperature(), 0.5);
    }

}