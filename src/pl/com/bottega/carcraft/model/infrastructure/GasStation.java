package pl.com.bottega.carcraft.model.infrastructure;

import pl.com.bottega.carcraft.model.cars.Car;
import pl.com.bottega.carcraft.model.cars.HybridCar;

/**
 * Created by anna on 13.11.2016.
 */
public class GasStation {

    private double amountOfFuel;
    private double amountOfEnergy;

    private int x;
    private int y;

    public GasStation(double amountOfFuel, double amountOfEnergy) {
        this.amountOfFuel = amountOfFuel;
        this.amountOfEnergy = amountOfEnergy;
    }

    public GasStation(double amountOfFuel, double amountOfEnergy, int x, int y) {
        this.amountOfFuel = amountOfFuel;
        this.amountOfEnergy = amountOfEnergy;
        this.x = x;
        this.y = y;
    }

    private void fullService(Car car) {
        double amount = car.getFuelCapacity() - car.getFuelLevel();
        car.fill(amount);
        if (car instanceof HybridCar) {
            HybridCar hybridCar = (HybridCar) car;
            double kwh = hybridCar.getEnergyCapacity() - hybridCar.getEnergyLevel();
            hybridCar.charge(kwh);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append("Amount of fuel is equal ").append(amountOfFuel).append(" , amount of energy is equal ")
                .append(amountOfEnergy).toString();
    }

    public void buildGasStation() {

    }
}
