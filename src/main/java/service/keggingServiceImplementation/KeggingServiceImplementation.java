package service.keggingServiceImplementation;

import entity.beerpackages.BeerPackage;
import entity.beerpackages.KegOfBeer;
import service.KeggingService;

import java.util.ArrayList;
import java.util.List;

public class KeggingServiceImplementation implements KeggingService {

    @Override
    public List<BeerPackage> Kegging(double volume, double liquidVolume) {
        List<BeerPackage> beerPackages = new ArrayList<>();
        int numberOfKegs = (int) (liquidVolume / volume);
        for (int i = 0; i < numberOfKegs; i++) {
            beerPackages.add(new KegOfBeer());
        }
        System.out.println("Kegged to " + beerPackages.size() + " " + volume + "L Kegs.");
        return beerPackages;
    }
}
