package pl.com.bottega.carcraft.model.sandbox;

/**
 * Created by anna on 19.11.2016.
 */
public class Present implements Weighable{

    private String foo;

    private Integer bar;

    public Present(String foo, Integer bar) {
        this.foo = foo;
        this.bar = bar;
    }

    public String toString() {
        return "Prezent";
    }

    public void unwrap() {
        System.out.println("Rozpakowuje prezent");
    }

    @Override
    public int getWeight() {
        return foo.length() * bar;
    }
}
