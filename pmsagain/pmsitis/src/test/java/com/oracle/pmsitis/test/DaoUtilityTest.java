package com.oracle.pmsitis.test;

import com.oracle.pmsitis.repository.utilities.DaoUtility;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class DaoUtilityTest {
    @Test
    void testDatabasePropertiesLoaded() {
        Properties props = getDbSettings();
        assertNotNull(props.getProperty("driver"));
        assertNotNull(props.getProperty("url"));
        assertNotNull(props.getProperty("username"));
        assertNotNull(props.getProperty("password"));
    }

    @Test
    void testCreateConnectionSuccess() throws Exception {
        Connection conn = DaoUtility.createConnection();
        assertNotNull(conn);
        conn.close();
    }

    @Test
    void testCreateConnectionFailure() throws Exception {
        Properties props = getDbSettings();
        String oldUrl = props.getProperty("url");
        props.remove("url");
        Exception ex = assertThrows(Exception.class, DaoUtility::createConnection);
        assertTrue(ex.getMessage().contains("connection string not found"));
        props.setProperty("url", oldUrl); // restore
    }

    private Properties getDbSettings() {
        try {
            java.lang.reflect.Field field = DaoUtility.class.getDeclaredField("DB_SETTINGS");
            field.setAccessible(true);
            return (Properties) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}