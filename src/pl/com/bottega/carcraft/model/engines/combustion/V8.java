package pl.com.bottega.carcraft.model.engines.combustion;

/**
 * Created by anna on 16.11.2016.
 */
public class V8 extends PetrolEngine {

    private static final double FUEL_CONSUMPTION = 0.003;

    @Override
    public double calculateFuelConsumption(double distance, int rpm) {
        return distance * FUEL_CONSUMPTION * rpm / 1000;
    }

    @Override
    public double getFuelConsumption() {
        return FUEL_CONSUMPTION;
    }
}
