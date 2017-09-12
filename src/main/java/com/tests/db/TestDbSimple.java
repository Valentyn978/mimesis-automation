package com.tests.db;

import com.configuration.db.JdbcDriverSetUp;
import com.configuration.reporting.TestLogger;
import com.data.sets.DataSets;
import com.google.inject.Inject;
import com.tests.AbstractTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.sql.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class TestDbSimple extends AbstractTest {

    private static final Logger LOGGER = TestLogger.getLogger(TestDbSimple.class);
    public static final String TEST_DESC = "Check connect to DB Simple";

    @Inject
    private JdbcDriverSetUp jdbcDriverSetUp;


    @Test(description = TEST_DESC)
    public void test01GivenWorkEnvironmentWhenConnectThenCheck() throws SQLException {

        jdbcDriverSetUp.dbDriverSetUp();

        Connection connectionDb = jdbcDriverSetUp.getConnectionDb();

        assertNotNull(connectionDb);

        Statement stm = connectionDb.createStatement();
        ResultSet resultSet = stm.executeQuery("select * from mimesis.new_table;");

        // TODO investigation and fix
        Blob test_table_data = resultSet.getBlob("test_table_data");

        LOGGER.info(String.format("Test '%s' complete. Result: '%s'", TEST_DESC, test_table_data));
    }
}