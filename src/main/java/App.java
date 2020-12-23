import fruits.Kiwi;

import java.io.IOException;
import java.util.*;

import cars.Car;
import cars.Tesla;
import cars.Toyota;

public class App {
	
    public static void main(String[] args) throws InterruptedException, IOException {

        // try it here

    }

    static void stringBufferBuilderExample() {
        // fast but not thread safe
        StringBuilder stringBuilder = new StringBuilder();

        // thread safe
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer
                .append("hello")
                .append(" ")
                .append("how")
                .append(" ")
                .append("are")
                .append(" ")
                .append("you");

        System.out.println(stringBuffer.toString());
    }

    static void simpleStringConcatExample() {

        System.out.println("hello" + " how " + "are" + " you");
        System.out.println("hello" + 1 + 1 + 1 + 1);
        System.out.println("hello" + (1 + 1 + 1 + 1));

    }

    static void innerClassExample() {

        System.out.println(Utils.combineWords("hello", "everybody"));

        final boolean isDinner = true;

        abstract class Food {
            abstract String getName();
            abstract boolean isGood();
            boolean isDinner() {
                return isDinner;
            }

            void printDetails() {
                System.out.println(getName());
                System.out.println(isGood());
                System.out.println(getName() + " is dinner: " + isDinner());
            }

        }

        Food wasabi = new Food() {

            String getName() {
                return "wasabi";
            }

            boolean isGood() {
                return false;
            };
        };

        Food dosa = new Food() {

            String getName() {
                return "dosa";
            }

            boolean isGood() {
                return true;
            };
        };

        wasabi.printDetails();
        dosa.printDetails();

    }

    private static String getSecret() {
        return "do not tell anybody I like red hot chilli peppers";
    }

	static class Utils {
		
		static String combineWords(String word1, String word2) {
			return word1.concat(" ").concat(word2).concat(getSecret());
		}
		
	}

    static void findInMapExample() {


        Map<String, Car> cars = new HashMap<String, Car>();

        cars.put("123", new Tesla("123"));
        cars.put("456", new Toyota("456"));

        // looking for a Tesla


        String needToFind = "123";

        // We already did the work - and mapped
        // the VIN to the object
        // key value -> memory directly
        cars.put("123", new Tesla("123"));
        cars.put("456", new Toyota("456"));

        Car ourCar = cars.get(needToFind);

        System.out.println(ourCar.getVin());

        // O(n) - List (how long does it take to find an entry)
        // related to the size of the list
        // bigger list - longer wait
        // 2x bigger list - 2x longer wait

        // O(1) - Map (how long does it take to find an entry)
        // not related to size of map
        // bigger map - same wait
        // 2x bigger map - same wait
        
    }

    static void linkedListExample() {
        List<Car> cars = new LinkedList<Car>();
        cars.add(new Tesla("123"));
        cars.add(new Toyota("456"));
        // if you're planning on adding cars frequently
        // your name Jay Leno
        // you would want a linkedList
        // why ??



        for (int i = 0; i < cars.size(); i++){
            System.out.println(cars.get(i).isElectric());
        }
    }

    static void mapExample() {
        // create naming conventions to quickly find objects
        // key -> points to -> hashcode -> memory (not relevant how many keys)
        Map<String, Car> carsByNickname = new HashMap<String, Car>();

        carsByNickname.put("tez", new Tesla("342"));
        carsByNickname.put("goat", new Toyota("345"));

        Set<String> nicknames = carsByNickname.keySet();
        for (int i = 0; i < nicknames.toArray().length; i++) {
            System.out.println(nicknames.toArray()[i]);
        }

    }



    static void collectionsExamples() {

        // Stack of cars at carwash
        Stack<Car> carsInGarage = new Stack<Car>();
        carsInGarage.add(new Toyota("4352"));
        carsInGarage.add(new Toyota("476876856"));
        carsInGarage.add(new Toyota("324"));
        carsInGarage.add(new Tesla("1111"));

        Car easyToTakeFromGarage = carsInGarage.pop();

        System.out.println("The easiest to take out of garage electric: " + easyToTakeFromGarage.isElectric());

        // A Toyota
        // B Tesla

        // Queue of cars at carwash
        Queue<Car> carsQueueAtCarwash = new LinkedList<Car>();
        carsQueueAtCarwash.add(new Toyota("887675"));
        carsQueueAtCarwash.add(new Toyota("989988"));
        carsQueueAtCarwash.add(new Toyota("878787"));
        carsQueueAtCarwash.add(new Tesla("2211212"));

        Car nextInLine = carsQueueAtCarwash.remove();

        System.out.println("Is next in line at carwash electric: " + nextInLine.isElectric());
        // A: That is a toyota
        // B: That is a tesla


        // Are fixed in size
        // Awkward for most cases
        Car[] carsArray = new Car[10];
        carsArray[0] = new Tesla("898776");
        carsArray[1] = new Toyota("21123232");
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
        carsList.add(new Tesla("78765654"));
        carsList.add(new Toyota("09987876"));

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