package pl.com.bottega.carcraft.model.cars;

/**
 * Created by anna on 13.11.2016.
 */
public enum BodyType {
    SEDAN((byte) 4, (byte) 6), COMBI((byte) 5, (byte) 2), HATCHBACK((byte) 5, (byte) 6), SUV((byte) 5, (byte) 3);

    private byte doorsCount;
    private byte sexiness;

    BodyType(byte doorsCount, byte sexiness) {
        this.doorsCount = doorsCount;
        this.sexiness = sexiness;
    }

    public byte getDoorsCount() {
        return doorsCount;
    }

    public byte getSexiness() {
        return sexiness;
    }
}
