import classes.Banana;
import classes.Kiwi;
import examples.RegexExample;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class App {

    public static void main(String[] args) throws InterruptedException, IOException {

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