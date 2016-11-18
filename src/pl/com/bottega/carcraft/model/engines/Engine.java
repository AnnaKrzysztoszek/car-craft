package pl.com.bottega.carcraft.model.engines;

/**
 * Created by anna on 12.11.2016.
 */
public interface Engine {

    void start(); //metody w interfejsach sa publiczne domyslnie
    void stop();
    double calculateFuelConsumption(double distance, int rpm);
    boolean isRunning();
    double getFuelConsumption();
}
