package pl.com.bottega.carcraft.model.sandbox;


/**
 * Created by anna on 19.11.2016.
 */
public class PostOffice {

    public static void main(String[] args) {
        Box<Present, String> box = new Box<>(5000); //Box przechowuje obiekt typu Present
        Present present = new Present("opakowanie różowe", 50);
        box.put(present);
        box.wrap("różowy papier");
        //box.put(50);
        send(box);
        receivePresent(box);
        //--------------
        Box<Box<Present, String>, String> doublePackageBox = new Box<>(5000);
        doublePackageBox.put(box);
        Box<Present, String> popped = doublePackageBox.pop();
        //--------------
        Box<Present, String> coffeeBox = new Box<>(5000);
        coffeeBox.put(new Present("kawa w ziarnach", 500));
        Box<Coffee, String> mixedCoffee = coffeeBox.change(new CoffeeMixer());
    }

    public static void send(Box<Present, String> box) {
        System.out.println("Wysyłam paczkę z " + box.pop());
    }

    private static void receivePresent(Box<Present, String> box) {
        Present present = box.pop();
        present.unwrap();
    }
}
