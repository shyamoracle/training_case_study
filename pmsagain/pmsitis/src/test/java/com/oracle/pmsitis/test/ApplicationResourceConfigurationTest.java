package com.oracle.pmsitis.test;

import com.oracle.pmsitis.ApplicationResourceConfiguration;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationResourceConfigurationTest {
    @Test
    void testDbSettingsLoaded() throws IOException {
        new ApplicationResourceConfiguration();
        Properties dbSettings = ApplicationResourceConfiguration.DB_SETTINGS;
        assertNotNull(dbSettings.getProperty("driver"));
        assertNotNull(dbSettings.getProperty("url"));
        assertNotNull(dbSettings.getProperty("username"));
        assertNotNull(dbSettings.getProperty("password"));
    }

    @Test
    void testDbQueriesLoaded() throws IOException {
        new ApplicationResourceConfiguration();
        Properties dbQueries = ApplicationResourceConfiguration.DB_QUERIES;
        assertNotNull(dbQueries.getProperty("select_all_products"));
        assertNotNull(dbQueries.getProperty("add_product"));
    }

    @Test
    void testLoadingMissingFileThrowsException() {
        IOException ex = assertThrows(IOException.class, () -> {
            Properties props = new Properties();
            ApplicationResourceConfiguration config = new ApplicationResourceConfiguration();
            config.loadFromPropertiesFile("missing.properties", props);  // only works if method is accessible
        });
        assertTrue(ex.getMessage().contains("Resource not found"));
    }

}