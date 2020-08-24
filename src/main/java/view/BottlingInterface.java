package view;

import entity.beerComponents.BeerComponent;
import entity.beerPackageOperations.*;
import entity.beerpackages.BeerPackage;
import exception.OperationNotExistException;
import service.BottlingService;
import service.KeggingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BottlingInterface {

    KeggingService keggingService;

    BottlingService bottlingService;

    List<BeerPackageOperation> beerPackageOperations = new ArrayList<>();

    public BottlingInterface() {
    }

    public BottlingInterface(KeggingService keggingService, BottlingService bottlingService) {
        this.keggingService = keggingService;
        this.bottlingService = bottlingService;
    }

    public List<BeerPackageOperation> getBeerPackageOperations() {
        return beerPackageOperations;
    }

    public void chooseOperation() throws InterruptedException {
        String choice;

        System.out.println("Choose action:");
        System.out.println("1. Fill 30L Keg");
        System.out.println("2. Fill 20L Keg");
        System.out.println("3. Fill Bottle 0.3");
        System.out.println("4. Fill Bottle 0.5");
        System.out.println("5. Program preview");
        System.out.println("6. Remove Operation");
        System.out.println("7. Change Operation");
        System.out.println("8. Clear Program.");
        System.out.println("0. Back ");

        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextLine();
        BeerPackageOperation beerPackageOperation = null;
        switch (choice) {
            case "1": {
                beerPackageOperation = new FillKegOperation(KegVolumeEnum.THIRTY_LITERS, keggingService);
                break;
            }
            case "2": {
                beerPackageOperation = new FillKegOperation(KegVolumeEnum.TWENTY_LITERS, keggingService);
                break;
            }
            case "3": {
                beerPackageOperation = new FillBottleOperation(BottleVolumeEnum.THREE_HUNDRED_MILLILITERS, bottlingService);
                break;
            }
            case "4": {
                beerPackageOperation = new FillBottleOperation(BottleVolumeEnum.FIVE_HUNDRED_MILLILITERS, bottlingService);
                break;
            }
            case "5":
                printOperations();
                break;
            case "6":
                try {
                    removeOperation();
                } catch (OperationNotExistException e) {
                    e.printStackTrace();
                }
                break;
            case "7": {
                try {
                    changeOperation();
                } catch (OperationNotExistException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "8":
                clearProgram();
                break;
            case "0":
                break;
        }
        if (beerPackageOperation != null) {
            beerPackageOperations.clear();
            beerPackageOperations.add(beerPackageOperation);
        }

        if (!choice.equals("0")) {
            chooseOperation();
        }
    }

    public List<BeerPackage> performBottlingProgram(BeerComponent bear, double liquidVolume) throws InterruptedException {
        List<BeerPackage> beerPackages = new ArrayList<>();
        for (BeerPackageOperation o : beerPackageOperations) {
            beerPackages = o.runProgram(bear, liquidVolume);
        }
        System.out.println();
        System.out.println("The Bottling program successfully finished");
        System.out.println();
        return beerPackages;
    }

    public void printOperations() {
        beerPackageOperations.stream().forEach(o -> System.out.println(beerPackageOperations.indexOf(o) + ". " + o.toString()));
        System.out.println();
    }

    public void clearProgram() {
        beerPackageOperations.clear();
    }

    public void setBeerPackageOperations(List<BeerPackageOperation> operations) {
        beerPackageOperations = operations;
    }

    private void removeOperation() throws OperationNotExistException {
        int index;
        System.out.println("Write the index of the operation which you want to remove:");
        index = new Scanner(System.in).nextInt();
        if (index >= 0 && index < beerPackageOperations.size()) {
            beerPackageOperations.remove(index);
        } else if (beerPackageOperations.size() == 0) {
            System.out.println("No operation to remove");
        } else {
            throw new OperationNotExistException(index);
        }
    }

    private void changeOperation() throws OperationNotExistException {
        int index;
        System.out.println("Write the index of the operation which you want to change:");
        index = new Scanner(System.in).nextInt();
        if (index >= 0 && index < beerPackageOperations.size()) {
            Optional<BeerPackageOperation> newOperation = selectNewOperation();
            if (newOperation != null) {
                beerPackageOperations.set(index, newOperation.get());
            } else {
                System.out.println("Wrong operation, try again");
            }
        } else if (beerPackageOperations.size() == 0) {
            System.out.println("No operation to change");
        } else {
            throw new OperationNotExistException(index);
        }
    }

    private Optional<BeerPackageOperation> selectNewOperation() {
        BeerPackageOperation beerPackageOperation = null;
        String choice;
        System.out.println("Write New Operation:");
        System.out.println("1. Fill 30L Keg");
        System.out.println("2. Fill 20L Keg");
        System.out.println("3. Fill Bottle 0.3");
        System.out.println("4. Fill Bottle 0.5");

        choice = new Scanner(System.in).nextLine();
        switch (choice) {
            case "1": {
                beerPackageOperation = new FillKegOperation(KegVolumeEnum.THIRTY_LITERS, keggingService);
                break;
            }
            case "2": {
                beerPackageOperation = new FillKegOperation(KegVolumeEnum.TWENTY_LITERS, keggingService);
                break;
            }
            case "3": {
                beerPackageOperation = new FillBottleOperation(BottleVolumeEnum.THREE_HUNDRED_MILLILITERS, bottlingService);
                break;
            }
            case "4": {
                beerPackageOperation = new FillBottleOperation(BottleVolumeEnum.FIVE_HUNDRED_MILLILITERS, bottlingService);
                break;
            }
        }
        return Optional.ofNullable(beerPackageOperation);
    }
}
