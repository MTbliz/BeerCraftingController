package entity;

import entity.beerPackageOperations.BeerPackageOperation;
import entity.temperatureOperations.TemperatureOperation;

import java.util.List;

public class Program {

    Long id;

    String programName;

    List<TemperatureOperation> mashingOperations;

    List<TemperatureOperation> fermentationOperations;

    List<BeerPackageOperation> beerPackageOperations;

    public Program() {
    }

    public Program(String programName, List<TemperatureOperation> mashingOperations, List<TemperatureOperation> fermentationOperations, List<BeerPackageOperation> beerPackageOperations) {
        this.programName = programName;
        this.mashingOperations = mashingOperations;
        this.fermentationOperations = fermentationOperations;
        this.beerPackageOperations = beerPackageOperations;
    }

    public Program(Long id, String programName, List<TemperatureOperation> mashingOperations, List<TemperatureOperation> fermentationOperations, List<BeerPackageOperation> beerPackageOperations) {
        this.id = id;
        this.programName = programName;
        this.mashingOperations = mashingOperations;
        this.fermentationOperations = fermentationOperations;
        this.beerPackageOperations = beerPackageOperations;
    }

    public Long getId() {
        return id;
    }

    public String getProgramName() {
        return programName;
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

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", programName='" + programName + '\'' +
                ", mashingOperations=" + mashingOperations +
                ", fermentationOperations=" + fermentationOperations +
                ", beerPackageOperations=" + beerPackageOperations +
                '}';
    }
}
