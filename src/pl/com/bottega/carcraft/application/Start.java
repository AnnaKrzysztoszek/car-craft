package pl.com.bottega.carcraft.application;

import pl.com.bottega.carcraft.model.cars.Car;
import pl.com.bottega.carcraft.model.cars.CarsFactory;
import pl.com.bottega.carcraft.model.cars.FuelException;
import pl.com.bottega.carcraft.model.cars.HybridCar;
import pl.com.bottega.carcraft.model.engines.combustion.BMW_N55;
import pl.com.bottega.carcraft.model.engines.Engine;
import pl.com.bottega.carcraft.model.engines.electric.ElectricEngine;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Slawek on 05/11/16.
 */
public class Start {
    private static boolean[][] map = new boolean[20][20];

    public static void main(String[] args){
        Car slowCar = null;
        Car fastCar = null;
        try {

            slowCar = CarsFactory.create("prius", 1, 1, 30);
            //new HybridCar(new BMW_N55(), new ElectricEngine(), "prius", 30, 40, 1, 1);

            fastCar = CarsFactory.create("i8", 3, 4, 20);
            fastCar.run();
        }
        catch (IllegalArgumentException ex){
            //ex.printStackTrace(); zła praktyka, użyć logger
            System.out.println("nie moge uruchomoc programu ;( " + ex.getMessage());
            return;
        }


        Random random = new Random();
        //zakres losowania
        int maxX = map.length;
        int maxY = map[0].length;

        try {
            boolean go = true;
            do{
                //System.out.print("\033[2J");//clear console
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println(fastCar);//info o samochodzie

                int x = fastCar.getX();
                int y = fastCar.getY();
                map[x][y] = true;
                printMap();


                char character = readKey();
                //fastCar.handleKey(character); zbrodnia przeciw ludzkosci!!!

                switch (character){
                    case 'w':
                        fastCar.up();
                        break;
                    case 'd':
                        fastCar.right();
                        break;
                    case 's':
                        fastCar.down();
                        break;
                    case 'a':
                        fastCar.left();
                        break;
                    case 'q':
                        go = false;
                        break;
                    default:
                        fastCar.moveTo(random.nextInt(maxX), random.nextInt(maxY));//ruch w losowe miejsce
                }

            }while(go);

        }
        catch(IllegalStateException | FuelException ex){
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static char readKey() throws IOException {
        Scanner s = new Scanner(System.in);
        char ch = s.next().charAt(0);
        return ch;
    }

    private static void printMap(){
        int rowsCount = map[0].length;//ilość wierszy pobrana z pierwszej kolumny (może być z jakiejkolwiek)

        for (int row = rowsCount-1; row >= 0; row--){//wiersze od ostatniego aby układ był zorientowany od dolnego lewego rogu
            for (int col = 0; col < map.length; col++){//kolejne kolumny danego wiersza
                boolean cell = map[col][row];
                if (cell){
                    System.out.print("X");
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.println();//na koniec wiersza łamiemy linię
        }
    }
}