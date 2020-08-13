package service;

import entity.beerpackages.BeerPackage;

import java.util.List;

public interface BottlingService {

    List<BeerPackage> bottling(double volume, double liquidVolume);
}
