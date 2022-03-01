package com.revature.foundations.util;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ConnectionFactoryTest {

    @Test
    public void test_getConnection_returnsValidConnection_givenThatPropertiesFileExistsWithCorrectInfo() {
        try {
            assertNotNull(ConnectionFactory.getInstance().getConnection());
        } catch (Throwable t) {
            fail();
        }
    }

    public void grabbingAConnection()
    {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
