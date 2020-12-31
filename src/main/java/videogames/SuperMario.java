package videogames;

public class SuperMario implements Platformer {

    @Override
    public int jumpHeight() {
        return 4;
    }

    @Override
    public int movementSpeed() {
        return 10;
    }

    @Override
    public int mapHeight() {
        return 40;
    }

    @Override
    public int mapWidth() {
        return 300;
    }

}
