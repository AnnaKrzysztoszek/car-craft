package pl.com.bottega.carcraft.model.engines.combustion;

import pl.com.bottega.carcraft.model.engines.Engine;

/**
 * Created by anna on 12.11.2016.
 */
public abstract class CombustionEngine implements Engine {

    private boolean isRunning;//domyslnie false

    protected abstract void init();//ta metode widza potomkowie

    @Override
    public void start() {
        isRunning = true;
        System.out.println("LOG: starting combustion engine");

        init();
    }

    @Override
    public void stop() {
        isRunning = false;
        System.out.println("LOG: shutting down combustion engine");
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }
}
