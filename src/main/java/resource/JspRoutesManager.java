package resource;

import java.util.ResourceBundle;

public class JspRoutesManager {
    private final static ResourceBundle resourcebundle = ResourceBundle.getBundle("jsproutes");

    public static String getProperty(String key) {
        return resourcebundle.getString(key);
    }
}
