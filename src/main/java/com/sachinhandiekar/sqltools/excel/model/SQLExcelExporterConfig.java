package com.sachinhandiekar.sqltools.excel.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * A class to denote the SQLExcelExporterConfig JSON data model.
 * <p>
 * E.g.
 * <p>
 * {
 * "datasource": {
 * "className": "oracle.jdbc.driver.OracleDriver",
 * "jdbcUrl": "jdbc:oracle:thin:@//192.168.56.101:1521/orcl",
 * "username": "developer1",
 * "password": "password"
 * },
 * "workspace": [
 * {
 * "id": "1",
 * "worksheet": [
 * {
 * "id": "1",
 * "sqlQuery": "Select * from Person",
 * "workSheetName": "Person"
 * },
 * {
 * "id": "2",
 * "sqlQuery": "Select * from Stock",
 * "workSheetName": "Stock"
 * }
 * ],
 * "fileName": "C:/temp/workspace1.xls"
 * }
 * ]
 * }
 */
public class SQLExcelExporterConfig {

    @SerializedName("datasource")
    private Datasource datasource;

    @SerializedName("workspace")
    private List<Workspace> workspaces;

    public Datasource getDatasource() {
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }


    public List<Workspace> getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces(List<Workspace> workspaces) {
        this.workspaces = workspaces;
    }
}
