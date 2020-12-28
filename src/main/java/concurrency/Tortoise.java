package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class Tortoise extends Thread {

    final Race race;

    final AtomicInteger steps;

    public Tortoise(Race race, AtomicInteger steps) {
        this.race = race;
        this.steps = steps;
    }

    public void run() {
        for (int i = 0; i <= 100; i++) {
            int totalSteps = steps.getAndIncrement();
            System.out.println("totalSteps: " + totalSteps);
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
