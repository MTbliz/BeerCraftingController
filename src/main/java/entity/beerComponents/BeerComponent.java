package entity.beerComponents;

import entity.Liquid;

public abstract class BeerComponent {

    private double temperature;

    Liquid liquid;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Liquid getLiquid() {
        return liquid;
    }

    public void setLiquid(Liquid liquid) {
        this.liquid = liquid;
    }
}
