package resource;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static ResourceBundle resourcebundle = ResourceBundle.getBundle("config");

    public static String getProperty(String key) {
        return resourcebundle.getString(key);
    }
}
