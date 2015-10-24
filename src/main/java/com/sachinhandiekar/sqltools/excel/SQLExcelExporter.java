package com.sachinhandiekar.sqltools.excel;

import com.google.gson.Gson;
import com.sachinhandiekar.sqltools.excel.model.SQLExcelExporterConfig;
import com.sachinhandiekar.sqltools.excel.model.Worksheet;
import com.sachinhandiekar.sqltools.excel.model.Workspace;
import org.apache.commons.cli.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * A main class to run the SQLExcelReporter
 *
 * Usage : java -jar SQLExcelExporter.jar -config <file>
 *
 */
public class SQLExcelExporter {

    private static final int HEADER_ROW = 0;

    private static final int DATA_ROW = 1;

    private static final Logger logger = LoggerFactory.getLogger(SQLExcelExporter.class);

    public static void main(String[] args) {
        try {

            // Parse the CLI arguments to get the location of the config
            String jsonConfigFilePath = parseCLIArgs(args);

            Gson gson = new Gson();

            // Load the JSON Config file
            logger.info("Reading JSON Config from :" + jsonConfigFilePath);

            BufferedReader br = new BufferedReader(new FileReader(jsonConfigFilePath));

            //convert the json string back to object
            SQLExcelExporterConfig sqlExcelImporterConfig = gson.fromJson(br, SQLExcelExporterConfig.class);

            // Load the driver class
            logger.debug("Loading driver class : " + sqlExcelImporterConfig.getDatasource().getClassName());
            Class.forName(sqlExcelImporterConfig.getDatasource().getClassName()).newInstance();

            // Create a connection to the database
            logger.debug("Creating a connection to the database...");
            Connection connection = DriverManager.getConnection(sqlExcelImporterConfig.getDatasource().getJdbcUrl(),
                    sqlExcelImporterConfig.getDatasource().getUserName(),
                    sqlExcelImporterConfig.getDatasource().getPassword());


            //Iterate through the list of workspace
            List<Workspace> workspaceList = sqlExcelImporterConfig.getWorkspaces();

            for (Workspace workspace : workspaceList) {
                HSSFWorkbook hssfWorkBook = new HSSFWorkbook();

                // Iterate through the list of worksheet for each workspace
                List<Worksheet> worksheets = workspace.getWorksheets();

                for (Worksheet workSheet : worksheets) {
                    ResultSet resultSet = getResultSetForQuery(workSheet.getSqlQuery(), connection);
                    generateWorksheet(workSheet.getWorkSheetName(), hssfWorkBook, resultSet);
                    resultSet.close();
                }

                FileOutputStream fileOut = new FileOutputStream(workspace.getFileName());
                hssfWorkBook.write(fileOut);
                fileOut.close();
            }


            logger.info("Data has been exported in excel file.");

            connection.close();
        } catch (Exception e) {
            logger.error("An error occurred while exporting data to excel.");
        }
    }

    private static String parseCLIArgs(String[] args) {
        CommandLineParser parser = new DefaultParser();
        String configFilePath = null;
        try {

            Options options = new Options();

            // add t option
            options.addOption("config", true, "Configuration file for SQLExcelExporter");
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);

            if (line.hasOption("config")) {
                configFilePath = line.getOptionValue("config");
            } else {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("SQLExcelExporter -config C:/pathto/sqlExcelExporter.json", options);
                System.exit(0);
            }
        } catch (Exception exp) {
            // oops, something went wrong
            logger.error("Parsing failed.  Reason: " + exp.getMessage());
        }

        return configFilePath;

    }

    private static void generateHeaderRow(HSSFSheet sheet, ResultSet rs) throws SQLException {

        HSSFRow headerRow = sheet.createRow(HEADER_ROW);

        ResultSetMetaData resultsetMetadata = rs.getMetaData();
        int columnCount = resultsetMetadata.getColumnCount();

        for (int i = 0; i < columnCount; i++) {
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(resultsetMetadata.getColumnName(i + 1));
        }
    }


    private static void populateRows(HSSFSheet sheet, ResultSet rs) throws SQLException {
        int rowCounter = DATA_ROW;
        while (rs.next()) {

            HSSFRow row = sheet.createRow(rowCounter);
            int columnCount = rs.getMetaData().getColumnCount();

            for (int i = 0; i < columnCount; i++) {
                row.createCell(i).setCellValue(rs.getString(i + 1));
            }
            rowCounter++;
        }
    }


    private static ResultSet getResultSetForQuery(String query, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    /**
     * Generate a worksheet and populate it with a header row and data rows
     *
     * @param workSheetName name of the worksheet
     * @param workbook a reference to the HFFSWorkbook (Apache POI)
     * @param resultSet a JDBC resultset containing the data
     * @throws SQLException if any error occurs
     */
    private static void generateWorksheet(String workSheetName, HSSFWorkbook workbook, ResultSet resultSet) throws SQLException {
        HSSFSheet workSheet = workbook.createSheet(workSheetName);

        // Create the first Header row
        // Get all the column names from the ResultSet
        generateHeaderRow(workSheet, resultSet);

        // Populate the data in the rows
        populateRows(workSheet, resultSet);
    }
}