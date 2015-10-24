package com.sachinhandiekar.sqltools.excel.model;

import com.google.gson.annotations.SerializedName;

/**
 * A class to denote the Datasource JSON data model.
 * <p>
 * E.g.
 * <p>
 * "datasource": {
 * "className": "oracle.jdbc.driver.OracleDriver",
 * "jdbcUrl": "jdbc:oracle:thin:@//192.168.56.101:1521/orcl",
 * "username": "developer1",
 * "password": "password"
 * }
 */
public class Datasource {

    @SerializedName("className")
    private String className;

    @SerializedName("jdbcUrl")
    private String jdbcUrl;

    @SerializedName("username")
    private String userName;

    @SerializedName("password")
    private String password;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Datasource{" +
                "className='" + className + '\'' +
                ", jdbcUrl='" + jdbcUrl + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
