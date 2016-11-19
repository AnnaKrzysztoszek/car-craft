package pl.com.bottega.carcraft.model.sandbox;

/**
 * Created by anna on 19.11.2016.
 */
public interface Changer<OldType, NewType> {

    NewType change(OldType oldType);
}
