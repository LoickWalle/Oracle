import singleton.ConfigurationManager;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ConfigurationManager configurationManager1 = ConfigurationManager.getInstance();

        configurationManager1.loadConfiguration(getConfiguration());

        System.out.println("\nFirst instance :");
        System.out.println("db.host = " + configurationManager1.getValue("db.host"));
        System.out.println("db.port = " + configurationManager1.getValue("db.port"));
        System.out.println("app.name = " + configurationManager1.getValue("app.name"));

        ConfigurationManager configurationManager2 = ConfigurationManager.getInstance();

        System.out.println("\nSecond instance :");
        System.out.println("db.host = " + configurationManager2.getValue("db.host"));
        System.out.println("db.port = " + configurationManager2.getValue("db.port"));
        System.out.println("app.name = " + configurationManager2.getValue("app.name"));

        System.out.println("\nIs first and second instance equal ? " + (configurationManager1 == configurationManager2));
    }

    public static Map<String, String> getConfiguration(){
        Map<String, String> configuration = new HashMap<>();
        configuration.put("db.host", "localhost");
        configuration.put("db.port", "5432");
        configuration.put("app.name", "MyApplication");
        return configuration;
    }
}