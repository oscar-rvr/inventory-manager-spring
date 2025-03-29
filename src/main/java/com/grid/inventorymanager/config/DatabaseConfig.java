package com.grid.inventorymanager.config;

import java.beans.BeanProperty;

@Configuration
public class DatabaseConfig {
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/managementDB";
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "i2345678";

    @Bean
    public DBConnection dbConnection(){
        return new DBConnection(JDBC_URL,USERNAME,PASSWORD);
    }

}
