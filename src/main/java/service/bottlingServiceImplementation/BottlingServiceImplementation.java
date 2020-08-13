package service.bottlingServiceImplementation;

import entity.beerpackages.BeerPackage;
import entity.beerpackages.BottleOfBeer;
import service.BottlingService;

import java.util.ArrayList;
import java.util.List;

public class BottlingServiceImplementation implements BottlingService {

    @Override
    public List<BeerPackage> bottling(double volume, double liquidVolume) {
        List<BeerPackage> beerPackages = new ArrayList<>();
        int numberOfBottles = (int) (liquidVolume / volume);
        for (int i = 0; i < numberOfBottles; i++) {
            beerPackages.add(new BottleOfBeer());
        }
        System.out.println("Bottled to " + beerPackages.size() + " " + volume + "L bottles.");
        return beerPackages;
    }
}
