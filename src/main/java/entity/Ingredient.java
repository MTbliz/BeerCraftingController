package entity;

public class Ingredient {

    String IngredientName;

    double IngredientMass;

    public Ingredient() {
    }

    public Ingredient(String ingredientName, double ingredientMass) {
        IngredientName = ingredientName;
        IngredientMass = ingredientMass;
    }

    public String getIngredientName() {
        return IngredientName;
    }

    public double getIngredientMass() {
        return IngredientMass;
    }
}
