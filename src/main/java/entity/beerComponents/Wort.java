package entity.beerComponents;

import entity.Liquid;

public class Wort extends BeerComponent {

    Liquid liquid;

    public Wort() {
    }

    public Wort(Liquid liquid) {
        this.liquid = liquid;
    }

    public Liquid getLiquid() {
        return liquid;
    }
}
