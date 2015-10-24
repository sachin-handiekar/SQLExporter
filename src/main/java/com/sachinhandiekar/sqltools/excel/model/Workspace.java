package com.sachinhandiekar.sqltools.excel.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * A class to denote the Workspace JSON Model.
 * <p>
 * E.g.
 * <p>
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
 */
public class Workspace {

    @SerializedName("id")
    private long id;

    @SerializedName("worksheet")
    private List<Worksheet> worksheets;

    @SerializedName("fileName")
    private String fileName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Worksheet> getWorksheets() {
        return worksheets;
    }

    public void setWorksheets(List<Worksheet> worksheets) {
        this.worksheets = worksheets;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


}
