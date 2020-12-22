package examples;

public class ThreadsExample {

    public static void twoThreads() {
        Thread a = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("Dogs are the best");
                }
            }
        };

        Thread b = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("Cats are the best");
                }
            }
        };

        a.start();
        b.start();
    }

}
