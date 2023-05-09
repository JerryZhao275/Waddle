package com.example.educationapplication.integration.database.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {

    final private static String PATH_TO_PROPERTIES = "environment.properties";
    private static WaddleDatabaseConfiguration configuration = null;

    public static WaddleDatabaseConfiguration configInstance() {
        if (configuration == null) {
            configuration = new WaddleDatabaseConfiguration();
            try (InputStream is = ConfigurationManager.class.getClassLoader().getResourceAsStream(PATH_TO_PROPERTIES)) {
                Properties properties = new Properties();
                properties.load(is);
                boolean mocked = Boolean.parseBoolean(properties.getProperty("database.is.using.mocks", "false"));

                configuration = configuration.withMocking(mocked);
            } catch (IOException | NullPointerException ignored) {
                // couldn't read properties - don't worry about it, return with defaults
            }
        }
        return configuration;
    }

}
