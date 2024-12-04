package singleton;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private static Map<String, String> configuration = new HashMap<>();

    private ConfigurationManager(){
        System.out.println("instance created !");
    }

    public static synchronized ConfigurationManager getInstance(){
        if(instance == null)
            instance = new ConfigurationManager();

        return instance;
    }

    public void loadConfiguration(Map<String, String> configurationLoaded){
        if(!configuration.isEmpty())
            return;

        configuration = Collections.unmodifiableMap(configurationLoaded);
    }

    public String getValue(String key){
        return configuration.get(key);
    }
}
