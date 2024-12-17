package org.example;

import java.util.ArrayList;
import java.util.List;

public class SharedResource {
    private static final List<Integer> list = new ArrayList<>();

    public synchronized void add(int nb) {
        list.add(nb);
        System.out.println(Thread.currentThread().getName() + " a ajouté " + nb);
    }

    public synchronized void remove() {
        if (list.isEmpty())
            System.out.println(Thread.currentThread().getName() + " n'a rien à supprimer.");
        else {
            int item = list.removeFirst();
            System.out.println(Thread.currentThread().getName() + " a supprimé " + item);
        }
    }

    public static List<Integer> getList(){
        return list;
    }
}
