package pl.com.bottega.carcraft.model.infrastructure;

/**
 * Created by anna on 17.11.2016.
 */
public class GasStationFactory {

    private static GasStation createSmall(){
        return new GasStation(1000, 1000);
    }

    private static GasStation createMedium() {
        return new GasStation(2000, 2000);
    }

    private static GasStation createLarge() {
        return new GasStation(3000, 3000);
    }

}
