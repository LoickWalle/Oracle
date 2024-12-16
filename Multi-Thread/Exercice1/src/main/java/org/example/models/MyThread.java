package org.example.models;

public class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i<5 ; i++){
            System.out.println("Thread " + getName() + " avec classe extended : step " + i);
            try {
                Thread.sleep(300);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
