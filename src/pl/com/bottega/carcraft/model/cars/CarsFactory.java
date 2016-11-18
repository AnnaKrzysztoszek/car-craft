package pl.com.bottega.carcraft.model.cars;

import pl.com.bottega.carcraft.model.engines.combustion.*;
import pl.com.bottega.carcraft.model.engines.electric.ElectricEngine;

/**
 * Created by anna on 13.11.2016.
 */
//idiom
public class CarsFactory {
    /**
     *
     * @param model
     * @param x
     * @param y
     * @param fuelPercentage 0...100
     * @return
     * @throws IllegalArgumentException
     */
    public static Car create(String model, int x, int y, double fuelPercentage) throws IllegalArgumentException {
        if (x < 0 || y < 0)
            throw new IllegalArgumentException("Coordinates can not be negative");
        if (fuelPercentage < 0 || fuelPercentage > 100)
            throw new IllegalArgumentException("percentage should be in range [0, 100]");

        double fuelLevel = Car.FUEL_CAPACITY * fuelPercentage / 100;

        switch (model) {
            case "prius":
                return new HybridCar(BodyType.HATCHBACK, new BMW_N55(), new ElectricEngine(), "bazyliszek", fuelLevel, 10, x, y);
            case "i8":
                return new HybridCar(BodyType.COMBI, new BMW_N55(), new ElectricEngine(), "spaceship", fuelLevel, 3, x, y);
            case "mustang":
                return new HybridCar(BodyType.SEDAN, new V8(), new ElectricEngine(), "mustang", fuelLevel, 4, x, y);
            case "911":
                return new HybridCar(BodyType.SUV, new Boxer6(), new ElectricEngine(), "911", fuelLevel, 5, x, y);
            default:
                throw new IllegalArgumentException("model " + model + " is unknown");
        }

    }
}
