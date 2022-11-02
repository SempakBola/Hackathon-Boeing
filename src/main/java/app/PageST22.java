package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * 
 * Edited by Victor Patrick Cabuenas, s3943468
 */
public class PageST22 implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page4.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Focused View On A LGA - Hope For Homes</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

        // Add some JS (external file)
        html = html + "<script type='text/javascript' src='filter.js'></script>";

        html = html + "</head>";

        // Add the body
        html = html + "<body>";

//------------------------------------------------------------------------------------------------

        // Add header content block
        html = html + """
            <div class='header'>
                <div class='row'>

                    <div class='column50NoChange'>
                        <h1>
                            <a href='/' title='Home'>
                                <img src='logo.png' class='top-image' alt='logo' style='height:75;'>
                                <i> HOPE FOR HOMES</i>
                            </a>
                        </h1>
                    </div>

                    <div class='column50NoChange' style='text-align:right;'>
                        
                        <!-- @author https://www.w3schools.com/howto/howto_google_translate.asp -->
                        <div id="google_translate_element"></div>
                        <script type="text/javascript">
                            function googleTranslateElementInit() {
                            new google.translate.TranslateElement({pageLanguage: 'en', layout: google.translate.TranslateElement.InlineLayout.SIMPLE}, 'google_translate_element');
                            }
                        </script>
                        <script type="text/javascript" src='//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit'></script>
                        
                        <br>

                        <!-- @author https://www.w3schools.com/howto/howto_css_dropdown.asp -->
                        <div class="dropdown">
                            <button class="dropbtn"><b>&#9776;</b></button>
                                <div class="dropdown-content">
                                    <a href='/' title='Home'>Homepage</a>
                                    <a href='mission.html' title='Mission'>Our Mission</a>
                                    <a href='page3.html' title='LGA Information'>Local Government Area Information</a>
                                    <a href='page4.html' title='Focused LGA'>Focused View On A LGA</a>
                                    <a href='page5.html' title='LGA Information'>Local Government Area Information 2.0</a>
                                    <a href='page6.html' title='Changes In Homelessness'>Changes In Homelessness Over Time</a>
                                </div>
                        </div>

                    </div>
                </div>
            </div>            
        """;

//------------------------------------------------------------------------------------------------

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <div class='topnav'>
                <a href='/' title='Home'>Homepage</a>
                <a href='mission.html' title='Mission'>Our Mission</a>
                <a href='page3.html' title='LGA Information'>Local Government Area Information</a>
                <a href='page4.html' title='Focused LGA'>Focused View On A LGA</a>
                <a href='page5.html' title='LGA Information'>Local Government Area Information 2.0</a>
                <a href='page6.html' title='Changes In Homelessness'>Changes In Homelessness Over Time</a>
            </div>
        """;

//------------------------------------------------------------------------------------------------

        // Add header content block
        html = html + """
            <div class='header2'>
                <h2>Focusing On A LGA</h2>
            </div>

            <div class='breadcrumbs'>
                <i><a href='/' title='Home'>Homepage</a> / <a href='page4.html' title='Focused LGA'>Focusing On A LGA</a></i>
            </div>
        """;

//------------------------------------------------------------------------------------------------

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Text
        html = html + "<h2>Choose a Local Government Area</h2>";
        //html = html + "<h5>Get focused information on your specified LGA</h5>";

        //html = html + "<h5>Filter Table By: TYPE, CODE/NAME, REGION</h5>";



        // Look up LGA Codes from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA22> codeLGA = jdbc.getAllLGACode();

        html = html + "<form action='/page4.html' method='post'>";
        html = html + "<fieldset><legend><b>Search by selecting the LGA, proportional value</b></legend>";

        html = html + "     <div class='form-group'>";
        html = html + "         <label for='lga_textbox' class='form-label'>Type the LGA Code/Name: </label>";
        
        html = html + "         <input class='form-control' list='lga_textbox' name='lga_textbox' placeholder='Search for LGA...' required>";
        
        html = html + "         <datalist id='lga_textbox'>";
        // Iterates their ArrayList to get values for the datalist
        for (LGA22 c : codeLGA) {
            html = html + "<option value='" + String.valueOf(c.getCode()) + "'>" + c.getName() + "</option>";
        }
        html = html + "         </datalist>";
        html = html + "     </div>";


        html = html + "   <div class='form-group'>";
        html = html + "      <label for='proportion_drop'>Select the Proportional Value (%):</label>";
        html = html + "      <select id='proportion_drop' name='proportion_drop' required>";
        html = html + "         <option value='' selected disabled hidden>Choose here</option>";
        html = html + "         <option value='All'>All</option>";
        html = html + "         <option value='Australia'>Australia</option>";
        html = html + "         <option value='State'>State</option>";
        html = html + "         <option value='LGA'>LGA</option>";
        html = html + "      </select>";
        html = html + "   </div>";

        html = html + "<br>";
        html = html + "         <button type='submit' class='btn btn-primary'>Search</button>";

        html = html + "</fieldset>";
        html = html  + "</form>";



        // Heading 
        html = html  + "<h4>Note: Data excludes LGA starting with 8 (ACT) and 9 (Other Australian Territories)" +
        " as their recorded population is not available</h4><br>";



        /* Get the Form Data
         *  from the drop down list
         * Need to be Careful!!
         *  If the form is not filled in, then the form will return null!
        */
        String lga_textbox = context.formParam("lga_textbox");
        String proportion_drop = context.formParam("proportion_drop");
        if ((lga_textbox == null || lga_textbox == "") & (proportion_drop == null || proportion_drop == "")) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html = html + "<h2><i>No Results to show - please select from above</i></h2>";
        } else {
            // If NOT NULL, then lookup the l by type!
            html = html + lgaCode(lga_textbox, proportion_drop);
        }




        // Close Content div
        html = html + "</div>";

//------------------------------------------------------------------------------------------------

        // Footer
        html = html + """
            <div class='footer'>
    
                <div class='footerlink'>
                    <a href='/' title='Home'>Homepage</a>&emsp;|&emsp;
                    <a href='mission.html' title='Mission'>Our Mission</a>&emsp;|&emsp;
                    <a href='page3.html' title='LGA Information'>Local Government Area Information</a>&emsp;|&emsp;
                    <a href='page4.html' title='Focused LGA'>Focused View On A LGA</a>&emsp;|&emsp;
                    <a href='page5.html' title='LGA Information'>Local Government Area Information 2.0</a>&emsp;|&emsp;
                    <a href='page6.html' title='Changes In Homelessness'>Changes In Homelessness Over Time</a>
                </div>

                <div class='footerlink2'>
                    <div class='row'>

                        <div class='column50NoChange'>
                            <h2>
                                <a href='/' title='Home'><img src='logo_footer.png' class='top-image' alt='logo' style=height:25;'>
                                    <i><b> HOPE FOR HOMES</i></b>
                                </a>
                            </h2>
                        </div>

                        <div class='column50NoChange' style='text-align:right;'>
                            <p>
                                Marco Bozovic - s3946269@student.rmit.edu.au <br>
                                Victor Patrick Cabuenas - s3943468@student.rmit.edu.au
                            </p>
                        </div>

                    </div>
                </div>
            </div>
        """;

//------------------------------------------------------------------------------------------------

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";
        
        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }
    
//------------------------------------------------------------------------------------------------

    public String lgaCode(String codeSelect, String proportionSelect) {

        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA22> lga = jdbc.getHomelessGroupByLGACode(codeSelect);

        String html = "";
        html = html + "<h2>Showing Information For; ";
        
        // Count used to break out of loops, so only displays once
        int count = 0;
        // Displays name
        for (LGA22 l : lga) {
            html = html + "Name: " + l.getName();
            count++;
            if (count == 1) {
                count = 0;
                break;
            }
        }

        html = html + ", Code: " + codeSelect;
        
        // Displays LGA type 
        for (LGA22 l : lga) {

            // Updates type name;
            String type = "";
            if (l.getType().equals("C")) {
                type = "Cities ";
            }
            else if (l.getType().equals("A")) {
                type = "Areas";
            }
            else if (l.getType().equals("") & l.getName().equals("Light (RegC)")) { // type RegC as in database it doesnt appear
                type = "Regional Councils";
            }
            else if (l.getType().equals("")) {
                type = "N/A";
            }
            else if (l.getType().equals("S")) {
                type = "Shires ";
            }
            else if (l.getType().equals("RC")) {
                type = "Rural Cities";
            }
            else if (l.getType().equals("B")) {
                type = "Boroughs";
            }
            else if (l.getType().equals("R")) {
                type = "Regional Councils";
            }
            else if (l.getType().equals("T")) {
                type = "Towns";
            }
            else if (l.getType().equals("DC")) {
                type = "District Councils";
            }
            else if (l.getType().equals("AC")) {
                type = "Aboriginal Councils";
            }
            else { // M
                type = "Municipalities/Municipal Councils";
            }

            html = html + ", Type: " + type;
            count++;
            if (count == 1) {
                count = 0;
                break;
            }
        }

        html = html + "</h2>";

        // Row, column
        html = html + "<div class='row'>";
        html = html + "<div class='column2' style='padding: 0px 15px'>";

        // Displays picture
        html = html + "<img src='/lgas18_code/" + codeSelect + ".png'   style='height:40%; vertical-align:center;'>";

        // Column div
        html = html + "</div>";

        html = html + "<div class='column2' style='padding: 0px 15px'>";

        // Area table
        html = html + """
            <table class="center">
                <thead>
                    <th>AreaSqkm</th>
                    <th>Latitude</th>
                    <th>Longitude</th>
                </thead>

                <tbody>
        """;

                for (LGA22 l : lga) {

                    html = html + 
                    "<tr>" +
        
                    "<td>"  + String.format("%.5f",l.getAreaSqkm()) + "</td>" + 
                    "<td>"  + String.format("%.5f",l.getLatitude()) + "</td>" + 
                    "<td>"  + String.format("%.5f", l.getLongitude()) + "</td>" + 

                    "</tr>";

                    count++;
                    if (count == 1) {
                        count = 0;
                        break;
                    }
                }
            
        html = html + "</tbody>";
        html = html + "</table>";

        // Pop table
        html = html + """
            <table class="center">
                <thead>
                    <th>Population 2016 (LGA)</th>
                    <th>Population 2018 (LGA)</th>
                </thead>

                <tbody>
        """;

                for (LGA22 l : lga) {

                    html = html + 
                    "<tr>" +

                    "<td>"  +  l.getPopulation16() + "</td>" + 
                    "<td>"  +  l.getPopulation18() + "</td>" + 

                    "</tr>";

                    count++;
                    if (count == 1) {
                        count = 0;
                        break;
                    }
                }
            
        html = html + "</tbody>";
        html = html + "</table>";


         // Pop State table
         html = html + """
            <table class="center">
                <thead>
                    <th>Population 2016 (State)</th>
                    <th>Population 2018 (State)</th>
                </thead>

                <tbody>
        """;

                for (LGA22 l : lga) {
                    // Gets the population for state based on LGA code
                    int state16 = 0;
                    int state18 = 0;
                    if (Integer.toString(l.getCode()).substring(0,1).equals("1")) {
                            state16 = 7980168;
                            state18 = 8089817;
                    }
                    else if (Integer.toString(l.getCode()).substring(0,1).equals("2")) {
                            state16 = 6462019;
                            state18 = 6596039;
                    }
                    else if (Integer.toString(l.getCode()).substring(0,1).equals("3")) {
                            state16 = 5009424;
                            state18 = 5094510;
                    }
                    else if (Integer.toString(l.getCode()).substring(0,1).equals("4")) {
                            state16 = 1736527;
                            state18 = 1751963;
                    }
                    else if (Integer.toString(l.getCode()).substring(0,1).equals("5")) {
                            state16 = 2594181;
                            state18 = 2621509;
                    }
                    else if (Integer.toString(l.getCode()).substring(0,1).equals("6")) {
                            state16 = 528298;
                            state18 = 534457;
                    }
                    else if (Integer.toString(l.getCode()).substring(0,1).equals("7")) {
                            state16 = 247058;
                            state18 = 245929;
                    }

                    html = html + 
                    "<tr>" +

                    "<td>"  + state16 + "</td>" + 
                    "<td>"  + state18 + "</td>" + 

                    "</tr>";

                    count++;
                    if (count == 1) {
                        count = 0;
                        break;
                    }
                }
            
        html = html + "</tbody>";
        html = html + "</table>";



        // Pop AUS table
        html = html + """
            <table class="center">
                <thead>
                    <th>Population 2016 (AUS)</th>
                    <th>Population 2018 (AUS)</th>
                </thead>

                <tbody>
        """;    
                    html = html + 
                    "<tr>" +

                    "<td>24557675</td>" + 
                    "<td>24934224</td>" + 

                    "</tr>";

        
        html = html + "</tbody>";
        html = html + "</table>";

        // Column div
        html = html + "</div>";

        // Row div 
        html = html + "</div>";

    

        // Adds table heading based on the proportion_drop value 
        String propTH = "";
        if (proportionSelect.equals("All")) {
            propTH = propTH + """
                <th col-index = 6>Proportional value (LGA)</th>
                <th col-index = 7>Proportional value (State)</th>
                <th col-index = 8>Proportional value (AUS)</th>
            """;
        }
        else if (proportionSelect.equals("Australia")) {
            propTH = propTH + """
                <th col-index = 6>Proportional value (AUS)</th>
            """;
        }
        else if (proportionSelect.equals("State")) {
            propTH = propTH + """
                <th col-index = 6>Proportional value (State)</th>
            """;
        }
        else {
            propTH = propTH + """
                <th col-index = 6>Proportional value (LGA)</th>
            """;
        }

    

        html = html + "<br>";
        
        html = html + """

            <div class="divScroll">

            <table class="center" style="width:75%" id="emp-table">

            <thead>

            <th col-index = 1>Year
                <select class="table-filter" onchange="filter_rows()">
                    <option value="all">All</option>
                </select>
            </th>

            <th col-index = 2>Gender 
                <select class="table-filter" onchange="filter_rows()">
                    <option value="all">All</option>
                </select>
            </th>

            <th col-index = 3>Age Group 
                <select class="table-filter" onchange="filter_rows()">
                    <option value="all">All</option>
                </select>
            </th>

            <th col-index = 4>Status 
                <select class="table-filter" onchange="filter_rows()">
                    <option value="all">All</option>
                </select>
            </th>

            <th col-index = 5>Number</th>
        """;

        html = html + propTH;
          

        html = html + "   </thead>";

             

        html = html + "<tbody>";
                
        
        for (LGA22 l : lga) {

            // Calculates state proportional value based on substring of LGA Code & Year
            Double state;
            if (Integer.toString(l.getCode()).substring(0,1).equals("1")) {
                if (l.getYear() == 2016) {
                    state = (Double.valueOf(l.getCount()) / 7980168.0 * 100.0);
                } 
                else { // Year is 2018
                    state = (Double.valueOf(l.getCount()) / 8089817.0 * 100.0);
                }
            }
            else if (Integer.toString(l.getCode()).substring(0,1).equals("2")) {
                if (l.getYear() == 2016) {
                    state = (Double.valueOf(l.getCount()) / 6462019.0 * 100.0);
                } 
                else { // Year is 2018
                    state = (Double.valueOf(l.getCount()) / 6596039.0 * 100.0);
                }            
            }
            else if (Integer.toString(l.getCode()).substring(0,1).equals("3")) {
                if (l.getYear() == 2016) {
                    state = (Double.valueOf(l.getCount()) / 5009424.0 * 100.0);
                } 
                else { // Year is 2018
                    state = (Double.valueOf(l.getCount()) / 5094510.0 * 100.0);
                }            
            }
            else if (Integer.toString(l.getCode()).substring(0,1).equals("4")) {
                if (l.getYear() == 2016) {
                    state = (Double.valueOf(l.getCount()) / 1736527.0 * 100.0);
                } 
                else { // Year is 2018
                    state = (Double.valueOf(l.getCount()) / 1751963.0 * 100.0);
                }            
            }
            else if (Integer.toString(l.getCode()).substring(0,1).equals("5")) {
                if (l.getYear() == 2016) {
                    state = (Double.valueOf(l.getCount()) / 2594181.0 * 100.0);
                } 
                else { // Year is 2018
                    state = (Double.valueOf(l.getCount()) / 2621509.0 * 100.0);
                }            
            }
            else if (Integer.toString(l.getCode()).substring(0,1).equals("6")) {
                if (l.getYear() == 2016) {
                    state = (Double.valueOf(l.getCount()) / 528298.0 * 100.0);
                } 
                else { // Year is 2018
                    state = (Double.valueOf(l.getCount()) / 534457.0 * 100.0);
                }            
            }
            else if (Integer.toString(l.getCode()).substring(0,1).equals("7")) {
                if (l.getYear() == 2016) {
                    state = (Double.valueOf(l.getCount()) / 247058.0 * 100.0);
                } 
                else { // Year is 2018
                    state = (Double.valueOf(l.getCount()) / 245929.0 * 100.0);
                }            
            }
             else  {
                state = (Double.valueOf(l.getCount()) / 0.0 * 100.0);
            }
             
            // Capitalises 
            String status;
            if ( l.getStatus().equals("homeless")) {
                status = "Homeless";
            }
            else {
                status = "At risk";
            }

            // Uppercase
            String sex;
            if ( l.getSex().equals("f")) {
                sex = "Female";
            }
            else {
                sex = "Male";
            }

            // Makes it readable 
            String age;
            if ( l.getAgeGroup().equals("_0_9")) {
                age = "0-9";
            }
            else if ( l.getAgeGroup().equals("_10_19")) {
                age = "10-19";
            }
            else if ( l.getAgeGroup().equals("_20_29")) {
                age = "20-29";
            }
            else if ( l.getAgeGroup().equals("_30_39")) {
                age = "30-39";
            }
            else if ( l.getAgeGroup().equals("_40_49")) {
                age = "40-49";
            }
            else if ( l.getAgeGroup().equals("_50_59")) {
                age = "50-59";
            }
            else {
                age = "60+";
            }

            // Population value corresponds to year 
            int yearpop;
            Double auspop = 0.0;
            if (l.getYear() == 2016) {
                yearpop = l.getPopulation16();
                auspop = 24557675.0;
            }
            else { // Year is 2018
                yearpop = l.getPopulation18();
                auspop = 24934224.0; 
            }


            // Determines table row based on proportion_drop value 
            String propTR = "";
            if (proportionSelect.equals("All")) {
            propTR = propTR + 
                "<td>"  + String.format("%.5f", (Double.valueOf(l.getCount()) /  Double.valueOf(yearpop) * 100.0)) + "</td>" + 
                "<td>"  + String.format("%.5f", state) + "</td>" + 
                "<td>"  + String.format("%.6f", (Double.valueOf(l.getCount()) /  auspop * 100.0)) + "</td>";
            }
            else if (proportionSelect.equals("Australia")) {
                propTR = propTR + 
                    "<td>"  + String.format("%.6f", (Double.valueOf(l.getCount()) /  auspop * 100.0)) + "</td>";
            }
            else if (proportionSelect.equals("State")) {
                propTR = propTR + 
                    "<td>"  + String.format("%.5f", state) + "</td>";
            }
            else {
                propTR = propTR +  
                    "<td>"  + String.format("%.5f", (Double.valueOf(l.getCount()) /  Double.valueOf(yearpop) * 100.0)) + "</td>";
            }
            
           

            html = html + 
            "<tr>" +
            "<td>"  + l.getYear() + "</td>" + 
            "<td>"  + sex + "</td>" + 
            "<td>"  + age + "</td>" + 
            "<td>"  + status + "</td>" + 
            "<td>"  + l.getCount() + "</td>" + 
            propTR +
            "</tr>";
        }

        html = html + "</tbody>";

        html = html + "</table>" ;

        /* @author Code Isshin, YouTube Link: https://youtu.be/tYdlt9q6Iug
         * GitHub Link: https://github.com/zangetsu-isshin/dropdown-filter/tree/main */    
        html= html + """ 
            <script>
                window.onload = () => {
                    console.log(document.querySelector("#emp-table > tbody > tr:nth-child(1) > td:nth-child(2) ").innerHTML);
                };
                getUniqueValuesFromColumn()
            </script>
        """;


        
        html = html + "</div>";

        return html;
    }


}
