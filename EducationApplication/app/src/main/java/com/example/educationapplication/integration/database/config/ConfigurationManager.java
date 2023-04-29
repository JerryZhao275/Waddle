package com.example.educationapplication.integration.database.config;

public class ConfigurationManager {
    //TODO rename class ?

    private static WaddleDatabaseConfiguration configuration = null;

    public static WaddleDatabaseConfiguration configInstance() {
        if (configuration == null) {
            // TODO READ FROM ENVIRONMENT.PROPERTIES
            configuration = new WaddleDatabaseConfiguration(null, null, false);
        }
        return configuration;
    }

}
