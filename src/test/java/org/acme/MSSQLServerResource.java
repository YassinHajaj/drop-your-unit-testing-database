package org.acme;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MSSQLServerResource implements QuarkusTestResourceLifecycleManager {

    @Container
    @SuppressWarnings("rawtypes")
    private final MSSQLServerContainer mssqlserver = new MSSQLServerContainer("mcr.microsoft.com/mssql/server:2019-latest")
            .acceptLicense();

    @Override
    public Map<String, String> start() {
        mssqlserver.start();
        while (!mssqlserver.isRunning()) {
            System.out.println(LocalDate.now() + " : Not running yet !");
        }
        Map<String, String> properties = new HashMap<>();
        properties.put("quarkus.datasource.db-kind", "mssql");
        properties.put("quarkus.datasource.username", mssqlserver.getUsername());
        properties.put("quarkus.datasource.password", mssqlserver.getPassword());
        properties.put("quarkus.datasource.jdbc.url", mssqlserver.getJdbcUrl());
        properties.put("quarkus.hibernate-orm.database.generation", "create");

        return properties;
    }

    @Override
    public void stop() {
        mssqlserver.stop();
    }
}
