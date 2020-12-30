package cereals;

abstract public class AbstractCereal {

    abstract boolean itIsGood();

    abstract String getName();

    public boolean willIEatIt() {
        if (itIsGood()) {
            System.out.println("Yes I'm eating " + getName() + " it is good");
            return true;
        }
        System.out.println("Not eating " + getName() + " it is not good");
        return false;
    }

}
