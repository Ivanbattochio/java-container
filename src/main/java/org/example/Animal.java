package org.example;

public class Animal {

    private final Dog dog;

    public Animal(Dog dog) {
        this.dog = dog;
    }

    public void bark() {
        this.dog.bark();
    }
}
