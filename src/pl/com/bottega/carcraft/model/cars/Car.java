package pl.com.bottega.carcraft.model.cars;

import com.sun.deploy.util.StringUtils;
import pl.com.bottega.carcraft.model.Human;
import pl.com.bottega.carcraft.model.engines.Engine;

import java.util.*;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;


/**
 * Created by anna on 05.11.2016.
 */
public class Car<L> {

    static final double FUEL_CAPACITY = 60;
    private static final int DEFAULT_DISTANCE = 1;

    protected static final int RPM = 2500;

    protected Engine engine;//klasy dziedziczace beda widziec protected

    protected int x;
    protected int y;

    private double fuelLevel;

    private String name;

    private BodyType bodyType;

    private Collection<L> load = new ArrayList<>();

    private Set<Human> passengers = new HashSet<>();

    Car(BodyType bodyType, Engine engine, String name, double fuelLevel) {
        this(bodyType, engine, name, fuelLevel, 0, 0);
    }

    Car(BodyType bodyType, Engine engine, String name, double fuelLevel, int x, int y){
        this.bodyType = bodyType;
        this.engine = engine;

        this.fuelLevel = fuelLevel;
        this.name = name;

        //========== 2 ============
        //this.fuelLevel = fuelLevel > FUEL_CAPACITY ? FUEL_CAPACITY : fuelLevel;
        //========== 3 ============
        //this.fuelLevel = min(fuelLevel, FUEL_CAPACITY);

        this.x = x;
        this.y = y;
    }

    public  void run() {
        if (!engine.isRunning())
            engine.start();
    }

    public void stop() {
        if (engine.isRunning())
            engine.stop();
    }

    public void left() {
        //x -= DEFAULT_DISTANCE;
        left(DEFAULT_DISTANCE);
    }

    private boolean isTooFar(int distance) {
        return distance > x;
    }

    public void left(int distance) throws IllegalStateException{//nie musimy deklarować throws dla wyjątków unchecked
        checkEngine();
        if (isOutOfBound(x-distance, y))
            return;
        int actualDistance = moveAndConsumeEnergyAsPossible(distance);
        x -= actualDistance;
    }

    public void right(int distance){
        checkEngine();
        if (isOutOfBound(x+distance, y))
            return;
        int actualDistance = moveAndConsumeEnergyAsPossible(distance);
        x += actualDistance;
    }

    public void right() {
        right(DEFAULT_DISTANCE);
    }

    public void up() {
        up(DEFAULT_DISTANCE);
    }

    public void up(int distance) {
        checkEngine();
        if (isOutOfBound(x, y+distance))
            return;
        int actualDistance = moveAndConsumeEnergyAsPossible(distance);
        y += actualDistance;
    }

    public void down() {
        down(DEFAULT_DISTANCE);
    }

    public void down(int distance) {
        checkEngine();
        if (isOutOfBound(x, y-distance))
            return;
        int actualDistance = moveAndConsumeEnergyAsPossible(distance);
        y -= actualDistance;
    }

    protected void checkEngine() {
        if (!engine.isRunning())
            throw new IllegalStateException("Engine is not running");
    }

    public void moveTo(int targetX, int targetY) throws FuelException {
        checkEngine();
        if (isOutOfBound(targetX, targetY)) return;

        int distanceX = Math.abs(targetX - x);
        int distanceY = Math.abs(targetY - y);
        int totalDistance = distanceX + distanceY;

        moveAndConsumeEnergyIfPossible(targetX, targetY, totalDistance);
    }

    private void moveAndConsumeEnergyIfPossible(int targetX, int targetY, double totalDistance) throws FuelException{
        double requiredFuel = engine.calculateFuelConsumption(totalDistance, RPM);
        if (requiredFuel > fuelLevel)
            throw new FuelException(requiredFuel - fuelLevel);

        fuelLevel -= requiredFuel;
        x = targetX;
        y = targetY;
    }

    protected int moveAndConsumeEnergyAsPossible(int distance) {
        int possibleDistance = (int)(fuelLevel / engine.getFuelConsumption());

        int actualDistance;
        if (possibleDistance < distance){
            actualDistance = possibleDistance;
        }
        else {
            actualDistance = distance;
        }

        fuelLevel -= engine.calculateFuelConsumption(actualDistance, RPM);

        return actualDistance;
    }

    protected boolean isOutOfBound(int targetX, int targetY) {
        return targetX < 0 || targetY < 0;
    }

    public void shortMoveTo(int targetX, int targetY) throws FuelException{ //tylko jako dokumentacja, kompilator tego nie wymaga
        checkEngine();
        if (isOutOfBound(targetX, targetY)) return;

        int a = x - targetX;
        int b = y - targetY;

        double totalDistance = sqrt(pow(a, 2) + pow(b, 2));

        moveAndConsumeEnergyIfPossible(targetX, targetY, totalDistance);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    public byte getDoorsCount() {
        return bodyType.getDoorsCount();
    }

    public void put(L load) {
        this.load.add(load);
    }

    public Collection<L> pop() {
        Collection<L> tmp = new ArrayList<>();
        tmp.addAll(load);
        load.clear();
        return tmp;
    }

    @Override
    public String toString() {
        //StringBuilder - nieodporny na wątki
        //StringBuffer - odporny na watki, wolniejszy
        //StringBuilder sb = new StringBuilder();
        //return sb.append("x=").append(x).append(" ").toString();
        return "x=" + x + " y=" + y + " f= " + fuelLevel + " l=" + loadString(load);
    }

    public String loadString(Collection<L> load) {
        return load.isEmpty() ? "empty" : StringUtils.join(load, ", ");
    }


    public boolean equals2(Object o) {
        if (this == o) return true;//ta sama pamiec ram jest zajeta
        if (o instanceof Car) {//Car lub cokolwiek co po nim dziedziczy
            Car car = (Car) o;
            return name.equals(car.name);
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return name.equals(car.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public double getFuelCapacity() {
        return FUEL_CAPACITY;
    }

    public void fill(double amountFuel) {
        double total = fuelLevel + amountFuel;
        if (total > FUEL_CAPACITY)
            throw new IllegalArgumentException("total fuel " + total + " exceeds capacity " + FUEL_CAPACITY);
        fuelLevel = total;
    }
}
