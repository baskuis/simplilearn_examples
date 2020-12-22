import classes.*;
import examples.RegexExample;

import java.io.IOException;
import java.util.*;

public class App {

    public static void main(String[] args) throws InterruptedException, IOException {




    }




    static void collectionsExamples() {
        // Queue of cars at carwash
        Stack<Car> carsInGarage = new Stack<Car>();
        carsInGarage.add(new Toyota());
        carsInGarage.add(new Toyota());
        carsInGarage.add(new Toyota());
        carsInGarage.add(new Tesla());

        Car easyToTakeFromGarage = carsInGarage.pop();

        System.out.println("The easiest to take out of garage electric: " + easyToTakeFromGarage.isElectric());

        // A Toyota
        // B Tesla

        // Queue of cars at carwash
        Queue<Car> carsQueueAtCarwash = new LinkedList<Car>();
        carsQueueAtCarwash.add(new Toyota());
        carsQueueAtCarwash.add(new Toyota());
        carsQueueAtCarwash.add(new Toyota());
        carsQueueAtCarwash.add(new Tesla());

        Car nextInLine = carsQueueAtCarwash.remove();

        System.out.println("Is next in line at carwash electric: " + nextInLine.isElectric());
        // A: That is a toyota
        // B: That is a tesla



        // Are fixed in size
        // Awkward for most cases
        Car[] carsArray = new Car[10];
        carsArray[0] = new Tesla();
        carsArray[1] = new Toyota();
        for (int i = 0; i < carsArray.length; i++){
            if (carsArray[i] != null) {
                System.out.println(carsArray[i].isElectric());
            } else {
                System.out.println("No car at entry " + i);
            }
        }

        // Read more natural
        // More flexible
        List<Car> carsList = new ArrayList<Car>();
        carsList.add(new Tesla());
        carsList.add(new Toyota());

        for (int i = 0; i < carsList.size(); i++){
            System.out.println(carsList.get(i).isElectric());
        }
    }



    static void kiwiExample() {
        Kiwi kiwi = new Kiwi();
        kiwi.printGood();

        Kiwi veryGoodKiwi = new Kiwi(true);
        veryGoodKiwi.printGood("Super good");
    }


}


/**

 //        ReadFileExample.readFile();
 //
 //        ThreadsExample.twoThreads();
 //
 //        FinallyRiddleExample.finallyRiddle();

 RegexExample.findTheMatch();

 */