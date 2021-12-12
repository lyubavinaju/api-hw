package com.epam.tc.hw.api.properties;

import java.util.ResourceBundle;

public class UserProperties {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("user");

    public static String key() {
        return resourceBundle.getString("key");
    }

    public static String token() {
        return resourceBundle.getString("token");
    }
}
