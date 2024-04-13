package org.example;


public class Main {
    public static void main(String[] args) throws Exception {


        Container container = new Container();

        container.set(Dog.class.getName());
        container.set(Animal.class.getName());

        Animal animal = (Animal) container.get(Animal.class.getName());
        animal.bark();


    }


}