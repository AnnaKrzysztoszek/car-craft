package pl.com.bottega.carcraft.model.engines.combustion;

import pl.com.bottega.carcraft.model.engines.combustion.CombustionEngine;

/**
 * Created by anna on 12.11.2016.
 */
public abstract class PetrolEngine extends CombustionEngine {

    @Override
    protected void init() {
        System.out.println("LOG: zaplon dziala");
    }
}
