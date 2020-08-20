package service.bottlingServiceImplementation;

import entity.beerpackages.BeerPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.BottlingService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BottlingServiceImplementationTest {

    BottlingService bottlingService = new BottlingServiceImplementation();

    @Test
    void bottling() {
        double volume = 0.5;
        double liquidVolume = 2.0;
        int numberOfBottles = 4;

        List<BeerPackage> result = bottlingService.bottling(volume, liquidVolume);
        Assertions.assertEquals(numberOfBottles ,result.size());
    }
}