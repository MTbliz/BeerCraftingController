package entity;

import entity.beerPackageOperations.BeerPackageOperation;
import entity.temperatureOperations.TemperatureOperation;

import java.util.List;

public class Program {

    Long id;

    List<TemperatureOperation> mashingOperations;

    List<TemperatureOperation> fermentationOperations;

    List<BeerPackageOperation> beerPackageOperations;

    public Program(){}

    public Program(List<TemperatureOperation> mashingOperations, List<TemperatureOperation> fermentationOperations, List<BeerPackageOperation> beerPackageOperations) {
        this.mashingOperations = mashingOperations;
        this.fermentationOperations = fermentationOperations;
        this.beerPackageOperations = beerPackageOperations;
    }

    public Long getId() {
        return id;
    }

    public List<TemperatureOperation> getMashingOperations() {
        return mashingOperations;
    }

    public List<TemperatureOperation> getFermentationOperations() {
        return fermentationOperations;
    }

    public List<BeerPackageOperation> getBeerPackageOperations() {
        return beerPackageOperations;
    }
}
