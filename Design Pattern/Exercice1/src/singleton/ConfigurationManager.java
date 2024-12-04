package singleton;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private Map<String, String> configuration = new HashMap<>();

    private ConfigurationManager(){
        System.out.println("instance created !");
    }

    public static synchronized ConfigurationManager getInstance(){
        if(instance == null)
            instance = new ConfigurationManager();

        return instance;
    }

    public void loadConfiguration(Map<String, String> configurationLoaded){
        configuration = configurationLoaded;
    }

    public String getValue(String key){
        return configuration.get(key);
    }
}
