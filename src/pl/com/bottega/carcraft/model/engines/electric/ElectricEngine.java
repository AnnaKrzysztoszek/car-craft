package pl.com.bottega.carcraft.model.engines.electric;

import pl.com.bottega.carcraft.model.engines.Engine;

/**
 * Created by anna on 13.11.2016.
 */
public class ElectricEngine implements Engine{

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public double calculateFuelConsumption(double distance, int rpm) {
        return 0;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public double getFuelConsumption() {
        return 0;
    }
}
