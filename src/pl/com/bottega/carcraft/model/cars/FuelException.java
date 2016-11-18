package pl.com.bottega.carcraft.model.cars;

/**
 * Created by anna on 06.11.2016.
 */
public class FuelException extends RuntimeException {

    private double missingFuel;

    public FuelException(double missingFuel) {
        super("Not enough fuel. Missing " + missingFuel);
        this.missingFuel = missingFuel;
    }

    public double getMissingFuel() {
        return missingFuel;
    }
}
