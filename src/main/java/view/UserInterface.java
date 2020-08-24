package view;

import entity.beerPackageOperations.BeerPackageOperation;
import entity.Program;
import entity.temperatureOperations.TemperatureOperation;
import entity.beerComponents.Beer;
import entity.beerComponents.Mash;
import entity.beerComponents.Wort;
import service.ProgramDbService;
import service.programDbServiceImplementation.ProgramDbServiceImplementation;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    ProgramDbService programDbService = new ProgramDbServiceImplementation();
    IngredientInterface ingredientInterface = new IngredientInterface();
    MashingInterface mashingInterface = new MashingInterface();
    FermentationInterface fermentationInterface = new FermentationInterface();
    BottlingInterface bottlingInterface = new BottlingInterface();

    public UserInterface(ProgramDbService programDbService,
                         IngredientInterface ingredientInterface,
                         MashingInterface mashingInterface,
                         FermentationInterface fermentationInterface,
                         BottlingInterface bottlingInterface) {
        this.programDbService = programDbService;
        this.ingredientInterface = ingredientInterface;
        this.mashingInterface = mashingInterface;
        this.fermentationInterface = fermentationInterface;
        this.bottlingInterface = bottlingInterface;
    }

    public UserInterface() throws InterruptedException {
    }

    public void chooseAction() throws InterruptedException {
        String choice;
        do {
            System.out.println("Choose action:");
            System.out.println("1. Set Liquid Volume");
            System.out.println("2. Plan mashing program");
            System.out.println("3. Plan fermentation program");
            System.out.println("4. Plan bottling program");
            System.out.println("5. Preview Program");
            System.out.println("6. Start Program");
            System.out.println("7. Clear Program");
            System.out.println("8. Save Program");
            System.out.println("9. Upload Program");
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
                    previewProgram();
                    break;
                case "6":
                    runProgram();
                    break;
                case "7":
                    clearProgram();
                    break;
                case "8":
                    System.out.println("Write the name of the Program:");
                    String programName = new Scanner(System.in).nextLine();
                    saveProgram(programName);
                    break;

                case "9":
                    List<Program> programs = getAllPrograms();
                    programs.forEach(program -> System.out.println(program.getId() + "." + program.getProgramName()));
                    System.out.println("Write the program id:");
                    Long programId = new Scanner(System.in).nextLong();
                    uploadExistingProgram(programId);
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

    public void saveProgram(String programName) {
        List<TemperatureOperation> mashingOperations = mashingInterface.getTemperatureOperations();
        List<TemperatureOperation> fermentationOperations = fermentationInterface.getTemperatureOperations();
        List<BeerPackageOperation> beerPackageOperations = bottlingInterface.getBeerPackageOperations();
        Program program = new Program(programName, mashingOperations, fermentationOperations, beerPackageOperations);
        programDbService.saveProgram(program);
    }

    public void clearProgram() {
        mashingInterface.clearProgram();
        fermentationInterface.clearProgram();
        bottlingInterface.clearProgram();
    }

    public void uploadExistingProgram(Long programId) {
        clearProgram();
        Program program = programDbService.getProgramById(programId);
        mashingInterface.setTemperatureOperations(program.getMashingOperations());
        fermentationInterface.setTemperatureOperations(program.getFermentationOperations());
        bottlingInterface.setBeerPackageOperations(program.getBeerPackageOperations());
    }

    public List<Program> getAllPrograms() {
        return programDbService.getAllPrograms();
    }
}
