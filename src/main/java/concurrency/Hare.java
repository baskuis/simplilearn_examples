package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class Hare extends Thread {

    final Race race;

    final AtomicInteger steps;

    public Hare(Race race, AtomicInteger steps) {
        this.race = race;
        this.steps = steps;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i+=5) {
            int totalSteps = steps.getAndIncrement();
            System.out.println("totalSteps: " + totalSteps);
            try {
                sleep(1L);
                synchronized (race) {
                    if (i == 80) {
                        System.out.println("Hare taking nap");
                        race.wait();
                        System.out.println("Hare is woken up");
                    }
                    race.setHare(i);
                    race.liveView();
                }
            } catch (InterruptedException e) {
                // ignore
            }
        }
        race.setHareFinished(true);
        race.liveView();
    }

}
