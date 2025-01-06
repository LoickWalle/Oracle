package service.impl.email;

import service.notification.NotificationService;

public class EmailNotification implements NotificationService{

    @Override
    public void sendNotification(String message) {
        System.out.println("Voici une notification : "+message);
    }
}
