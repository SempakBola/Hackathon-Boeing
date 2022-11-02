package helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PopulationProcessCSV {
    private static final String DATABASE = "jdbc:sqlite:database/homelessness.db";
    private static final String CSV_FILE = "database/population.csv";

    public static void main (String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "CREATE TABLE Population (lga_code INTEGER NOT NULL,lga_name TEXT NOT NULL,y2016 INTEGER, y2018 INTEGER, PRIMARY KEY (lga_code, lga_name) FOREIGN KEY (lga_code) REFERENCES LGA (lga_code));";
            System.out.println("Executing: " + query);
            statement.execute(query);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        } 
        
        try {
            Scanner lineScanner = new Scanner(new File(CSV_FILE));
            String header = lineScanner.nextLine();
            System.out.println("Heading row" + header + "\n");
            connection = DriverManager.getConnection(DATABASE);

            int row = 1;
            while (lineScanner.hasNext()) {
               String line = lineScanner.nextLine();
               Scanner rowScanner = new Scanner(line);
               rowScanner.useDelimiter(",");
   
               String lgaCode = rowScanner.next();
               String lgaName = rowScanner.next();
               int y2016 = rowScanner.nextInt();
               int y2018 = rowScanner.nextInt();
               Statement statement = connection.createStatement();
               String query = "INSERT into Population VALUES ("
                                 + lgaCode + ","
                                 + "'" + lgaName + "',"
                                 + y2016 + ","
                                 + y2018 + ")";
               System.out.println("Executing: " + query);
               statement.execute(query);
               row++;
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
         }

    }
}
