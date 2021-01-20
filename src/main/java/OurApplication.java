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
import desserts.*;
import dundermifflin.PaperCompany;
import excersizes.LongestIncreasingSubsequence;
import excersizes.OurCircularLinkedList;
import excersizes.OurDoublyLinkedList;
import excersizes.OurSinglyLinkedList;
import fruits.Kiwi;
import hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import robot.RobotEntity;
import videogames.HalfLife;
import videogames.SuperMario;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OurApplication {

    public static void main(String[] args) {
        try {
            hibernateSession = HibernateUtils
                    .buildSessionFactory()
                    .openSession();
            hibernateSession.beginTransaction();

            /* Insert some robots */
            for (int i = 0; i <= 10; i++) {
                RobotEntity robot = new RobotEntity();
                robot.setName("awesome robot number:" + i);
                robot.setWeight(i * 100L);
                hibernateSession.save(robot);
            }

            /* Get all robots */
            CriteriaBuilder builder = hibernateSession.getCriteriaBuilder();
            CriteriaQuery<RobotEntity> criteria = builder.createQuery(RobotEntity.class);
            criteria.from(RobotEntity.class);
            List<RobotEntity> robots = hibernateSession.createQuery(criteria).getResultList();

            robots.forEach((r) -> System.out.println(
                    "r: " + r.getId() +
                            " name:" + r.getName() +
                            " weight:" + r.getWeight()
            ));

            hibernateSession.getTransaction().commit();
        } catch(Exception sqlException) {
            if (null != hibernateSession.getTransaction()) {
                hibernateSession.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (hibernateSession != null) {
                hibernateSession.close();
            }
        }
    }

    static Session hibernateSession;


    static int counter = 0;

    public static void createReadUpdateDelete() {

        DrinkDAO drinkDAO = new DrinkDAOImpl();
        DrinkDTO drink = drinkDAO.create(new DrinkDTO(
                "orange juice",
                true
        ));

        drinkDAO.remove(4L);

        drink.setName("not orange juice");
        drinkDAO.update(drink);

        List<DrinkDTO> drinks = drinkDAO.getAll();
        drinks.forEach((d) -> System.out.println(d.toString()));

    }

    public static void dbLifeCycleExample() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root")) {

            String ourDatabase = "mynewdatabase";
            try (Statement statement = conn.createStatement()) {

                int affectedRows = statement.executeUpdate("CREATE DATABASE " + ourDatabase);
                if (affectedRows == 0) {
                    System.out.println("no changes");
                } else {
                    System.out.println("database[" + ourDatabase + "] created");
                }

                useDatabase(ourDatabase, statement);

                dropDatabase(ourDatabase, statement);
            }

        } catch (SQLException e) {
            System.out.println("SQL exception");
            e.printStackTrace();
        }
    }

    public static void dropDatabase(String db, Statement statement) {
        try {
            statement.execute("DROP DATABASE " + db);
            System.out.println("database [" + db + "] dropped");
        } catch (SQLException e) {
            System.out.println("Unable to run query");
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    public static void useDatabase(String db, Statement statement) {
        try {
            statement.execute("USE " + db);
            System.out.println("switched");
        } catch (SQLException e) {
            System.out.println("Unable to run query");
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    public static void storedProcedureExample() {
        DessertDAOImpl dessertDAO = new DessertDAOImpl();
        String art = dessertDAO.getGoodDesserts("Totallygood", true);
        System.out.println(art);

        System.out.println(dessertDAO.isGood(1L));
    }

    public static void dbConnectExample() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/desserts?createDatabaseIfNotExist=true", "root", "root")) {
            if (conn != null) {
                System.out.println("Connected to the database!");
                String query = "select id, name from goodstuff";
                try (Statement stmt = conn.createStatement()) {
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        System.out.println(id + ", " + name);
                    }
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void streamExample() {
        List<String> words = Arrays.asList("HI", "how", "are", "you");

        words.stream()
                .filter((word) -> word.length() > 2) //Predicate
                .map((word) -> word + " is great") //Function
                .forEach(OurApplication.PrintUtils::printIt); //Consumer

    }

    public static class PrintUtils {
        public static void printIt(String it) {
            System.out.println("It is: " + it);
        }
    }

    public static void functionalIntefacesExample() {
        String game = "hide and go seek";

        Consumer<String> play = (g) -> {
            System.out.println("Starting the game " + g);
            try { Thread.sleep(500); } catch (InterruptedException e) { }
            System.out.println("Game is over");
        };

        play.accept(game);

        play.accept("soccer");

        BiFunction<Integer, String, String> showAge =
                (age, name) -> name + " is " + age + " years old";

        System.out.println(showAge.apply(33, "Mellisa"));
        System.out.println(showAge.apply(38, "Bob"));

        TriFunction<Integer, Integer, String, String> showAgeAndHeight =
                (age, height, name) -> name + " is " + age + " years old and is " + height + "ft tall";

        System.out.println(showAgeAndHeight.apply(33, 6,"Mellisa"));
        System.out.println(showAgeAndHeight.apply(23, 5,"Suzy"));
        System.out.println(showAgeAndHeight.apply(43, 7,"Sally"));
    }

    @FunctionalInterface
    interface TriFunction<T, U, P, R> {
        R apply(T t, U u, P p);
    }

    public static void predicateExample() {
        List<String> nameList=Arrays.asList("Sachin", "Samuels");
        System.out.println("Printing all the names starting with S");
        checkString(nameList, (name->name.startsWith("S")));
        System.out.println("Printing all the names ending with s");
        checkString(nameList, (name->name.endsWith("s")));
    }

    public static void checkString(List<String> nameList, Predicate<String> p){
        for (String name: nameList) {
            if (p.test(name)) {
                System.out.println(name);
            }
        }
    }


    @FunctionalInterface
    interface MyFunInterface {
        String makeOfficial(String name); // abstract // does not have body
    }

    static void functionalInterfaceExample() {

        // functional interface + lambda
        MyFunInterface printItNice = (anyName) -> "The distinquished " + anyName;
        System.out.println(printItNice.makeOfficial("Bishnu"));
        System.out.println(printItNice.makeOfficial("Chris"));
        System.out.println(printItNice.makeOfficial("Jon"));

        MyFunInterface printItGoofy = (anyName) -> "The silly " + anyName;
        System.out.println(printItGoofy.makeOfficial("Bas"));

        printThemAll(
                new String[]{"suzy", "sally", "sam"},
                (name) -> "The funny " + name
        );

    }

    static void printThemAll(String[] names, MyFunInterface howToDoIt) {
        for (String name: names) {
            System.out.println(howToDoIt.makeOfficial(name));
        }
    }

    static void daoExample() {

        GenericDAO<DessertDTO> dessertDAO = new DessertDAOImpl();
        List<DessertDTO> desserts = dessertDAO.getAll();

        DessertDTO pudding = new DessertDTO(
                12L, "pudding", false
        );

        dessertDAO.create(pudding);
        System.out.println("List size: " + dessertDAO.count());
        dessertDAO.remove(pudding);
        System.out.println("List size: " + dessertDAO.count());

        for (DessertDTO dessert : desserts) {
            System.out.println(dessert);
        }

    }

    static void quickSortExample() {

        // O(n*log(n))

        // quick sort
        int[] numbers = { 8, 2, 4, 1, 99, 23, 12, 11, 13, 5, 6, 7, 3, 2, 1, 5, 7, 8, 9, 1 };

        // pivot point: last element
        quickSort(numbers, 0, numbers.length - 1);

        for(int n : numbers) {
            System.out.println("n: " + n);
        }

        System.out.println("evaluations: " + counter);

    }

    static void quickSort(int[] data, int start, int end) {
        if (start < end) {
            int pivot_index = partition(data, start, end);
            quickSort(data, start, pivot_index - 1);
            quickSort(data, pivot_index + 1, end);
        }
    }

    static int partition(int[] data, int start, int end) {
        int pivotValue = data[end];
        int small_index = start - 1;
        for (int currentIndex = start; currentIndex < end; currentIndex++) {
            counter++;
            if (data[currentIndex] < pivotValue) {
                small_index++;
                int temp = data[small_index];
                data[small_index] = data[currentIndex];
                data[currentIndex] = temp;
            }
        }

        int temp_a = data[small_index + 1];
        data[small_index + 1] = data[end];
        data[end] = temp_a;
        return small_index + 1;
    }

    static void definitelyWorkingMergeSort() {
        // merge sort
        int[] numbers = { 12, 11, 13, 5, 6, 7 };

        mergeSort(numbers, 0, numbers.length - 1);

        for(int n : numbers) {
            System.out.println("n: " + n);
        }
    }

    static void mergeSort(int[] data, int left_index, int right_index) {
        if (left_index >= right_index) {
            return;
        }
        int middle_index = (left_index + right_index) / 2;
        mergeSort(data, left_index, middle_index);
        mergeSort(data, middle_index + 1, right_index);
        merge(data, left_index, middle_index, right_index);
    }

    static void merge(int[] data, int left_index, int middle_index, int right_index) {
        int left_size = middle_index - left_index + 1;
        int right_size = right_index - middle_index;
        int[] leftData = new int[left_size];
        int[] rightData = new int[right_size];
        for (int i = 0; i < left_size; i++) {
            leftData[i] = data[left_index + i];
        }
        for (int i = 0; i < right_size; i++) {
            rightData[i] = data[middle_index + 1 + i];
        }
        int indexOfLeftData = 0;
        int indexOfRightData = 0;
        int startingIndex = left_index;
        while(
              indexOfLeftData < left_size &&
              indexOfRightData < right_size
        ) {
            if (leftData[indexOfLeftData] <= rightData[indexOfRightData]) {
                data[startingIndex] = leftData[indexOfLeftData];
                indexOfLeftData++;
            } else {
                data[startingIndex] = rightData[indexOfRightData];
                indexOfRightData++;
            }
            startingIndex++;
        }
        while (indexOfLeftData < left_size) {
            data[startingIndex] = leftData[indexOfLeftData];
            indexOfLeftData++;
            startingIndex++;
        }
        while (indexOfRightData < right_size) {
            data[startingIndex] = rightData[indexOfRightData];
            indexOfRightData++;
            startingIndex++;
        }
    }

    static void insertionSort() {

        // insertion sort
        int[] numbers = {11, 19, 18, 5, 16, 15, 14, 13, 12, 20, 10, 9, 8, 7, 6, 17, 4, 3, 2, 1};

        /*
         *                      PASS
         * 2, 3, 3, 1, 7, 8, 1   |
         * |                     |
         *    |  |  |            |
         * 1, 2, 3, 3, 7, 8, 1   |
         *          |            |
         *             |  |  |   |
         * 1, 2, 3, 1, 3, 7, 8   |
         * |
         *    |  |  |
         * 1, 1, 2, 3, 3, 7, 8
         * -----------------------
         */
        int evaluations = 0;

        while (true) {
            boolean inOrder = true;
            int seriesLength = 1;
            for (int i = 0; i < numbers.length - 1; i += seriesLength) {
                seriesLength = 0;
                for (int r = i + 1; r < numbers.length; r++) {
                    evaluations++;
                    seriesLength++;
                    int right = numbers[r];
                    if (right < numbers[r - 1]) {
                        for (int a = r; a > i; a--) {
                            numbers[a] = numbers[a - 1];
                        }
                        numbers[i] = right;
                        inOrder = false;
                    }
                }
            }
            if (inOrder) {
                break;
            }
        }

        for (int n: numbers) {
            System.out.println("n: " + n);
        }
        System.out.println("performed evaluations: " + evaluations);

    }

    static void bubbleSort() {

        int[] numbers = {1, 3, 2, 1, 3, 3, 4, 7, 8, 9};

        while (true) {

            // assume it's in order
            boolean inOrder = true;

            // let's check if that is true
            for (int i = 0; i < numbers.length - 1; i++) {

                int left = numbers[i];
                int right = numbers[i + 1];

                // hey these are not in order
                if (right < left) {
                    numbers[i] = right;
                    numbers[i + 1] = left;

                    // so we'll need to check again
                    inOrder = false;
                }

            }
            if (inOrder) {
                break;
            }
        }

        for (int n: numbers) {
            System.out.println("n: " + n);
        }

    }

    static void selectionSort() {

        //Selection sort
        int[] numbers = {4,2,4,6,7,8,1,4,6,8,4,2,4,6,7,8,1,4,6,8,4,2,4,6,7,8,1,4,6,8,4,2,4,6,7,8,1,4,6,8,4,2,4,6,7,8,1,4,6,8,4,2,4,6,7,8,1,4,6,8,4,2,4,6,7,8,1,4,6,8,4,2,4,6,7,8,1,4,6,8};

        int evaluations = 0;

        // Put the number in incrementing order
        // Loop through our numbers
        for (int i = 0; i < numbers.length ; i++) {

            // This is one of those numbers
            int outer = numbers[i];

            // Lets find if we can go lower?
            int lowest = outer;
            int key = i;
            for (int k = i + 1; k < numbers.length; k++) {
                evaluations++;
                int inner = numbers[k];
                if (inner < lowest) {
                    lowest = inner;
                    key = k;
                }
            }

            // Swap them out
            numbers[i] = lowest;
            numbers[key] = outer;

        }

        for(int i: numbers) {
            System.out.println("Number: " + i);
        }

        System.out.println("We performed " + evaluations + " evaluations");


    }

    static void searchProblems() {
        int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,15,16,17,18,19,20,21,22,23};
        int lookingFor = 10;

        System.out.println("Linear search");
        //Linear search
        //O(n)
        for (int number: numbers) {
            System.out.println("--------");
            if (number == lookingFor) {
                System.out.println("Found " + number);
            }
        }

        //Binary search
        //O(log n)
        System.out.println("Binary search");
        int index = binarySearch(lookingFor, 0, numbers.length, numbers);
        System.out.println("Found answer at index: " + index);

        //Exponential search
        //O(log n)
        System.out.println("Exponential search");
        index = exponentialSearch(lookingFor, numbers);
        System.out.println("Found answer at index: " + index);

    }

    static int exponentialSearch(int lookingFor, int[]data) {
        System.out.println("--------");
        if (data[0] == lookingFor) {
            return 0;
        }
        int range = 2;
        int index = 1;
        int previousIndex = 0;
        while (data[index] < lookingFor) {
            previousIndex = index;
            index += range;
            range = range * 2;
        }
        return binarySearch(lookingFor, previousIndex, index, data);
    }

    static int binarySearch(int lookingFor, int start, int end, int[]data) {
        System.out.println("--------");
        int evaluateIndex = (end + start) / 2;
        if (data[evaluateIndex] == lookingFor) {
            System.out.println("found " + lookingFor + " at index " + evaluateIndex);
            return evaluateIndex;
        }
        if (data[evaluateIndex] > lookingFor) {
            return binarySearch(lookingFor, start, evaluateIndex, data);
        } else {
            return binarySearch(lookingFor, evaluateIndex, end, data);
        }
    }



    public static void longestSubSequence() {
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();

        Integer[] result = longestIncreasingSubsequence.getLongestIncreasingSubsequence();

        System.out.println("Winning sequence is");
        for (Integer i : result) {
            System.out.println("number: " + i);
        }
        System.out.println("End of sequence");
    }

    public static void lineAtCarWashQueueExample() {
        final Queue<String> lineAtCarwash = new ArrayBlockingQueue<>(200);

        /** Carwash manager watching line */
        new Thread(() -> {
            while(true) {
                System.out.println("Line is " + lineAtCarwash.size() + " long");
                if (lineAtCarwash.size() > 20) {
                    System.out.println("hurry up");
                }
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) { }
            }
        }).start();

        /** Cars arriving at the carwash */
        new Thread(() -> {
            while(true) {
                lineAtCarwash.add("Dodge Ram");
                lineAtCarwash.add("Nissan");
                lineAtCarwash.add("Charger");
                lineAtCarwash.add("Chevy Camero");
                lineAtCarwash.add("Honda Civic");
                lineAtCarwash.add("Buggy");
                lineAtCarwash.add("Jeep");
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) { }
            }
        }).start();

        /** Carwash washing cars */
        new Thread(() -> {
            while (true) {
                if (Math.random() > 0.5) {
                    System.out.println("Tom is washing : " + lineAtCarwash.poll());
                } else {
                    System.out.println("Suzy is washing: " + lineAtCarwash.poll());
                    System.out.println("Sally is washing: " + lineAtCarwash.poll());
                    System.out.println("Barry is washing: " + lineAtCarwash.poll());
                }
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) { }
            }
        }).start();
    }

    public static void stackExample() {
        /** FILO */
//        Stack<String> favoriteClothes = new Stack<>();
//        favoriteClothes.push("jeans");
//        favoriteClothes.push("hoodie");
//        favoriteClothes.push("cap");
//        favoriteClothes.push("cap");
//        favoriteClothes.push("cap");
//        favoriteClothes.push("cap");
//        while(!favoriteClothes.empty()) {
//            System.out.println(favoriteClothes.pop());
//        }

        String[] words = {
                "banana",
                "lion",
                "liger",
                "giraffe",
                "elephant",
                "dog",
                "fish",
                "tiger",
        };

        Stack<String> stack = new Stack<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!stack.empty()) {
                String previous = stack.peek();
                if (previous.length() < word.length()) {
                    stack.push(word);
                } else {
                    printItemsInStack(stack);
                    stack.removeAllElements();
                    stack.push(word);
                }
            } else {
                stack.push(word);
            }
        }

        printItemsInStack(stack);
    }

    private static void printItemsInStack(Stack<String> stack) {
        System.out.println("==========");
        for (Object entry: stack.toArray()) {
            System.out.println(entry);
        }
    }

    public static void doublyLinkedListExample() {
        OurDoublyLinkedList<String> list = new OurDoublyLinkedList<>();
        list.add("Narges");
        list.add("Nazmul");
        list.add("Mustafa");
        list.add("Nate");
        list.add("Benjamin");
        list.add("Dan");
        list.add("Indra");
        list.add("James");
        list.printAllValues();
    }

    public static void ourCircularLinkedListExample() {
        //        OurCircularLinkedList<String> playList = new OurCircularLinkedList<>();
//        playList.add("Fly me to the moon");
//        playList.add("Take Five");
//        playList.add("Boys of Summer");
//        playList.add("Dirty Laundry");
//        playList.add("wonderful life");
//        playList.add("Hells Bells");
//        playList.add("Reptilia");
//        playList.add("Living on a Prayer");
//        playList.printSomeEntries(1000);

        OurSinglyLinkedList<String> ourNames = new OurSinglyLinkedList<>();
        ourNames.add("Narges");
        ourNames.add("Nazmul");
        ourNames.add("Mustafa");
        ourNames.add("Nate");
        ourNames.add("Benjamin");
        ourNames.add("Dan");
        ourNames.add("Indra");
        ourNames.add("James");
        ourNames.printAllValues();

        OurCircularLinkedList<String> ourCircularNames = new OurCircularLinkedList<>(ourNames);
        ourCircularNames.printSomeEntries(300);
    }

    public static void ourSinglyLinkedListExample() {
        OurSinglyLinkedList<String> ourNames = new OurSinglyLinkedList<>();
        ourNames.add("Narges");
        ourNames.add("Nazmul");
        ourNames.add("Mustafa");
        ourNames.add("Nate");
        ourNames.add("Benjamin");
        ourNames.add("Dan");
        ourNames.add("Indra");
        ourNames.add("James");
        ourNames.printAllValues();
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
