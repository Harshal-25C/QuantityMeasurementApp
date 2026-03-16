package com.quantity.util;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static final Properties properties = new Properties();

    static {

        try {

            InputStream input =
                    ApplicationConfig.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties");

            properties.load(input);

        } catch (Exception e) {

            throw new RuntimeException("Failed to load application.properties", e);

        }

    }

    public static String getProperty(String key) {

        return properties.getProperty(key);

    }

}