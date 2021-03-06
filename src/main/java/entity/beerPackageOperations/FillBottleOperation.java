package entity.beerPackageOperations;

import entity.beerComponents.BeerComponent;
import entity.beerpackages.BeerPackage;
import service.BottlingService;

import java.util.List;

public class FillBottleOperation extends BeerPackageOperation {
    Long id;
    private BottlingService bottlingService;
    private String operationName = "Bottling";
    private double volume;
    private BottleVolumeEnum bottleVolumeEnum;
    Long program_id;

    public FillBottleOperation() {
    }

    public FillBottleOperation(BottlingService bottlingService) {
        this.bottlingService = bottlingService;
    }

    public FillBottleOperation(BottleVolumeEnum bottleVolumeEnum, BottlingService bottlingService) {
        this.bottleVolumeEnum = bottleVolumeEnum;
        this.volume = bottleVolumeEnum.getVolume();
        this.bottlingService = bottlingService;
    }

    public FillBottleOperation(Long id, BottleVolumeEnum bottleVolumeEnum, Long program_id, BottlingService bottlingService) {
        this.id = id;
        this.volume = bottleVolumeEnum.getVolume();
        this.bottleVolumeEnum = bottleVolumeEnum;
        this.program_id = program_id;
        this.bottlingService = bottlingService;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    @Override
    public void setProgram_id(Long program_id) {
        this.program_id = program_id;
    }

    public Long getId() {
        return id;
    }

    public double getVolume() {
        return volume;
    }

    public BottleVolumeEnum getBottleVolumeEnum() {
        return bottleVolumeEnum;
    }

    public Long getProgram_id() {
        return program_id;
    }

    @Override
    public String getOperationName() {
        return operationName;
    }

    @Override
    public List<BeerPackage> runProgram(BeerComponent beerComponent, double liquidVolume) throws InterruptedException {
        List<BeerPackage> beerPackages = bottlingService.bottling(this.getVolume(), liquidVolume);
        return beerPackages;
    }

    @Override
    public String toString() {
        return "FillBottle{" +
                "operationName='" + operationName + '\'' +
                ", volume=" + volume +
                '}';
    }
}
