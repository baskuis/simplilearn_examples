package examples;

public class FinallyRiddleExample {

    public static void finallyRiddle() {
        System.out.println(getSecret());
        System.out.println("And when do you see me");
    }

    static String getSecret() {
        try {
            return "secret";
        } finally {
            System.out.println("do you see me?");
        }
    }

}
