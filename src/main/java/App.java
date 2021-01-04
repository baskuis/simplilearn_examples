import cars.Car;
import cars.Tesla;
import cars.Toyota;
import catsanddogs.Brutus;
import catsanddogs.Rufus;
import cereals.AbstractCereal;
import cereals.CocoPuffsCereal;
import cereals.SoggyFruitLoopsCereal;
import concurrency.Hare;
import concurrency.Race;
import concurrency.Tortoise;
import dundermifflin.PaperCompany;
import excersizes.ArrayRotation;
import excersizes.OrderStatistics;
import fruits.Kiwi;
import videogames.HalfLife;
import videogames.SciFi;
import videogames.SuperMario;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) throws IOException {

        OrderStatistics.meanMedianAge();

    }

    public static void readFromFileDunderMifflin() throws IOException {
        PaperCompany dunderMifflin = new PaperCompany();
        dunderMifflin.receiveShipments();
        dunderMifflin.readFromFile();
        //dunderMifflin.receiveOrders();
        dunderMifflin.processOrders();
        dunderMifflin.printSummary();
    }

    public static void games() {
        HalfLife halfLife = new HalfLife();
        halfLife.showNumberOfWeapons();
        SuperMario superMario = new SuperMario();
    }

    public static void catsAndDogs() {

        Rufus rufus = new Rufus();
        System.out.println("alive: " + rufus.isAlive());
        System.out.println("name: " + rufus.myName());
        rufus.bark();

        Brutus brutus = new Brutus();
        System.out.println("alive: " + brutus.getAnimal().isAlive());
        System.out.println("name: " + brutus.myName());
        brutus.getCat().meow();

    }

    public static void abstractClassExample() {
        List<AbstractCereal> cereals = new ArrayList<>();
        cereals.add(new SoggyFruitLoopsCereal());
        cereals.add(new CocoPuffsCereal());
        for (AbstractCereal cereal : cereals) {
            cereal.willIEatIt();
        }
        System.out.println(
                cereals.stream()
                        .collect(Collectors
                                .toMap(AbstractCereal::getClass, AbstractCereal::willIEatIt)
                        )
        );
    }

    public static void paperCompanyExceptions() {
        PaperCompany dunderMifflin = new PaperCompany();
        dunderMifflin.receiveShipments();
        dunderMifflin.receiveOrders();
        dunderMifflin.processOrders();
        dunderMifflin.printSummary();
    }

    public static void anotherTryCatchExample() {
        String input = "BANANAS 12";
        try {
            Integer number = Integer.valueOf(input);
            System.out.println("Our number is:" + number);
        } catch (Exception e) {
            System.out.println("Oops there was an issue");
        } finally {
            System.out.println("Done trying to convert " + input);
        }
    }

    public static void tortoiseAndHareRace() throws Exception {

        /*
         * race -----------------------------------------------------------|
         *        \--tortoise------------------------------------WAKE UP!--/
         *        \--hare-------------------------NAP----------------------/
         */
        AtomicInteger stepCounter = new AtomicInteger();
        Race race = new Race();
        Tortoise tortoise = new Tortoise(race, stepCounter);
        Hare hare = new Hare(race, stepCounter);

        hare.start(); // starts the hare thread
        tortoise.start(); // starts the tortoise thread

        hare.join();
        tortoise.join();

        // how do we know the race is finished?
        System.out.println("RACE FINISHED");
    }

    public static void manyThreadExample() throws Exception {

        Thread.sleep(5000);
        for (int a = 0; a < 200; a++) {

            Thread bwmThread = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        try {
                            sleep(500L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("BMW " + i);
                    }
                }
            };

            Thread audiThread = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        try {
                            sleep(5L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("AUDI " + i);
                    }
                }
            };
            bwmThread.start();
            audiThread.start();
        }
    }

    public static void restoreToyotaFromFile() throws Exception {
        File ourToyotaFile = new File("/Users/baskuis/projects/simplilearn/mytoyota.txt");
        FileInputStream ourToyotaFileStream = new FileInputStream(
                ourToyotaFile
        );
        ObjectInputStream ourToyotaFileObject = new ObjectInputStream(ourToyotaFileStream);

        Toyota ourNiceToyota = (Toyota) ourToyotaFileObject.readObject();

        System.out.println(ourNiceToyota.getVin());

        // we want our toyota instance back
    }

    public static void saveToyotaToFile() throws Exception {
        Toyota toyota = new Toyota("123456789");

        FileOutputStream fos = new FileOutputStream(
                new File("/Users/baskuis/projects/simplilearn/mytoyota.txt")
        );
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(toyota);
        oos.flush();

        System.out.println("Done");
    }

    public static void tryCatchExample() {
        String amIANumber = "BLA-12";
        try {
            System.out.println(
                    Integer.valueOf(amIANumber)
            );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Oops something is wrong");
        } finally {
            System.out.println("All done");
        }
    }

    public static void deserializtionExample() throws Exception {
        // try it here
        Tesla myTesla = (Tesla) readObject(new File("/Users/baskuis/projects/simplilearn/mytesla.txt"));

        System.out.println(myTesla.getVin());
    }

    public static void serializationExample() throws IOException {
        Tesla tesla = new Tesla("123");
        writeObject(tesla, new File("/Users/baskuis/projects/simplilearn/mytesla.txt"));
    }

    public static void writeObject(Object obj, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        oos.flush();
    }

    public static Object readObject(File file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return ois.readObject();
    }

    static void regexWithGroupsExample() {

        String message = "Hi merry, santa.2007@northpole.io christmas eve!";

        Pattern emailPattern = Pattern.compile("[ ]+(([a-z]+).[0-9]+@([^ ]{2,253}\\.[a-z]{2,55}))");

        // how do we get just 'santa'?

        // how do these group work


        //     /[a-z]+.([a-z]+).(([a-z]+).[a-z]+)./
        //             (      ) ((      )       )


        // hi.hello.how.areyou
        // what if we wanted just 'hello'? - group 1
        // what if we wanted just 'how.areyou'? - group 2
        // what if we wanted just 'how'? - group 3


        Matcher emailMatcher = emailPattern.matcher(message);

        if (emailMatcher.find()) {
            System.out.println("full group [" + emailMatcher.group() + "]");
            System.out.println("group 1 [" + emailMatcher.group(1) + "]");
            System.out.println("group 2 [" + emailMatcher.group(2) + "]");
        }

    }

    static void regexExample() {

        String message = "Hello, how are you? The hamburger, at restaurant" +
                "on 1234-123 street, WI 55321, U.S.A. you made was great. What was the recipe?" +
                "Could let me know? Call me at +1 123-123-1234 please";

        // regex --> why use it?

        // need to know the phone number??

        Pattern phoneMatch = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{4}");
        Matcher phoneMatcher = phoneMatch.matcher(message);
        if (phoneMatcher.find()) {
            System.out.println(phoneMatcher.group());
        }

    }

    static void arrayExample() {


        // array needs to be initialized with size
        // array indexes start with 0
        Integer[][] ourAgesYesterdayAndToday = new Integer[2][2];

        ourAgesYesterdayAndToday[0][0] = 26;
        ourAgesYesterdayAndToday[0][1] = 27;
        ourAgesYesterdayAndToday[1][0] = 33;
        ourAgesYesterdayAndToday[1][1] = 34;

        for (int i = 0; i < ourAgesYesterdayAndToday.length; i++) {
            System.out.println("One of us is " + ourAgesYesterdayAndToday[i][0] + " today");
            System.out.println("But they will be " + ourAgesYesterdayAndToday[i][1] + " next year");
        }
        // cannot resize after the fact
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
            }

            ;
        };

        Food dosa = new Food() {

            String getName() {
                return "dosa";
            }

            boolean isGood() {
                return true;
            }

            ;
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


        for (int i = 0; i < cars.size(); i++) {
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
        for (int i = 0; i < carsArray.length; i++) {
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

        for (int i = 0; i < carsList.size(); i++) {
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
