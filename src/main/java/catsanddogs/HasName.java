package catsanddogs;

public interface HasName {

    String myName();

    default String callingMessage() {
        return "Hi " + myName();
    }

}
