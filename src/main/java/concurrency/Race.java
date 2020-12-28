package concurrency;

public class Race {

    int tortoise = 0;
    int hare = 0;
    boolean tortoiseFinished = false;
    boolean hareFinished = false;

    synchronized void liveView() {
        System.out.println("Tortoise: at " + tortoise + " miles " + (tortoiseFinished ? "finished!!" : "running"));
        System.out.println("Hare: at " + hare + " miles " + (hareFinished ? "finished!!" : "running"));
        System.out.println("======================" + "\n");
    }

    public void setTortoise(int tortoise) {
        this.tortoise = tortoise;
    }

    public void setHare(int hare) {
        this.hare = hare;
    }

    public void setTortoiseFinished(boolean tortoiseFinished) {
        this.tortoiseFinished = tortoiseFinished;
    }

    public void setHareFinished(boolean hareFinished) {
        this.hareFinished = hareFinished;
    }
}
