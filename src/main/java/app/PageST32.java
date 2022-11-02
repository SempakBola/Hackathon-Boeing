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
public class PageST32 implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page6.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Changes In Homelessness Over Time - Hope For Homes</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        
        // Add some JS (external file)
        html = html + "<script type='text/javascript' src='filter.js'></script>";
        html = html + "<script type='text/javascript' src='sortNumbers.js'></script>";


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
                <h2>Changes In Homelessness Over Time</h2>
            </div>

            <div class='breadcrumbs'>
                <i><a href='/' title='Home'>Homepage</a> / <a href='page6.html' title='Changes In Homelessness'>Changes In Homelessness Over Time</a></i>
            </div>
        """;

//------------------------------------------------------------------------------------------------

        // Add Div for page Content
        html = html + "<div class='content'>";


        // Start the List HTML
        //html = html + "<ul>";

        html = html + "<h2>Changes In The Rate Of Homelessness Between 2016 & 2018</h2>";
        html = html + "<h5>Data shows the difference of 2018 from 2016 - increasing or decreasing</h5>";



        html = html + "<form action='/page6.html' method='post'>";
        
        html = html + "<fieldset><legend><b>Search by selecting region, gender, age group</b></legend>";

        html = html + "   <div class='form-group'>";
        html = html + "      <label for='region_drop'>Select the Region:</label>";
        html = html + "      <select id='region_drop' name='region_drop' required>";
        html = html + "         <option value='' selected disabled hidden>Choose here</option>";
        html = html + "         <option value='1'>NSW</option>";
        html = html + "         <option value='2'>Victoria</option>";
        html = html + "         <option value='3'>QLD</option>";
        html = html + "         <option value='4'>South Australia</option>";
        html = html + "         <option value='5'>Western Australia</option>";
        html = html + "         <option value='6'>Tasmania</option>";
        html = html + "         <option value='7'>Northern Territory</option>";
        html = html + "      </select>";
        html = html + "   </div>";

        html = html + "   <div class='form-group'>";
        html = html + "      <label for='sex_drop'>Select the Gender:</label>";
        html = html + "      <select id='sex_drop' name='sex_drop' required>";
        html = html + "         <option value='' selected disabled hidden>Choose here</option>";
        html = html + "         <option value='F'>Female</option>";
        html = html + "         <option value='M'>Male</option>";
        html = html + "      </select>";
        html = html + "   </div>";

        html = html + "   <div class='form-group'>";
        html = html + "      <label for='ageGroup_drop'>Select the Age Group:</label>";
        html = html + "      <select id='ageGroup_drop' name='ageGroup_drop' required>";
        html = html + "         <option value='' selected disabled hidden>Choose here</option>";
        html = html + "         <option value='09'>0-9</option>";
        html = html + "         <option value='1019'>10-19</option>";
        html = html + "         <option value='2029'>20-29</option>";
        html = html + "         <option value='3039'>30-39</option>";
        html = html + "         <option value='4049'>40-49</option>";
        html = html + "         <option value='5059'>50-59</option>";
        html = html + "         <option value='60'>60+</option>";
        html = html + "      </select>";
        html = html + "   </div>";

        html = html + "<br>";
        html = html + "   <button type='submit' class='btn btn-primary'>Search</button>";

        html = html + "</fieldset>";
        html = html + "</form>";



        // Heading 
        html = html  + "<h4>Note: Data excludes LGA starting with 8 (ACT) and 9 (Other Australian Territories)" +
        " as their recorded population is not available</h4><br>";



        /* Get the Form Data
         *  from the drop down list
         * Need to be Careful!!
         *  If the form is not filled in, then the form will return null!
        */
        String region_drop = context.formParam("region_drop");
        String sex_drop = context.formParam("sex_drop");
        String ageGroup_drop = context.formParam("ageGroup_drop");

        // When all fields specified - 3VAR 
        if ((region_drop == null || region_drop == "") & (sex_drop == null || sex_drop == "") & 
        (ageGroup_drop == null || ageGroup_drop == "")) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html = html + "<br><h2><i>No Results to show - please select from above</i></h2><br>";
        }
        else {
            // If NOT NULL, then lookup data using the inputs

            // Buttons - Filters number values ASC or DESC

            html = html + """
                <!-- @author https://www.w3schools.com/howto/howto_css_dropdown.asp -->

                <div class="dropdown">
                    <button class="dropbtn"><b>Sort table</b></button>
                        <div class="dropdown-content" style='left:0; width:300px'>
        
                            <a onclick='sortLGACodeASC()' title='Reset'>Reset</a>

                            <a onclick='sortAtRiskDESC()' title='At Risk: Increase-to-Decrease'>At Risk: Increase-to-Decrease</a>
                            <a onclick='sortAtRiskASC()' title='At Risk: Decrease-to-Increase'>At Risk: Decrease-to-Increase</a>
                            
                            <a onclick='sortAtRiskPerDESC()' title='At Risk %: Increase-to-Decrease'>At Risk %: Increase-to-Decrease</a>
                            <a onclick='sortAtRiskPerASC()' title='At Risk %: Decrease-to-Increase'>At Risk %: Decrease-to-Increase</a>


                            <a onclick='sortHomelessDESC()' title='Homeless: Increase-to-Decrease'>Homeless: Increase-to-Decrease</a>
                            <a onclick='sortHomelessASC()' title='Homeless: Decrease-to-Increase'>Homeless: Decrease-to-Increase</a>
                
                            <a onclick='sortHomelessPerDESC()' title='Homeless %: Increase-to-Decrease'>Homeless %: Increase-to-Decrease</a>
                            <a onclick='sortHomelessPerASC()' title='Homeless %: Decrease-to-Increase'>Homeless %: Decrease-to-Increase</a>
                

                            <a onclick='sortPopulationDESC()' title='Population: Increase-to-Decrease'>Population: Increase-to-Decrease</a>
                            <a onclick='sortPopulationASC()' title='Population: Decrease-to-Increase'>Population: Decrease-to-Increase</a>
          
                            <a onclick='sortPopulationPerDESC()' title='Population %: Increase-to-Decrease'>Population %: Increase-to-Decrease</a>
                            <a onclick='sortPopulationPerASC()' title='Population %: Decrease-to-Increase'>Population %: Decrease-to-Increase</a>
          

                            <a onclick='sortRatioDESC()' title='Ratio: Increase-to-Decrease'>Ratio: Increase-to-Decrease</a>
                            <a onclick='sortRatioASC()' title='Ratio: Decrease-to-Increase'>Ratio: Decrease-to-Increase</a>
          
                        </div>  
                </div>
            """;
            

            html = html + allStats(region_drop,  sex_drop, ageGroup_drop);
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

    public String allStats(String regionSelect, String sexSelect, String ageGroupSelect) {
        
        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA22> lga = jdbc.getChangeOverTime3VAR(regionSelect, sexSelect, ageGroupSelect);
        
        String html = "";


        // Region to words for label
        if (regionSelect.equals("1")) {
            regionSelect = "NSW";
        }
        else if (regionSelect.equals("2")) {
            regionSelect = "Victoria";
        }
        else if (regionSelect.equals("3")) {
            regionSelect = "QLD";
        }
        else if (regionSelect.equals("4")) {
            regionSelect = "South Australia";
        }
        else if (regionSelect.equals("5")) {
            regionSelect = "Western Australia";
        }
        else if (regionSelect.equals("6")) {
            regionSelect = "Tasmania";
        }
        else {
            regionSelect = "Northern Territory";
        }

        if (sexSelect.equals("F")) {
            sexSelect = "Female";
        }
        else {
            sexSelect = "Male";
        }

        if (ageGroupSelect.equals("09")) {
            ageGroupSelect = "0-9";
        }
        else if (ageGroupSelect.equals("1019")) {
            ageGroupSelect = "10-19";
        }
        else if (ageGroupSelect.equals("2029")) {
            ageGroupSelect = "20-29";
        }
        else if (ageGroupSelect.equals("3039")) {
            ageGroupSelect = "30-39";
        }
        else if (ageGroupSelect.equals("4049")) {
            ageGroupSelect = "40-49";
        }
        else if (ageGroupSelect.equals("5059")) {
            ageGroupSelect = "50-59";
        }
        else {
            ageGroupSelect = "60+";
        }

        html = html + "<h2>Showing Information For; Region: " + regionSelect + ", Gender: " + sexSelect + ", Age Group: " + ageGroupSelect + "</h2>";

        


        // Table
        html = html + """
            <div class="divScroll">

            <table class="center" style="width:75%" id="emp-table">

            <thead>
            
            <th col-index = 1>Name
                <select class="table-filter" onchange="filter_rows()">
                    <option value="all">All</option>
                </select>
            </th>
            
            <th col-index = 2>Code
                <select class="table-filter" onchange="filter_rows()">
                    <option value="all">All</option>
                </select>
            </th>

            <th col-index = 3>At Risk</th>
            <th col-index = 4>At Risk %</th>

            <th col-index = 5>Homeless</th>
            <th col-index = 6>Homeless %</th>

            <th col-index = 7>Population</th>
            <th col-index = 8>Population %</th>

            <th col-index = 9>Ratio (Homeless to At Risk)</th>

            </thead>

        """;

        html = html + "<tbody>";
                
        for (LGA22 l : lga) {



            Double ratio = 0.0;
            if (String.format("%.3f", (Double.valueOf(l.getHomelessChange()) / Double.valueOf(l.getAtRiskChange()))).equals("NaN") ||
            String.format("%.3f", (Double.valueOf(l.getHomelessChange()) / Double.valueOf(l.getAtRiskChange()))).equals("Infinity") || 
            String.format("%.3f", (Double.valueOf(l.getHomelessChange()) / Double.valueOf(l.getAtRiskChange()))).equals("-Infinity")) {
                ratio = 0.000;
            } else {
                ratio = (Double.valueOf(l.getHomelessChange()) / Double.valueOf(l.getAtRiskChange()));
            }


            Double atRisk = 0.0;
            if (String.format("%.3f", (Double.valueOf(l.getAtRiskChange()) / Double.valueOf(l.getAtRiskOG()) * 100.0)).equals("NaN") ||
            String.format("%.3f", (Double.valueOf(l.getAtRiskChange()) / Double.valueOf(l.getAtRiskOG()) * 100.0)).equals("Infinity") || 
            String.format("%.3f", (Double.valueOf(l.getAtRiskChange()) / Double.valueOf(l.getAtRiskOG()) * 100.0)).equals("-Infinity")) {
                atRisk = 0.000;
            } else {
                atRisk = (Double.valueOf(l.getAtRiskChange()) / Double.valueOf(l.getAtRiskOG()) * 100.0);
            }    


            Double homeless = 0.0;
            if (String.format("%.3f", (Double.valueOf(l.getHomelessChange()) / Double.valueOf(l.getHomelessOG()) * 100.0)).equals("NaN") ||
            String.format("%.3f", (Double.valueOf(l.getHomelessChange()) / Double.valueOf(l.getHomelessOG()) * 100.0)).equals("Infinity") || 
            String.format("%.3f", (Double.valueOf(l.getHomelessChange()) / Double.valueOf(l.getHomelessOG()) * 100.0)).equals("-Infinity")) {
                homeless = 0.000;
            } else {
                homeless = (Double.valueOf(l.getHomelessChange()) / Double.valueOf(l.getHomelessOG()) * 100.0);
            }  


            Double pop = 0.0;
            if (String.format("%.3f", (Double.valueOf(l.getPopChange()) / Double.valueOf(l.getPopOG()) * 100.0)).equals("NaN") ||
            String.format("%.3f", (Double.valueOf(l.getPopChange()) / Double.valueOf(l.getPopOG()) * 100.0)).equals("Infinity") || 
            String.format("%.3f", (Double.valueOf(l.getPopChange()) / Double.valueOf(l.getPopOG()) * 100.0)).equals("-Infinity")) {
                pop = 0.000;
            } else {
                pop = (Double.valueOf(l.getPopChange()) / Double.valueOf(l.getPopOG()) * 100.0);
            }  


            


            html = html + 
            "<tr>" +

            "<td>" + l.getName() + "</td>"  + 
            "<td>"  + l.getCode() + "</td>" + 

            "<td>"  + l.getAtRiskChange() + "</td>" +  
            "<td>"  + String.format("%.3f", atRisk) + "</td>" +        

            
            "<td>"  + l.getHomelessChange() + "</td>" +  
            "<td>"  + String.format("%.3f", homeless) + "</td>" +  

            
            "<td>"  + l.getPopChange() + "</td>" +  
            "<td>"  + String.format("%.3f", pop) + "</td>" +     
  

            "<td>"  + String.format("%.3f", (ratio)) + "</td>" + 

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
        
        html = html + "</div>"; // Closes divScroll

        return html;
    }






}