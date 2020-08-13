package view;

import entity.beerPackageOperations.BeerPackageOperation;
import entity.Program;
import entity.temperatureOperations.TemperatureOperation;
import entity.beerComponents.Beer;
import entity.beerComponents.Mash;
import entity.beerComponents.Wort;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    IngredientInterface ingredientInterface = new IngredientInterface();
    MashingInterface mashingInterface = new MashingInterface();
    FermentationInterface fermentationInterface = new FermentationInterface();
    BottlingInterface bottlingInterface = new BottlingInterface();

    public UserInterface() throws InterruptedException {
        chooseAction();
    }

    private void chooseAction() throws InterruptedException {
        String choice;
        do {
            System.out.println("Choose action:");
            System.out.println("1. Set Liquid Volume.");
            System.out.println("2. Plan mashing program.");
            System.out.println("3. Plan fermentation program.");
            System.out.println("4. Plan bottling program.");
            System.out.println("5. Start Program.");
            System.out.println("6. Preview Program.");
            System.out.println("0. Turn off ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    ingredientInterface.chooseOperation();
                    break;
                case "2":
                    mashingInterface.chooseOperation();
                    break;
                case "3":
                    fermentationInterface.chooseOperation();
                    break;
                case "4":
                    bottlingInterface.chooseOperation();
                    break;
                case "5":
                    runProgram();
                    break;
                case "6":
                    previewProgram();
                    break;
                case "0":
                    break;
            }
        } while (!choice.equals("0"));
    }

    public void previewProgram() {
        System.out.println("<-- Liquid Volume -->");
        double liquidVolume = ingredientInterface.getLiquid().getVolume();
        System.out.println(liquidVolume + "L");
        System.out.println();
        System.out.println("<-- Mashing Program -->");
        mashingInterface.printOperations();
        System.out.println("<-- Fermentation Program -->");
        fermentationInterface.printOperations();
        System.out.println("<-- Bottling Program -->");
        bottlingInterface.printOperations();
    }

    public void runProgram() throws InterruptedException {
        if (ingredientInterface.getLiquid().getVolume() == 0) {
            System.out.println();
            System.out.println("The program cannot be started. Please set the liquid volume first.");
            System.out.println();
        } else {
            Wort wort = mashingInterface.performMashingProgram(new Mash(ingredientInterface.getLiquid()));
            Beer beer = fermentationInterface.performFermentationProgram(wort);
            bottlingInterface.performBottlingProgram(beer, ingredientInterface.getLiquid().getVolume());
        }
    }

    public void saveProgram() {
        List<TemperatureOperation> mashingOperations = mashingInterface.getTemperatureOperations();
        List<TemperatureOperation> fermentationOperations = fermentationInterface.getTemperatureOperations();
        List<BeerPackageOperation> beerPackageOperations = bottlingInterface.getBeerPackageOperations();
        Program program = new Program(mashingOperations, fermentationOperations, beerPackageOperations);

    }

    public void uploadExistingProgram() {

    }
}
