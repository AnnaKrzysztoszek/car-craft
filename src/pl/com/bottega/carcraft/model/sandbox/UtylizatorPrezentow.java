package pl.com.bottega.carcraft.model.sandbox;

/**
 * Created by anna on 19.11.2016.
 */
public class UtylizatorPrezentow implements Changer<Present, Sieczka>{
    @Override
    public Sieczka change(Present present) {
        return new Sieczka();
    }
}
