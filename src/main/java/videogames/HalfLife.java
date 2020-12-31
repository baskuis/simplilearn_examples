package videogames;

public class HalfLife implements FirstPersonShooter, SciFi, Multiplayer {

    int kills = 0;

    @Override
    public String getName() {
        return "Half Life";
    }

    public void killWasMadeInGame() {
        this.kills++;
    }

    @Override
    public int numberOfWeapons() {
        return 20;
    }

    @Override
    public int numberOfPlayers() {
        return 10;
    }

    @Override
    public int killCounter() {
        return kills;
    }

    @Override
    public boolean hasAliens() {
        return true;
    }

    @Override
    public boolean isInSpace() {
        return false;
    }

    @Override
    public boolean isDystopian() {
        return true;
    }

    @Override
    public int getMapDepth() {
        return 100;
    }

    @Override
    public int mapHeight() {

        // call some service
        // call some other service
        // then get the number
        // return


        return 100;
    }

    @Override
    public int mapWidth() {
        return 100;
    }
}
