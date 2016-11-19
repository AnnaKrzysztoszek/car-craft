package pl.com.bottega.carcraft.model.cars;

import pl.com.bottega.carcraft.model.cars.Car;
import pl.com.bottega.carcraft.model.engines.combustion.CombustionEngine;
import pl.com.bottega.carcraft.model.engines.electric.ElectricEngine;

/**
 * Created by anna on 13.11.2016.
 */
public class HybridCar<L> extends Car<L> {

    private static final double ENERGY_CAPACITY = 85;

    protected ElectricEngine electricEngine;

    protected CombustionEngine combustionEngine;

    private double energyLevel;

    public HybridCar(BodyType bodyType, CombustionEngine engine, ElectricEngine electricEngine, String name, double fuelLevel,double energyLevel, int x, int y) {
        super(bodyType, engine, name, fuelLevel, x, y);
        if (energyLevel > ENERGY_CAPACITY)
            throw new IllegalArgumentException("Energy level can not exceed" + ENERGY_CAPACITY);
        this.electricEngine = electricEngine;
        this.energyLevel = energyLevel;
    }

    @Override
    public void run() {
        super.run();
        if (! electricEngine.isRunning())
            electricEngine.start();
    }

    @Override
    public void stop() {
        super.stop();
        if (electricEngine.isRunning())
            electricEngine.stop();
    }

    public void charge(double energyQuantity) throws IllegalArgumentException{
        double total = energyLevel + energyQuantity;
        if (total > ENERGY_CAPACITY)
            throw new IllegalArgumentException("total energy " + total + " exceeds capacity " + ENERGY_CAPACITY);
        energyLevel = total;
    }

    @Override
    public void left(int distance) throws IllegalStateException {

    }


    private void firstConsumeElectricEnergyThenFuel() {

    }

    private void switchingBetweenEngines() {
        if (energyLevel == ENERGY_CAPACITY && combustionEngine.isRunning()) {
            combustionEngine.stop();
            electricEngine.start();
        }
        if (energyLevel == 0 && electricEngine.isRunning()) {
            electricEngine.stop();
            combustionEngine.start();
        }
    }

    public double getEnergyCapacity() {
        return ENERGY_CAPACITY;
    }

    public double getEnergyLevel() {
        return energyLevel;
    }
}
