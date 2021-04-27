package com.yana.microservices.camelmicroservicesa.routes.ftp;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class DbConnectionTest {

    @Test
    public void testDB(){
        DataSource dataSource = new DriverManagerDataSource(
                "jdbc:postgresql://localhost:5432/testdb?user=postgres&password=postgres");
        CamelContext context = new DefaultCamelContext();

        context.getRegistry().bind("myTestDb", dataSource);

    }
}
