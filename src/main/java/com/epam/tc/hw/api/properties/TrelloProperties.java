package com.epam.tc.hw.api.properties;

import java.util.ResourceBundle;

public class TrelloProperties {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("user");

    public static String key() {
        return resourceBundle.getString("key");
    }

    public static String token() {
        return resourceBundle.getString("token");
    }

    public static String baseUrl() {
        return resourceBundle.getString("trelloUrl");
    }
}
