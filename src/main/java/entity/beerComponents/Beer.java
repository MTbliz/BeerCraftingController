package entity.beerComponents;

import entity.Liquid;

public class Beer extends BeerComponent {

    Liquid liquid;

    public Beer() {

    }

    public Beer(Liquid liquid) {
        this.liquid = liquid;
    }

    public Liquid getLiquid() {
        return liquid;
    }
}
