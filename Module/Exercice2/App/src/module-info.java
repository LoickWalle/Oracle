module App {
    requires BookAPI;
    requires Checkout;
    requires EmailNotification;
    requires Inventory;
    requires Reports;
    requires NotificationService;
    exports app;
}