javac -d out/BookAPI .\BookAPI\src\module-info.java .\BookAPI\src\bookapi\Book.java
jar --create --file libs/bookAPI.jar --module-version 1.0 -C .\out\BookAPI\ .

javac -d .\out\Checkout --module-path libs .\Checkout\src\module-info.java .\Checkout\src\checkout\CheckoutSystem.java
jar --create --file libs/Checkout.jar --module-version 1.0 -C .\out\Checkout\ .

javac -d .\out\Inventory --module-path libs .\Inventory\src\module-info.java .\Inventory\src\inventory\BookInventory.java
jar --create --file libs/Inventory.jar --module-version 1.0 -C .\out\Inventory\ .

javac -d .\out\NotificationService --module-path libs .\NotificationService\src\module-info.java .\NotificationService\src\service\notification\NotificationService.java
jar --create --file libs/NotificationService.jar --module-version 1.0 -C .\out\NotificationService\ .

javac -d .\out\EmailNotification --module-path libs .\EmailNotification\src\module-info.java .\EmailNotification\src\service\impl\email\EmailNotification.java
jar --create --file libs/EmailNotification.jar --module-version 1.0 -C .\out\EmailNotification\ .

javac -d .\out\Reports --module-path libs .\Reports\src\module-info.java .\Reports\src\reports\InventoryReport.java
jar --create --file libs/Reports.jar --module-version 1.0 -C .\out\Reports\ .

javac -d .\out\App --module-path libs .\App\src\module-info.java .\App\src\app\App.java
jar --create --file libs/App.jar --module-version 1.0 --main-class app.App -C .\out\App\ .

java --module-path libs -m App