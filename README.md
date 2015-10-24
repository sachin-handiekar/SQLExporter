# SQLExcelExporter
A simple java application to import SQL Data into Excel Spreadsheet


Usage :

```
java -jar SQLExcelExporter.jar -config <file>
```


A JSON Configuration file can be used with the following content -

```json
{
  "datasource": {
    "className": "oracle.jdbc.driver.OracleDriver",
    "jdbcUrl": "jdbc:oracle:thin:@//localhost:1521/orcl",
    "username": "user",
    "password": "password"
  },
  "workspace": [
    {
      "id": "1",
      "worksheet": [
        {
          "id": "1",
          "sqlQuery": "Select * from Person",
          "workSheetName": "Person"
        },
        {
          "id": "2",
          "sqlQuery": "Select * from Stock",
          "workSheetName": "Stock"
        }
      ],
      "fileName": "C:/temp/workspace1.xls"
    }
  ]
}

```

The tool is single threaded and multiple worksheets with different dataset can be created using the tool.
