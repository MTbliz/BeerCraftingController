package view;

import entity.Liquid;

import java.util.Scanner;

public class IngredientInterface {

    Liquid liquid = new Liquid(0);

    public void chooseOperation() {

        String choice;
        double liquidVolume = 0;
        System.out.println("Choose action:");
        System.out.println("1. Set Liquid Volume");
        System.out.println("0. Back");

        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextLine();
        switch (choice) {
            case "1": {
                System.out.println("Set Liquid Volume (L)");
                liquidVolume = new Scanner(System.in).nextDouble();
                break;
            }
            case "0":
                break;
        }
        if (liquidVolume != 0) {
            liquid = new Liquid(liquidVolume);
        }
    }

    public Liquid getLiquid() {
        return liquid;
    }
}
