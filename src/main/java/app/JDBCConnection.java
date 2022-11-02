package app;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 *
 * @author Timothy Wiley, 2022. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    //private static final String DATABASE = "jdbc:sqlite:database/ctg.db";

    // If you are using the Homelessness data set replace this with the below
    private static final String DATABASE = "jdbc:sqlite:database/homelessness.db";

    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    /**
     * Get all of the LGAs in the database.
     * @return
     *    Returns an ArrayList of LGA objects
     */
    public ArrayList<LGA> getLGAs() {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT LGA.lga_name, HomlessGroup.status, HomlessGroup.sex, HomlessGroup.count FROM LGA JOIN HomlessGroup ON LGA.lga_code = HomlessGroup.lga_code WHERE HomlessGroup.age_group = '_0_9' AND HomlessGroup.sex = 'm'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String name16  = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = "";
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }



    // TODO: Add your required methods here



/* *************************************************************************
 * *************************************************************************
 */


    public ArrayList<LGA> getHomelessGroup16(String typeLGA) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM homlessgroup LEFT JOIN lga ON homlessgroup.lga_code = lga.lga_code LEFT JOIN Population ON lga.lga_code = Population.lga_code WHERE homlessgroup.year = 2016 AND lga.lga_type = '" + typeLGA + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String name16  = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = "";
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }


/* *************************************************************************
 * *************************************************************************
 */

    // USING
    public ArrayList<LGA22> getHomelessGroupByLGACode(String codeSelect) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA22> lgas = new ArrayList<LGA22>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM HomlessGroup JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code JOIN Population ON LGA.lga_code = Population.lga_code WHERE LGA.lga_code = '" + codeSelect + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String name = results.getString("lga_name");
                String status = results.getString("status");
                String sex = results.getString("sex");
                String ageGroup = results.getString("age_group");
                String type = results.getString("lga_type");

                int code = results.getInt("lga_code");
                int count = results.getInt("count");
                int population16 = results.getInt("pop2016");
                int population18 = results.getInt("pop2018");
                int year = results.getInt("year");

                Double areaSqkm = results.getDouble("area_sqkm");
                Double latitude = results.getDouble("latitude");
                Double longitude = results.getDouble("longitude");

                // Create a LGA Object
                LGA22 LGA22 = new LGA22(name, code, status, sex, ageGroup, count, type, areaSqkm,
                        latitude, longitude, population16, population18, year);

                // Add the lga object to the array
                lgas.add(LGA22);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }


/* *************************************************************************
 * *************************************************************************
 */
 
    
    public ArrayList<LGA22> getAllHomelessGroup16() {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA22> lgas = new ArrayList<LGA22>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT lga.lga_code, lga_name, status,sex, age_group, count, lga_type, area_sqkm, latitude, longitude, pop2016 FROM homlessgroup JOIN lga ON homlessgroup.lga_code = lga.lga_code  JOIN Population ON lga.lga_code = Population.lga_code WHERE homlessgroup.year = 2016";
            //String query = "SELECT * FROM homlessgroup JOIN lga ON homlessgroup.lga_code = lga.lga_code JOIN Population ON lga.lga_code = Population.lga_code WHERE homlessgroup.year = 2016";


            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String name = results.getString("lga_name");
                String status = results.getString("status");
                String sex = results.getString("sex");
                String ageGroup = results.getString("age_group");
                String type = results.getString("lga_type");

                int code = results.getInt("lga_code");
                int count = results.getInt("count");
                int population16 = results.getInt("pop2016");
                int population18 = 0;
                int year = 0;

                Double areaSqkm = results.getDouble("area_sqkm");
                Double latitude = results.getDouble("latitude");
                Double longitude = results.getDouble("longitude");

                // Create a LGA Object
                LGA22 LGA22 = new LGA22(name, code, status, sex, ageGroup, count, type, areaSqkm,
                        latitude, longitude, population16, population18, year);

                // Add the lga object to the array
                lgas.add(LGA22);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }


/* *************************************************************************
 * *************************************************************************
 */
     
    
    public ArrayList<LGA22> getAllHomelessGroupChange16Homeless() {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA22> lgas = new ArrayList<LGA22>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            //String query = "SELECT * FROM homlessgroup JOIN lga ON homlessgroup.lga_code = lga.lga_code JOIN Population ON lga.lga_code = Population.lga_code";

            String query = "SELECT  lga.lga_code, lga_name, status, sex, age_group, count, lga_type, pop2016 FROM homlessgroup JOIN lga ON homlessgroup.lga_code = lga.lga_code JOIN Population ON lga.lga_code = Population.lga_code WHERE homlessgroup.year ='2016' AND homlessgroup.status='homeless'";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String name = results.getString("lga_name");
                String status = results.getString("status");
                String sex = results.getString("sex");
                String ageGroup = results.getString("age_group");
                String type = results.getString("lga_type");

                int code = results.getInt("lga_code");
                int count = results.getInt("count");
                int population16 = results.getInt("pop2016");
                int population18 = 0;
                int year = 0;


                Double areaSqkm = 0.0;
                Double latitude = 0.0;
                Double longitude = 0.0;
                //Double areaSqkm = results.getDouble("area_sqkm");
                //Double latitude = results.getDouble("latitude");
                //Double longitude = results.getDouble("longitude");

                // Create a LGA Object
                LGA22 LGA22 = new LGA22(name, code, status, sex, ageGroup, count, type, areaSqkm,
                        latitude, longitude, population16, population18, year);

                // Add the lga object to the array
                lgas.add(LGA22);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }


/* *************************************************************************
 * *************************************************************************
 */


    public ArrayList<LGA22> getAllHomelessGroupChange16AtRisk() {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA22> lgas = new ArrayList<LGA22>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            //String query = "SELECT * FROM homlessgroup JOIN lga ON homlessgroup.lga_code = lga.lga_code JOIN Population ON lga.lga_code = Population.lga_code";

            String query = "SELECT  lga.lga_code, lga_name, status, sex, age_group, count, lga_type, pop2016 FROM homlessgroup JOIN lga ON homlessgroup.lga_code = lga.lga_code JOIN Population ON lga.lga_code = Population.lga_code WHERE homlessgroup.year ='2016' AND homlessgroup.status='at_risk'";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String name = results.getString("lga_name");
                String status = results.getString("status");
                String sex = results.getString("sex");
                String ageGroup = results.getString("age_group");
                String type = results.getString("lga_type");

                int code = results.getInt("lga_code");
                int count = results.getInt("count");
                int population16 = results.getInt("pop2016");
                int population18 = 0;
                int year = 0;


                Double areaSqkm = 0.0;
                Double latitude = 0.0;
                Double longitude = 0.0;
                //Double areaSqkm = results.getDouble("area_sqkm");
                //Double latitude = results.getDouble("latitude");
                //Double longitude = results.getDouble("longitude");

                // Create a LGA Object
                LGA22 LGA22 = new LGA22(name, code, status, sex, ageGroup, count, type, areaSqkm,
                        latitude, longitude, population16, population18, year);

                // Add the lga object to the array
                lgas.add(LGA22);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }


/* *************************************************************************
 * *************************************************************************
 */

    
    //USING
    public ArrayList<LGA22> getAllLGACode() {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA22> lgas = new ArrayList<LGA22>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DISTINCT lga.lga_code, lga_name FROM homlessgroup JOIN lga ON homlessgroup.lga_code = lga.lga_code  JOIN Population ON lga.lga_code = Population.lga_code";
            //String query = "SELECT * FROM homlessgroup JOIN lga ON homlessgroup.lga_code = lga.lga_code JOIN Population ON lga.lga_code = Population.lga_code WHERE homlessgroup.year = 2016";


            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String name = results.getString("lga_name");
                String status = "";
                String sex = "";
                String ageGroup = "";
                String type = "";

                int code = results.getInt("lga_code");
                int count = 0;
                int population16 = 0;
                int population18 = 0;
                int year = 0;



                Double areaSqkm = 0.0;
                Double latitude = 0.0;
                Double longitude = 0.0;

                // Create a LGA Object
                LGA22 LGA22 = new LGA22(name, code, status, sex, ageGroup, count, type, areaSqkm,
                        latitude, longitude, population16, population18, year);

                // Add the lga object to the array
                lgas.add(LGA22);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }


/* *************************************************************************
 * *************************************************************************
 */

    public ArrayList<LGA22> getChangeOverTime(String regionSelect, String sexSelect, String ageGroupSelect) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA22> lgas = new ArrayList<LGA22>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM homlessgroup LEFT JOIN lga ON homlessgroup.lga_code = lga.lga_code LEFT JOIN Population ON lga.lga_code = Population.lga_code WHERE homlessgroup.year = 2016 AND lga.lga_type = '" + regionSelect + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String name = results.getString("lga_name");
                String status = "";
                String sex = "";
                String ageGroup = "";
                String type = "";

                int code = results.getInt("lga_code");
                int count = 0;
                int population16 = 0;
                int population18 = 0;
                int year = 0;



                Double areaSqkm = 0.0;
                Double latitude = 0.0;
                Double longitude = 0.0;

                // Create a LGA Object
                LGA22 LGA22 = new LGA22(name, code, status, sex, ageGroup, count, type, areaSqkm,
                        latitude, longitude, population16, population18, year);

                // Add the lga object to the array
                lgas.add(LGA22);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }




/* *************************************************************************
 * *************************************************************************
 */



    public ArrayList<LGA22> getAllHomelessGroupBOTHYEAR() {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA22> lgas = new ArrayList<LGA22>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT lga.lga_code, lga_name, year, status, sex, age_group, count, lga_type, area_sqkm, latitude, longitude, pop2016, pop2018 FROM homlessgroup JOIN lga ON homlessgroup.lga_code = lga.lga_code  JOIN Population ON lga.lga_code = Population.lga_code";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String name = results.getString("lga_name");
                String status = results.getString("status");
                String sex = results.getString("sex");
                String ageGroup = results.getString("age_group");
                String type = results.getString("lga_type");

                int code = results.getInt("lga_code");
                int count = results.getInt("count");
                int population16 = results.getInt("pop2016");
                int population18 = results.getInt("pop2018");;
                int year = results.getInt("year");;

                Double areaSqkm = results.getDouble("area_sqkm");
                Double latitude = results.getDouble("latitude");
                Double longitude = results.getDouble("longitude");

                // Create a LGA Object
                LGA22 LGA22 = new LGA22(name, code, status, sex, ageGroup, count, type, areaSqkm,
                        latitude, longitude, population16, population18, year);

                // Add the lga object to the array
                lgas.add(LGA22);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }



/* *************************************************************************
 * *************************************************************************
 */
    // USING
    public ArrayList<LGA22> getChangeOverTime3VAR(String regionSelect, String sexSelect, String ageGroupSelect) {
    
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA22> lgas = new ArrayList<LGA22>();

        // Setup the variable for the JDBC connection
        Connection connection = null;
 
        try {
             // Connect to JDBC data base
             connection = DriverManager.getConnection(DATABASE);
 
             // Prepare a new SQL Query & Set a timeout
             Statement statement = connection.createStatement();
             statement.setQueryTimeout(30);
 
             // The Query
             String query = "SELECT * FROM newChangeAtRisk a JOIN newChangeHomeless b ON a.lga_code=b.lga_code WHERE a.lga_code LIKE '" + regionSelect + "%' ";
             
             // Get Result
             ResultSet results = statement.executeQuery(query);
 
             // Process all of the results
             while (results.next()) {
                 // Lookup the columns we need
                 String name = results.getString("lga_name");
                 int code = results.getInt("lga_code");
                 int atRiskChange = results.getInt("AT" + sexSelect + ageGroupSelect + "");
                 int homelessChange = results.getInt("H" + sexSelect + ageGroupSelect + "");
                 int popChange = results.getInt("POP Change");

                 int atRiskOG = results.getInt("AT" + sexSelect + ageGroupSelect + ":1");
                 int homelessOG = results.getInt("H" + sexSelect + ageGroupSelect + ":1");
                 int popOG = results.getInt("POP Change:1");
 
                 // Create a LGA Object
                 LGA22 LGA22 = new LGA22(name, code, atRiskChange, homelessChange, popChange, atRiskOG, homelessOG, popOG);
 
                 // Add the lga object to the array
                 lgas.add(LGA22);
 
             }
 
             // Close the statement because we are done with it
             statement.close();
        } catch (SQLException e) {
             // If there is an error, lets just pring the error
             System.err.println(e.getMessage());
        } finally {
             // Safety code to cleanup
             try {
                 if (connection != null) {
                     connection.close();
                 }
             } catch (SQLException e) {
                 // connection close failed.
                 System.err.println(e.getMessage());
             }
         }
 
        // Finally we return all of the lga
        return lgas;
     }





        //Marcos Methods---------------------------------------------------------------------------------------------------------------------------------------------------------------
     public ArrayList<LGA> getHomelessGroup18ByAge(String lga_name, String ageGroup, String sex, String status) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM HomlessGroup LEFT JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code LEFT JOIN Population ON LGA.lga_code = Population.lga_code WHERE HomlessGroup.year = 2018 AND LGA.lga_name LIKE '%" + lga_name + "%' AND HomlessGroup.age_group = '" + ageGroup + "' AND HomlessGroup.sex = '" + sex + "' AND HomlessGroup.status = '" + status + "' ";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                //String name = results.getString("lga_name");
                //String Status  = results.getString("status");
                //String Sex  = results.getString("sex");
                //int count    = results.getInt("count");

                String name16 = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = "";
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    public ArrayList<LGA> getHomelessGroup18ByAgeAndPop(String lga_name, String ageGroup, String sex, String status, String population) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT *, Population.pop2018 FROM HomlessGroup LEFT JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code LEFT JOIN Population ON LGA.lga_code = Population.lga_code WHERE HomlessGroup.year = 2018 AND LGA.lga_name LIKE '%" + lga_name + "%' AND HomlessGroup.age_group = '" + ageGroup + "' AND HomlessGroup.sex = '" + sex + "' AND HomlessGroup.status = '" + status + "' ";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                //String name = results.getString("lga_name");
                //String Status  = results.getString("status");
                //String Sex  = results.getString("sex");
                //int count    = results.getInt("count");

                String name16 = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = results.getString("pop2018");
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    public ArrayList<LGA> getSingleLGA(String lga_name) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM HomlessGroup LEFT JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code LEFT JOIN Population ON LGA.lga_code = Population.lga_code WHERE HomlessGroup.year = 2018 AND LGA.lga_name LIKE '%" + lga_name + "%'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                //String name = results.getString("lga_name");
                //String Status  = results.getString("status");
                //String Sex  = results.getString("sex");
                //int count    = results.getInt("count");

                String name16 = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = results.getString("pop2018");
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    public ArrayList<LGA> getLgaWithAge(String lga_name, String ageGroup) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM HomlessGroup LEFT JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code LEFT JOIN Population ON LGA.lga_code = Population.lga_code WHERE HomlessGroup.year = 2018 AND LGA.lga_name LIKE '%" + lga_name + "%' AND HomlessGroup.age_group = '" + ageGroup + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                //String name = results.getString("lga_name");
                //String Status  = results.getString("status");
                //String Sex  = results.getString("sex");
                //int count    = results.getInt("count");

                String name16 = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = results.getString("pop2018");
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    public ArrayList<LGA> getLgaWithSex(String lga_name, String sex) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM HomlessGroup LEFT JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code LEFT JOIN Population ON LGA.lga_code = Population.lga_code WHERE HomlessGroup.year = 2018 AND LGA.lga_name LIKE '%" + lga_name + "%' AND HomlessGroup.sex = '" + sex + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                //String name = results.getString("lga_name");
                //String Status  = results.getString("status");
                //String Sex  = results.getString("sex");
                //int count    = results.getInt("count");

                String name16 = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = results.getString("pop2018");
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    public ArrayList<LGA> getLgaWithStatus(String lga_name, String status) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM HomlessGroup LEFT JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code LEFT JOIN Population ON LGA.lga_code = Population.lga_code WHERE HomlessGroup.year = 2018 AND LGA.lga_name LIKE '%" + lga_name + "%' AND HomlessGroup.status = '" + status + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                //String name = results.getString("lga_name");
                //String Status  = results.getString("status");
                //String Sex  = results.getString("sex");
                //int count    = results.getInt("count");

                String name16 = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = results.getString("pop2018");
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    public ArrayList<LGA> getLgaWithPop(String lga_name, String population) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT *, Population.pop2018 FROM HomlessGroup LEFT JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code LEFT JOIN Population ON LGA.lga_code = Population.lga_code WHERE HomlessGroup.year = 2018 AND LGA.lga_name LIKE '%" + lga_name + "%'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                //String name = results.getString("lga_name");
                //String Status  = results.getString("status");
                //String Sex  = results.getString("sex");
                //int count    = results.getInt("count");

                String name16 = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = results.getString("pop2018");
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    public ArrayList<LGA> getLgaWithPopAndAge(String lga_name, String population, String ageGroup) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT *, Population.pop2018 FROM HomlessGroup LEFT JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code LEFT JOIN Population ON LGA.lga_code = Population.lga_code WHERE HomlessGroup.year = 2018 AND LGA.lga_name LIKE '%" + lga_name + "%' AND HomlessGroup.age_group = '" + ageGroup + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                //String name = results.getString("lga_name");
                //String Status  = results.getString("status");
                //String Sex  = results.getString("sex");
                //int count    = results.getInt("count");

                String name16 = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = results.getString("pop2018");
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    public ArrayList<LGA> getLgaWithPopAndSex(String lga_name, String population, String sex) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT *, Population.pop2018 FROM HomlessGroup LEFT JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code LEFT JOIN Population ON LGA.lga_code = Population.lga_code WHERE HomlessGroup.year = 2018 AND LGA.lga_name LIKE '%" + lga_name + "%' AND HomlessGroup.sex = '" + sex + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                //String name = results.getString("lga_name");
                //String Status  = results.getString("status");
                //String Sex  = results.getString("sex");
                //int count    = results.getInt("count");

                String name16 = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = results.getString("pop2018");
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    public ArrayList<LGA> getLgaWithPopAndStatus(String lga_name, String population, String status) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT *, Population.pop2018 FROM HomlessGroup LEFT JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code LEFT JOIN Population ON LGA.lga_code = Population.lga_code WHERE HomlessGroup.year = 2018 AND LGA.lga_name LIKE '%" + lga_name + "%' AND HomlessGroup.status = '" + status + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                //String name = results.getString("lga_name");
                //String Status  = results.getString("status");
                //String Sex  = results.getString("sex");
                //int count    = results.getInt("count");

                String name16 = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = results.getString("pop2018");
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    public ArrayList<LGA> getLgaWithPopAgeSex(String lga_name, String population, String ageGroup, String sex) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT *, Population.pop2018 FROM HomlessGroup LEFT JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code LEFT JOIN Population ON LGA.lga_code = Population.lga_code WHERE HomlessGroup.year = 2018 AND LGA.lga_name LIKE '%" + lga_name + "%' AND HomlessGroup.age_group = '" + ageGroup + "'AND HomlessGroup.sex = '" + sex + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                //String name = results.getString("lga_name");
                //String Status  = results.getString("status");
                //String Sex  = results.getString("sex");
                //int count    = results.getInt("count");

                String name16 = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = results.getString("pop2018");
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    public ArrayList<LGA> getLgaWithPopAgeStatus(String lga_name, String population, String ageGroup, String status) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT *, Population.pop2018 FROM HomlessGroup LEFT JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code LEFT JOIN Population ON LGA.lga_code = Population.lga_code WHERE HomlessGroup.year = 2018 AND LGA.lga_name LIKE '%" + lga_name + "%' AND HomlessGroup.age_group = '" + ageGroup + "'AND HomlessGroup.status = '" + status + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                //String name = results.getString("lga_name");
                //String Status  = results.getString("status");
                //String Sex  = results.getString("sex");
                //int count    = results.getInt("count");

                String name16 = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = results.getString("pop2018");
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    public ArrayList<LGA> getLgaWithPopSexStatus(String lga_name, String population, String sex, String status) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT *, Population.pop2018 FROM HomlessGroup LEFT JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code LEFT JOIN Population ON LGA.lga_code = Population.lga_code WHERE HomlessGroup.year = 2018 AND LGA.lga_name LIKE '%" + lga_name + "%' AND HomlessGroup.sex = '" + sex + "'AND HomlessGroup.status = '" + status + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                //String name = results.getString("lga_name");
                //String Status  = results.getString("status");
                //String Sex  = results.getString("sex");
                //int count    = results.getInt("count");

                String name16 = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = results.getString("pop2018");
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    public ArrayList<LGA> getLgaWithAgeAndSex(String lga_name, String ageGroup, String sex) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM HomlessGroup LEFT JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code LEFT JOIN Population ON LGA.lga_code = Population.lga_code WHERE HomlessGroup.year = 2018 AND LGA.lga_name LIKE '%" + lga_name + "%' AND HomlessGroup.age_group = '" + ageGroup + "' AND HomlessGroup.sex = '" + sex + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                //String name = results.getString("lga_name");
                //String Status  = results.getString("status");
                //String Sex  = results.getString("sex");
                //int count    = results.getInt("count");

                String name16 = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = results.getString("pop2018");
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    public ArrayList<LGA> getLgaWithAgeAndStatus(String lga_name, String ageGroup, String status) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM HomlessGroup LEFT JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code LEFT JOIN Population ON LGA.lga_code = Population.lga_code WHERE HomlessGroup.year = 2018 AND LGA.lga_name LIKE '%" + lga_name + "%' AND HomlessGroup.age_group = '" + ageGroup + "' AND HomlessGroup.status = '" + status + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                //String name = results.getString("lga_name");
                //String Status  = results.getString("status");
                //String Sex  = results.getString("sex");
                //int count    = results.getInt("count");

                String name16 = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = results.getString("pop2018");
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    public ArrayList<LGA> getLgaWithSexAndStatus(String lga_name, String sex, String status) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM HomlessGroup LEFT JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code LEFT JOIN Population ON LGA.lga_code = Population.lga_code WHERE HomlessGroup.year = 2018 AND LGA.lga_name LIKE '%" + lga_name + "%' AND HomlessGroup.sex = '" + sex + "' AND HomlessGroup.status = '" + status + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                //String name = results.getString("lga_name");
                //String Status  = results.getString("status");
                //String Sex  = results.getString("sex");
                //int count    = results.getInt("count");

                String name16 = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = results.getString("pop2018");
                String age = results.getString("age_group");
                String income1 = "";
                String income2 = "";
                String income3 = "";
                String income4 = "";

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    //income

    public ArrayList<LGA> getLgaIncome(String lga_name, String ageGroup, String sex, String status, String population) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT *, Population.pop2018, MedianIncome.household_weekly_income, MedianIncome.age, MedianIncome.mortgage_repay_monthly, MedianIncome.rent_weekly FROM HomlessGroup LEFT JOIN LGA ON HomlessGroup.lga_code = LGA.lga_code LEFT JOIN Population ON LGA.lga_code = Population.lga_code LEFT JOIN MedianIncome ON LGA.lga_code = MedianIncome.lga_code WHERE HomlessGroup.year = 2018 AND LGA.lga_name LIKE '%" + lga_name + "%' AND HomlessGroup.age_group = '" + ageGroup + "' AND HomlessGroup.sex = '" + sex + "' AND HomlessGroup.status = '" + status + "' ";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                //String name = results.getString("lga_name");
                //String Status  = results.getString("status");
                //String Sex  = results.getString("sex");
                //int count    = results.getInt("count");

                String name16 = results.getString("lga_name");
                String name10  = results.getString("status");
                String name2  = results.getString("sex");
                int code2    = results.getInt("count");
                String pop = results.getString("pop2018");
                String age = results.getString("age_group");
                String income1 = results.getString("household_weekly_income");
                String income2 = results.getString("age");
                String income3 = results.getString("mortgage_repay_monthly");
                String income4 = results.getString("rent_weekly");

                // Create a LGA Object
                //LGA lga = new LGA(name, Status, Sex, count);
                LGA lga = new LGA(code2, name16, name10, name2, pop, age, income1, income2, income3, income4);

                lgas.add(lga);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }
}
