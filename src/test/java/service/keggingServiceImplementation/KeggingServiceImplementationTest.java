package service.keggingServiceImplementation;

import entity.beerpackages.BeerPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.BottlingService;
import service.KeggingService;
import service.bottlingServiceImplementation.BottlingServiceImplementation;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KeggingServiceImplementationTest {

    KeggingService keggingService = new KeggingServiceImplementation();

    @Test
    void kegging() {
        double volume = 20.0;
        double liquidVolume = 40.0;
        int numberOfKegs = 2;

        List<BeerPackage> result = keggingService.Kegging(volume, liquidVolume);
        Assertions.assertEquals(numberOfKegs ,result.size());
    }
}