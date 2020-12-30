package catsanddogs;

public class Brutus implements HasName {

    Cat cat;
    Animal animal;

    public Brutus() {
        cat = new Cat();
        animal = new Animal();
    }

    @Override
    public String myName() {
        return "Brutus the fuzzy";
    }

    public Cat getCat() {
        return cat;
    }

    public Animal getAnimal() {
        return animal;
    }
}
