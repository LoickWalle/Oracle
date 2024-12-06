package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        try {
            Class<?> person = Class.forName("org.example.Person");

            System.out.println("Nom de la class : " + person.getName());
            System.out.println("Nom simple : " + person.getSimpleName());
            System.out.println("Nom package : " + person.getPackage());

            System.out.println("Champs de ma classe : ");
            for(Field field : person.getDeclaredFields()){
                System.out.println("- " + field.getName() + ", type : " + field.getType());
            }

            System.out.println("Liste des méthodes : ");
            for(Method method : person.getDeclaredMethods()){
                System.out.println("- " + method.getName() + ", return type : " + method.getReturnType());
            }

            Constructor<?> constructor = person.getConstructor(String.class, int.class);
            Object personInstance = constructor.newInstance("Toto", 42);
            System.out.println("Instance de person cree : " + personInstance);

            Method sayHello = person.getDeclaredMethod("sayHello");
            sayHello.setAccessible(true);
            sayHello.invoke(personInstance);

            Field nameField = person.getDeclaredField("name");
            nameField.setAccessible(true);
            nameField.set(personInstance, "Bob");
            System.out.println("Nom après changement : " + nameField.get(personInstance));

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}