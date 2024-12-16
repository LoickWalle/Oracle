package org.example.models;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i<5 ; i++){
            System.out.println("Thread " + Thread.currentThread().getName() + " avec interface implements : step " + i);
            try {
                Thread.sleep(300);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
