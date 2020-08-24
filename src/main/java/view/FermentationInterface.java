package view;

import entity.beerComponents.BeerComponent;
import entity.temperatureOperations.TemperatureOperation;
import entity.beerComponents.Beer;
import entity.temperatureOperations.CoolingTemperatureOperation;
import entity.temperatureOperations.HeatingTemperatureOperation;
import entity.temperatureOperations.KeepTemperatureOperation;
import entity.temperatureOperations.SetTemperatureOperation;
import exception.OperationNotExistException;
import service.TemperatureService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class FermentationInterface {

    TemperatureService temperatureService;

    String operationType = "Fermentation";

    List<TemperatureOperation> temperatureOperations = new ArrayList<>();

    public FermentationInterface() {
    }

    public FermentationInterface(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    public List<TemperatureOperation> getTemperatureOperations() {
        return temperatureOperations;
    }

    public void chooseOperation() throws InterruptedException {
        String choice;

        System.out.println("Choose action:");
        System.out.println("1. Set initial temperature");
        System.out.println("2. Heating");
        System.out.println("3. Cooling");
        System.out.println("4. Keep temperature");
        System.out.println("5. Program preview");
        System.out.println("6. Remove Operation");
        System.out.println("7. Change Operation");
        System.out.println("8. Clear Program");
        System.out.println("0. Back ");

        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextLine();
        TemperatureOperation temperatureOperation = null;
        switch (choice) {
            case "1": {
                temperatureOperation = SetInitialTemperatureOperation();
                break;
            }
            case "2": {
                temperatureOperation = SetHeatingOperation();
                break;
            }
            case "3": {
                temperatureOperation = SetCoolingOperation();
                break;
            }
            case "4": {
                temperatureOperation = SetKeepTemperatureOperation();
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
        if (temperatureOperation != null) {
            temperatureOperations.add(temperatureOperation);
        }

        if (!choice.equals("0")) {
            chooseOperation();
        }
    }

    public Beer performFermentationProgram(BeerComponent wort) throws InterruptedException {
        for (TemperatureOperation o : temperatureOperations) {
            wort = o.runProgram(wort);
        }
        System.out.println("The Fermentation program successfully finished");
        System.out.println();
        Beer beer = new Beer(wort.getLiquid());
        beer.setTemperature(wort.getTemperature());
        return beer;
    }

    public void printOperations() {
        temperatureOperations.stream().forEach(o -> System.out.println(temperatureOperations.indexOf(o) + ". " + o.toString()));
        System.out.println();
    }

    public void clearProgram() {
        temperatureOperations.clear();
    }

    public void setTemperatureOperations(List<TemperatureOperation> operations) {
        temperatureOperations = operations;
    }

    private TemperatureOperation SetInitialTemperatureOperation() {
        double temperature;
        System.out.println("Set Initial temperature (°C)");
        temperature = new Scanner(System.in).nextDouble();
        TemperatureOperation temperatureOperation = new SetTemperatureOperation(temperature, operationType, temperatureService);
        return temperatureOperation;
    }

    private TemperatureOperation SetHeatingOperation() {
        double finalTemperature;
        double speed;
        System.out.println("Set final temperature (°C)");
        finalTemperature = new Scanner(System.in).nextDouble();
        System.out.println("Set speed (°C/h)");
        speed = new Scanner(System.in).nextDouble();
        TemperatureOperation temperatureOperation = new HeatingTemperatureOperation(finalTemperature, speed, operationType, temperatureService);
        return temperatureOperation;
    }

    private TemperatureOperation SetKeepTemperatureOperation() {
        int mins;
        System.out.println("Set time to keep (mins)");
        mins = new Scanner(System.in).nextInt();
        TemperatureOperation temperatureOperation = new KeepTemperatureOperation(mins, operationType, temperatureService);
        return temperatureOperation;
    }

    private TemperatureOperation SetCoolingOperation() {
        double finalTemperature;
        double speed;
        System.out.println("Set final temperature (°C)");
        finalTemperature = new Scanner(System.in).nextDouble();
        System.out.println("Set speed (°C/h)");
        speed = new Scanner(System.in).nextDouble();
        TemperatureOperation temperatureOperation = new CoolingTemperatureOperation(finalTemperature, speed, operationType, temperatureService);
        return temperatureOperation;
    }

    private void removeOperation() throws OperationNotExistException {
        int index;
        System.out.println("Write the index of the operation which you want to remove:");
        index = new Scanner(System.in).nextInt();
        if (index >= 0 && index < temperatureOperations.size()) {
            temperatureOperations.remove(index);
        } else if (temperatureOperations.size() == 0) {
            System.out.println("No operation to remove");
        } else {
            throw new OperationNotExistException(index);
        }
    }

    private void changeOperation() throws OperationNotExistException {
        int index;
        System.out.println("Write the index of the operation which you want to change:");
        index = new Scanner(System.in).nextInt();
        if (index >= 0 && index < temperatureOperations.size()) {
            Optional<TemperatureOperation> newOperation = selectNewOperation();
            if (newOperation != null) {
                temperatureOperations.set(index, newOperation.get());
            } else {
                System.out.println("Wrong operation, try again");
            }
        } else if (temperatureOperations.size() == 0) {
            System.out.println("No operation to change");
        } else {
            throw new OperationNotExistException(index);
        }
    }

    private Optional<TemperatureOperation> selectNewOperation() {
        TemperatureOperation temperatureOperation = null;
        String choice;
        System.out.println("Write new Operation:");
        System.out.println("1. Set initial temperature");
        System.out.println("2. Heating");
        System.out.println("3. Cooling");
        System.out.println("4. Keep temperature");
        choice = new Scanner(System.in).nextLine();
        switch (choice) {
            case "1": {
                temperatureOperation = SetInitialTemperatureOperation();
                break;
            }
            case "2": {
                temperatureOperation = SetHeatingOperation();
                break;
            }
            case "3": {
                temperatureOperation = SetCoolingOperation();
                break;
            }
            case "4": {
                temperatureOperation = SetKeepTemperatureOperation();
                break;
            }
        }
        return Optional.ofNullable(temperatureOperation);
    }
}
