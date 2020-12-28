package concurrency;

public class Tortoise implements Runnable {

    final Race race;

    public Tortoise(Race race) {
        this.race = race;
    }

    public void run() {
        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(1L);
                synchronized (race) {
                    if (i == 98) {
                        race.notify();
                    }
                    race.setTortoise(i);
                    race.liveView();
                }
            } catch (InterruptedException e) {
                // ignore
            }
        }
        race.setTortoiseFinished(true);
        race.liveView();
    }
}
