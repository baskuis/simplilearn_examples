package videogames;

public interface FirstPersonShooter extends ThreeDimensional {

    String getName();

    int numberOfWeapons();

    default void showNumberOfWeapons() {
        System.out.println("Player has " + numberOfWeapons());
    }

}
