package service;

import entity.beerpackages.BeerPackage;

import java.util.List;

public interface KeggingService {

    List<BeerPackage> Kegging(double volume, double liquidVolume);
}
