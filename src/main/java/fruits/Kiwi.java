package fruits;

public class Kiwi {

    boolean good = true;

    public Kiwi() {

    }

    public Kiwi(boolean isGood) {
        this.good = isGood;
    }

    public void printGood(String howGood) {
        System.out.println(howGood + " " + this.good);
    }

    public void printGood() {
        System.out.println(this.good);
    }

}
