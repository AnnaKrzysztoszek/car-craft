package pl.com.bottega.carcraft.application;

/**
 * Created by anna on 06.11.2016.
 */
public class DoubleTest {

    public static void main(String[] args) {
        int[] data = new int[4000000];
        //wypelnienie danymi
        for (int i = 0; i < data.length; i++) {
            data[i] = i + 1;
        }
        //suma elementow
        long sum = 0;
        for (int element : data) {
            sum += element;
        }
        //lub
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
        System.out.println(sum);

        int i = 0;
        while (i < data.length) {
            sum += data[i];
            i++;
        }
    }
}
