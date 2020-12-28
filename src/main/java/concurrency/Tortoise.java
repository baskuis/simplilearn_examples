package concurrency;

public class Tortoise extends Thread {

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
                        System.out.println("Tortoise yelles at Hare");
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
