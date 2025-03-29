package com.grid.inventorymanager.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private final HikariDataSource dataSource;

    public DBConnection(String jdbcUrl, String username, String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(30000);
        config.setMaxLifetime(600000);
        config.setConnectionTimeout(3000);

        this.dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

    public void close(){
        dataSource.close();
    }
}
