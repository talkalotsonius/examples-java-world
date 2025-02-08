package com.examples.api.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MavenPropertiesReader {
    private final Properties projectProperties;

    public MavenPropertiesReader(String propertyFileName) throws IOException {
        InputStream inputStream = getClass().getClassLoader()
                                            .getResourceAsStream(propertyFileName);
        this.projectProperties = new Properties();
        this.projectProperties.load(inputStream);
    }

    public String getProperty(String propertyName) {
        return this.projectProperties.getProperty(propertyName);
    }

}