package entity.beerComponents;

import entity.Liquid;

public class Mash extends BeerComponent {

    Liquid liquid;

    public Mash() {
    }

    public Mash(Liquid liquid) {
        this.liquid = liquid;
    }

    public Liquid getLiquid() {
        return liquid;
    }
}
