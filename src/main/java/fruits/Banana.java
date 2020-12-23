package fruits;

public class Banana {

    int age = 0;

    private Banana() {

    }

    public Banana(int age) {
        this.age = age;
    }

    public void ripen() {
        this.age++;
    }

    public int getAge() {
        return age;
    }
}
