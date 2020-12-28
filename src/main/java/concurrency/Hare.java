package concurrency;

public class Hare extends Thread {

    final Race race;

    public Hare(Race race) {
        this.race = race;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i+=5) {
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
