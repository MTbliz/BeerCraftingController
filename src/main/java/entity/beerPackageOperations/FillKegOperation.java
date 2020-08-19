package entity.beerPackageOperations;

import entity.beerComponents.BeerComponent;
import entity.beerpackages.BeerPackage;
import service.KeggingService;
import service.keggingServiceImplementation.KeggingServiceImplementation;

import java.util.List;

public class FillKegOperation extends BeerPackageOperation {
    Long id;
    private KeggingService keggingService = new KeggingServiceImplementation();
    private String operationName = "Kegging";
    private double volume;
    private KegVolumeEnum kegVolumeEnum;
    Long program_id;

    public FillKegOperation(){}

    public FillKegOperation(KegVolumeEnum kegVolumeEnum) {
        this.volume = kegVolumeEnum.getVolume();
        this.kegVolumeEnum = kegVolumeEnum;
    }

    public FillKegOperation(Long id, KegVolumeEnum kegVolumeEnum, Long program_id) {
        this.id = id;
        this.volume = kegVolumeEnum.getVolume();
        this.kegVolumeEnum = kegVolumeEnum;
        this.program_id = program_id;
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

    public KegVolumeEnum getKegVolumeEnum() {
        return kegVolumeEnum;
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
        List<BeerPackage> beerPackages = keggingService.Kegging(this.getVolume(), liquidVolume);
        return beerPackages;
    }

    @Override
    public String toString() {
        return "FillKeg{" +
                "operationName='" + operationName + '\'' +
                ", volume=" + volume +
                '}';
    }
}
