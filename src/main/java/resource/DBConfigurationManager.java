package resource;

import java.util.ResourceBundle;

public class DBConfigurationManager {

    private final static ResourceBundle resourcebundle = ResourceBundle.getBundle("dbconfiguration");

    public static String getProperty(String key) {
        return resourcebundle.getString(key);
    }
}
