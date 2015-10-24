package com.sachinhandiekar.sqltools.excel.model;

import com.google.gson.annotations.SerializedName;

/**
 * A class to denote the Worksheet JSON Model.
 * <p>
 * E.g.
 * <p>
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
 * ]
 */
public class Worksheet {

    @SerializedName("id")
    private long id;

    @SerializedName("sqlQuery")
    private String sqlQuery;

    @SerializedName("workSheetName")
    private String workSheetName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public String getWorkSheetName() {
        return workSheetName;
    }

    public void setWorkSheetName(String workSheetName) {
        this.workSheetName = workSheetName;
    }
}
