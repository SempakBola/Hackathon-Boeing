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
 */
public class PageST21 implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Local Government Area Information - Hope For Homes</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

        // Add some JS (external file)
        html = html + "<script type='text/JavaScript' src='filter.js'></script>";

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
                <h2>Local Government Area Information</h2>
            </div>

            <div class='breadcrumbs'>
                <i><a href='/' title='Home'>Homepage</a> / <a href='page3.html' title='LGA Information'>Local Government Area Information</a></i>
            </div>
        """;

//------------------------------------------------------------------------------------------------

        // Add Div for page Content
        html = html + "<div class='content'>";


        // Start the List HTML
        //html = html + "<ul>";

        html = html + "<h2>Choose a Local Government Area</h2>";
        html = html + "<h3>2018 Data Used</h3>";
    
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA22> codeLGA = jdbc.getAllLGACode();

        html = html + "<form action='/page3.html' method='post'>";
        html = html + "<fieldset><legend><b>PARAMETERS</b></legend>";

        html = html + "     <div class='form-group'>";
        html = html + "         <label for='lga_textbox' class='form-label'>Type the LGA Code/Name: </label>";
        
        html = html + "         <input class='form-control' list='lga_textbox' name='lga_textbox' placeholder='Search for LGA...' required>";
        
        html = html + "         <datalist id='lga_textbox'>";
        // Iterates their ArrayList to get values for the datalist
        for (LGA22 c : codeLGA) {
            html = html + "<option value='" + String.valueOf(c.getName()) + "'>" + c.getCode() + "</option>";
        }
        html = html + "         </datalist>";
        html = html + "     </div>";

       // html = html + "</form>";

        //Age
        html = html + "   <div class='form-group'>";
        html = html + "      <label for='movietype_drop'>Select the Age Range:</label>";
        html = html + "      <select id='movietype_drop' name='movietype_drop'>";
        html = html + "         <option value='' selected disabled hidden>Choose here</option>";
        html = html + "         <option value='_0_9'>0-9</option>";
        html = html + "         <option value='_10_19'>10-19</option>";
        html = html + "         <option value='_20_29'>20-29</option>";
        html = html + "         <option value='_30_39'>30-39</option>";
        html = html + "         <option value='_40_49'>40-49</option>";
        html = html + "         <option value='_50_59'>50-59</option>";
        html = html + "         <option value='_60_plus'>60+</option>";
        html = html + "      </select>";
        html = html + "</div>";


        //Gender
        html = html + "   <div class='form-group'>";
        html = html + "      <label for='gender_drop'>Select the Gender:</label>";
        html = html + "      <select id='gender_drop' name='gender_drop'>";
        html = html + "         <option value='' selected disabled hidden>Choose here</option>";
        html = html + "         <option value='m'>Male</option>";
        html = html + "         <option value='f'>Female</option>";
        html = html + "      </select>";
        html = html + "</div>";


        //Status
        html = html + "   <div class='form-group'>";
        html = html + "      <label for='status_drop'>Select the Status:</label>";
        html = html + "      <select id='status_drop' name='status_drop'>";
        html = html + "         <option value='' selected disabled hidden>Choose here</option>";
        html = html + "         <option value='at_risk'>At Risk</option>";
        html = html + "         <option value='homeless'>Homeless</option>";
        html = html + "      </select>";
        html = html + "</div>";

        //Population
        html = html + "   <div class='form-group'>";
        html = html + "      <label for='pop_drop'>Compare to Population:</label>";
        html = html + "      <select id='pop_drop' name='pop_drop'>";
        html = html + "         <option value='' selected disabled hidden>Leave Blank for No</option>";
        html = html + "         <option value='Y'>Yes</option>";
        html = html + "      </select>";
        html = html + "</div>";
        
        html = html + "   <button type='submit' class='btn btn-primary'>Search</button>";
        //html = html + "   </div>";
        html = html + "</fieldset>";
        html = html  + "</form>";

        /* Get the Form Data
         *  from the drop down list
         * Need to be Careful!!
         *  If the form is not filled in, then the form will return null!
        */
        String lga_textbox = context.formParam("lga_textbox");
        String movietype_drop = context.formParam("movietype_drop");
        String gender_drop = context.formParam("gender_drop");
        String status_drop = context.formParam("status_drop");
        String pop_drop = context.formParam("pop_drop");

        if ((lga_textbox != null) & 
        (movietype_drop == null) & 
        (gender_drop == null) & 
        (status_drop == null) &
        (pop_drop == null)) {
            html = html + singleLga(lga_textbox);
        }
        else if ((lga_textbox != null) & 
        (movietype_drop != null) & 
        (gender_drop == null) & 
        (status_drop == null) &
        (pop_drop == null)) {
            html = html + LgaWithAge(lga_textbox, movietype_drop);
        }
        else if ((lga_textbox != null) & 
        (movietype_drop == null) & 
        (gender_drop != null) & 
        (status_drop == null) &
        (pop_drop == null)) {
            html = html + LgaWithSex(lga_textbox, gender_drop);
        }
        else if ((lga_textbox != null) & 
        (movietype_drop == null) & 
        (gender_drop == null) & 
        (status_drop != null) &
        (pop_drop == null)) {
            html = html + LgaWithStatus(lga_textbox, status_drop);
        }
        else if ((lga_textbox != null) & 
        (movietype_drop != null) & 
        (gender_drop != null) & 
        (status_drop == null) &
        (pop_drop == null)) {
            html = html + LgaWithAgeAndSex(lga_textbox, movietype_drop, gender_drop);
        }
        else if ((lga_textbox != null) & 
        (movietype_drop != null) & 
        (gender_drop == null) & 
        (status_drop != null) &
        (pop_drop == null)) {
            html = html + LgaWithAgeAndStatus(lga_textbox, movietype_drop, status_drop);
        }
        else if ((lga_textbox != null) & 
        (movietype_drop == null) & 
        (gender_drop != null) & 
        (status_drop != null) &
        (pop_drop == null)) {
            html = html + LgaWithSexAndStatus(lga_textbox, gender_drop, status_drop);
        }
        else if ((lga_textbox != null) & 
        (movietype_drop == null) & 
        (gender_drop == null) & 
        (status_drop == null) &
        (pop_drop != null)) {
            html = html + LgaWithPop(lga_textbox, pop_drop);
        }
        else if ((lga_textbox != null) & 
        (movietype_drop != null) & 
        (gender_drop == null) & 
        (status_drop == null) &
        (pop_drop != null)) {
            html = html + LgaWithPopAndAge(lga_textbox, pop_drop, movietype_drop);
        }
        else if ((lga_textbox != null) & 
        (movietype_drop == null) & 
        (gender_drop != null) & 
        (status_drop == null) &
        (pop_drop != null)) {
            html = html + LgaWithPopAndSex(lga_textbox, pop_drop, gender_drop);
        }
        else if ((lga_textbox != null) & 
        (movietype_drop == null) & 
        (gender_drop == null) & 
        (status_drop != null) &
        (pop_drop != null)) {
            html = html + LgaWithPopAndStatus(lga_textbox, pop_drop, status_drop);
        }
        else if ((lga_textbox != null) & 
        (movietype_drop != null) & 
        (gender_drop != null) & 
        (status_drop == null) &
        (pop_drop != null)) {
            html = html + LgaWithPopAgeSex(lga_textbox, pop_drop, movietype_drop, gender_drop);
        }
        else if ((lga_textbox != null) & 
        (movietype_drop != null) & 
        (gender_drop == null) & 
        (status_drop != null) &
        (pop_drop != null)) {
            html = html + LgaWithPopAgeStatus(lga_textbox, pop_drop, movietype_drop, status_drop);
        }
        else if ((lga_textbox != null) & 
        (movietype_drop == null) & 
        (gender_drop != null) & 
        (status_drop != null) &
        (pop_drop != null)) {
            html = html + LgaWithPopSexStatus(lga_textbox, pop_drop, gender_drop, status_drop);
        }
        else if ( (pop_drop != null)){
            html = html + outputMoviesAndPop(lga_textbox, movietype_drop, gender_drop, status_drop, pop_drop);
        }
        else if ( (lga_textbox == null) & 
        (movietype_drop == null) & 
        (gender_drop == null) & 
        (status_drop == null) &
        (pop_drop == null)){
            html = html + "<h2><i>No Results to show - please select from above</i></h2>";
        }
        else {
            // If NOT NULL, then lookup the l by type!
            html = html + outputMovies(lga_textbox, movietype_drop, gender_drop, status_drop);
        }
    

       

        // Finish the List HTML
        //html = html + "</ul>";

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

    public String singleLga(String lga_name) {
    
        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA> lga = jdbc.getSingleLGA(lga_name);

        String html = "";
        html = html + "<h2>Showing: " + lga_name;
        
        html = html + """
            <div class="divScroll">

            <table class="center" style="width:75%">

            <tr>
            <th>LGA Name</th>
            <th>Age Group</th>
            <th>Status</th>
            <th>Sex</th>
            <th>Number</th>
            </tr>

                """;

        for (LGA l : lga) {
            html = html + 
            "<tr>" +  
            "<td>" + l.getName16() + "</td>"  + 
            "<td>"  + l.getAge() + "</td>" + 
            "<td>"  + l.getName10() + "</td>" + 
            "<td>"  + l.getName2() + "</td>" + 
            "<td>"  + l.getCode2() + "</td>" +  
            "</tr>";
        }

        html = html +   "</table>" + "  </div>";
        // +        "</ul>"

        return html;
    }

    public String LgaWithAge(String lga_name, String ageGroup) {
    
        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA> lga = jdbc.getLgaWithAge(lga_name, ageGroup);

        String html = "";
        html = html + "<h2>Showing: " + lga_name + " with Age Group: " + ageGroup;
        
        html = html + """
            <div class="divScroll">

            <table class="center" style="width:75%">

            <tr>
            <th>LGA Name</th>
            <th>Status</th>
            <th>Sex</th>
            <th>Number</th>
            </tr>

                """;

        for (LGA l : lga) {
            html = html + 
            "<tr>" +  
            "<td>" + l.getName16() + "</td>"  + 
            "<td>"  + l.getName10() + "</td>" + 
            "<td>"  + l.getName2() + "</td>" + 
            "<td>"  + l.getCode2() + "</td>" +  
            "</tr>";
        }

        html = html +   "</table>" + "  </div>";
        // +        "</ul>"

        return html;
    }

    public String LgaWithSex(String lga_name, String sex) {
    
        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA> lga = jdbc.getLgaWithSex(lga_name, sex);

        if (sex == "f") {
            sex = "'Female'";
        }
        else {
            sex = "'Male'";
        }

        String html = "";
        html = html + "<h2>Showing: " + lga_name + " with Gender : " + sex;
        
        html = html + """
            <div class="divScroll">

            <table class="center" style="width:75%">

            <tr>
            <th>LGA Name</th>
            <th>Age Group</th>
            <th>Status</th>
            <th>Sex</th>
            <th>Number</th>
            </tr>

                """;

        for (LGA l : lga) {
            html = html + 
            "<tr>" +  
            "<td>" + l.getName16() + "</td>"  + 
            "<td>"  + l.getName10() + "</td>" +
            "<td>"  + l.getAge() + "</td>" +  
            "<td>"  + l.getName2() + "</td>" + 
            "<td>"  + l.getCode2() + "</td>" +  
            "</tr>";
        }

        html = html +   "</table>" + "  </div>";
        // +        "</ul>"

        return html;
    }

    public String LgaWithStatus(String lga_name, String status) {
    
        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA> lga = jdbc.getLgaWithStatus(lga_name, status);

        if (status == "homeless") {
            status = "'Homeless'";
        }
        else {
            status = "'At risk'";
        }

        String html = "";
        html = html + "<h2>Showing: " + lga_name + " with Status : " + status;
        
        html = html + """
            <div class="divScroll">

            <table class="center" style="width:75%">

            <tr>
            <th>LGA Name</th>
            <th>Status</th>
            <th>Age Group</th>
            <th>Sex</th>
            <th>Number</th>
            </tr>

                """;

        for (LGA l : lga) {
            html = html + 
            "<tr>" +  
            "<td>" + l.getName16() + "</td>"  + 
            "<td>"  + l.getName10() + "</td>" + 
            "<td>"  + l.getAge() + "</td>" + 
            "<td>"  + l.getName2() + "</td>" + 
            "<td>"  + l.getCode2() + "</td>" +  
            "</tr>";
        }

        html = html +   "</table>" + "  </div>";
        // +        "</ul>"

        return html;
    }

    public String LgaWithPop(String lga_name, String pop) {
    
        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA> lga = jdbc.getLgaWithPop(lga_name, pop);

        String html = "";
        html = html + "<h2>Showing: " + lga_name + " with Population";
        
        html = html + """
            <div class="divScroll">

            <table class="center" style="width:75%">

            <tr>
            <th>LGA Name</th>
            <th>Age Group</th>
            <th>Status</th>
            <th>Sex</th>
            <th>Number</th>
            <th>Population</th>
            </tr>

                """;

        for (LGA l : lga) {
            html = html + 
            "<tr>" +  
            "<td>" + l.getName16() + "</td>"  + 
            "<td>" + l.getAge() + "</td>"  + 
            "<td>"  + l.getName10() + "</td>" + 
            "<td>"  + l.getName2() + "</td>" + 
            "<td>"  + l.getCode2() + "</td>" +  
            "<td>"  + l.getPop() + "</td>" + 
            "</tr>";
        }

        html = html +   "</table>" + "  </div>";
        // +        "</ul>"

        return html;
    }

    public String LgaWithPopAndAge(String lga_name, String pop, String ageGroup) {
    
        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA> lga = jdbc.getLgaWithPopAndAge(lga_name, pop, ageGroup);

        String html = "";
        html = html + "<h2>Showing: " + lga_name + " with Age Group : " + ageGroup + " and Population";
        
        html = html + """
            <div class="divScroll">

            <table class="center" style="width:75%">

            <tr>
            <th>LGA Name</th>
            <th>Status</th>
            <th>Sex</th>
            <th>Number</th>
            <th>Population</th>
            </tr>

                """;

        for (LGA l : lga) {
            html = html + 
            "<tr>" +  
            "<td>" + l.getName16() + "</td>"  + 
            "<td>"  + l.getName10() + "</td>" + 
            "<td>"  + l.getName2() + "</td>" + 
            "<td>"  + l.getCode2() + "</td>" +  
            "<td>"  + l.getPop() + "</td>" + 
            "</tr>";
        }

        html = html +   "</table>" + "  </div>";
        // +        "</ul>"

        return html;
    }

    public String LgaWithPopAndSex(String lga_name, String pop, String sex) {
    
        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA> lga = jdbc.getLgaWithPopAndSex(lga_name, pop, sex);

        if (sex == "f") {
            sex = "'Female'";
        }
        else {
            sex = "'Male'";
        }

        String html = "";
        html = html + "<h2>Showing: " + lga_name + " with Gender : " + sex + " and Population";
        
        html = html + """
            <div class="divScroll">

            <table class="center" style="width:75%">

            <tr>
            <th>LGA Name</th>
            <th>Age Group</th>
            <th>Status</th>
            <th>Sex</th>
            <th>Number</th>
            <th>Population</th>
            </tr>

                """;

        for (LGA l : lga) {
            html = html + 
            "<tr>" +  
            "<td>" + l.getName16() + "</td>"  + 
            "<td>" + l.getAge() + "</td>"  + 
            "<td>"  + l.getName10() + "</td>" + 
            "<td>"  + l.getName2() + "</td>" + 
            "<td>"  + l.getCode2() + "</td>" +  
            "<td>"  + l.getPop() + "</td>" + 
            "</tr>";
        }

        html = html +   "</table>" + "  </div>";
        // +        "</ul>"

        return html;
    }

    public String LgaWithPopAndStatus(String lga_name, String pop, String status) {
    
        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA> lga = jdbc.getLgaWithPopAndStatus(lga_name, pop, status);

        if (status == "homeless") {
            status = "'Homeless'";
        }
        else {
            status = "'At risk'";
        }

        String html = "";
        html = html + "<h2>Showing: " + lga_name + " with Gender : " + status + " and Population";
        
        html = html + """
            <div class="divScroll">

            <table class="center" style="width:75%">

            <tr>
            <th>LGA Name</th>
            <th>Age Group</th>
            <th>Status</th>
            <th>Sex</th>
            <th>Number</th>
            <th>Population</th>
            </tr>

                """;

        for (LGA l : lga) {
            html = html + 
            "<tr>" +  
            "<td>" + l.getName16() + "</td>"  + 
            "<td>" + l.getAge() + "</td>"  + 
            "<td>"  + l.getName10() + "</td>" + 
            "<td>"  + l.getName2() + "</td>" + 
            "<td>"  + l.getCode2() + "</td>" +  
            "<td>"  + l.getPop() + "</td>" + 
            "</tr>";
        }

        html = html +   "</table>" + "  </div>";
        // +        "</ul>"

        return html;
    }

    public String LgaWithPopAgeSex(String lga_name, String pop, String ageGroup, String sex) {
    
        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA> lga = jdbc.getLgaWithPopAgeSex(lga_name, pop, ageGroup, sex);

        String html = "";
        html = html + "<h2>Showing: " + lga_name + " with Age Group: " + ageGroup + ", Sex: " + sex + " and Population";
        
        html = html + """
            <div class="divScroll">

            <table class="center" style="width:75%">

            <tr>
            <th>LGA Name</th>
            <th>Status</th>
            <th>Sex</th>
            <th>Number</th>
            <th>Population</th>
            </tr>

                """;

        for (LGA l : lga) {
            html = html + 
            "<tr>" +  
            "<td>" + l.getName16() + "</td>"  + 
            "<td>"  + l.getName10() + "</td>" + 
            "<td>"  + l.getName2() + "</td>" + 
            "<td>"  + l.getCode2() + "</td>" +  
            "<td>"  + l.getPop() + "</td>" + 
            "</tr>";
        }

        html = html +   "</table>" + "  </div>";
        // +        "</ul>"

        return html;
    }

    public String LgaWithPopAgeStatus(String lga_name, String pop, String ageGroup, String status) {
    
        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA> lga = jdbc.getLgaWithPopAgeStatus(lga_name, pop, ageGroup, status);

        String html = "";
        html = html + "<h2>Showing: " + lga_name + " with Age Group: " + ageGroup + ", Sex: " + status + " and Population";
        
        html = html + """
            <div class="divScroll">

            <table class="center" style="width:75%">

            <tr>
            <th>LGA Name</th>
            <th>Status</th>
            <th>Sex</th>
            <th>Number</th>
            <th>Population</th>
            </tr>

                """;

        for (LGA l : lga) {
            html = html + 
            "<tr>" +  
            "<td>" + l.getName16() + "</td>"  + 
            "<td>"  + l.getName10() + "</td>" + 
            "<td>"  + l.getName2() + "</td>" + 
            "<td>"  + l.getCode2() + "</td>" +  
            "<td>"  + l.getPop() + "</td>" + 
            "</tr>";
        }

        html = html +   "</table>" + "  </div>";
        // +        "</ul>"

        return html;
    }

    public String LgaWithPopSexStatus(String lga_name, String pop, String sex, String status) {
    
        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA> lga = jdbc.getLgaWithPopSexStatus(lga_name, pop, sex, status);

        if (status == "homeless") {
            status = "'Homeless'";
        }
        else {
            status = "'At risk'";
        }

        String html = "";
        html = html + "<h2>Showing: " + lga_name + " with Gender : " + status + " and Population";
        
        html = html + """
            <div class="divScroll">

            <table class="center" style="width:75%">

            <tr>
            <th>LGA Name</th>
            <th>Age Group</th>
            <th>Status</th>
            <th>Sex</th>
            <th>Number</th>
            <th>Population</th>
            </tr>

                """;

        for (LGA l : lga) {
            html = html + 
            "<tr>" +  
            "<td>" + l.getName16() + "</td>"  + 
            "<td>" + l.getAge() + "</td>"  + 
            "<td>"  + l.getName10() + "</td>" + 
            "<td>"  + l.getName2() + "</td>" + 
            "<td>"  + l.getCode2() + "</td>" +  
            "<td>"  + l.getPop() + "</td>" + 
            "</tr>";
        }

        html = html +   "</table>" + "  </div>";
        // +        "</ul>"

        return html;
    }

    public String LgaWithAgeAndSex(String lga_name, String ageGroup, String sex) {
    
        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA> lga = jdbc.getLgaWithAgeAndSex(lga_name, ageGroup, sex);

        if (sex == "f") {
            sex = "'Female'";
        }
        else {
            sex = "'Male'";
        }

        String html = "";
        html = html + "<h2>Showing: " + lga_name + " with Age Group: " + ageGroup + " and Gender: " + sex; 
        
        html = html + """
            <div class="divScroll">

            <table class="center" style="width:75%">

            <tr>
            <th>LGA Name</th>
            <th>Status</th>
            <th>Sex</th>
            <th>Number</th>
            </tr>

                """;

        for (LGA l : lga) {
            html = html + 
            "<tr>" +  
            "<td>" + l.getName16() + "</td>"  + 
            "<td>"  + l.getName10() + "</td>" + 
            "<td>"  + l.getName2() + "</td>" + 
            "<td>"  + l.getCode2() + "</td>" +  
            "</tr>";
        }

        html = html +   "</table>" + "  </div>";
        // +        "</ul>"

        return html;
    }

    public String LgaWithAgeAndStatus(String lga_name, String ageGroup, String status) {
    
        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA> lga = jdbc.getLgaWithAgeAndStatus(lga_name, ageGroup, status);

        if (status == "homeless") {
            status = "'Homeless'";
        }
        else {
            status = "'At risk'";
        }

        String html = "";
        html = html + "<h2>Showing: " + lga_name + " with Age Group: " + ageGroup + " and Gender: " + status; 
        
        html = html + """
            <div class="divScroll">

            <table class="center" style="width:75%">

            <tr>
            <th>LGA Name</th>
            <th>Status</th>
            <th>Sex</th>
            <th>Number</th>
            </tr>

                """;

        for (LGA l : lga) {
            html = html + 
            "<tr>" +  
            "<td>" + l.getName16() + "</td>"  + 
            "<td>"  + l.getName10() + "</td>" + 
            "<td>"  + l.getName2() + "</td>" + 
            "<td>"  + l.getCode2() + "</td>" +  
            "</tr>";
        }

        html = html +   "</table>" + "  </div>";
        // +        "</ul>"

        return html;
    }

    public String LgaWithSexAndStatus(String lga_name, String sex, String status) {
    
        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA> lga = jdbc.getLgaWithSexAndStatus(lga_name, sex, status);

        if (sex == "f") {
            sex = "'Female'";
        }
        else {
            sex = "'Male'";
        }
        if (status == "homeless") {
            status = "'Homeless'";
        }
        else {
            status = "'At risk'";
        }

        String html = "";
        html = html + "<h2>Showing: " + lga_name + " with Gender: " + sex + " and Status: " + status; 
        
        html = html + """
            <div class="divScroll">

            <table class="center" style="width:75%">

            <tr>
            <th>LGA Name</th>
            <th>Status</th>
            <th>Age Group</th>
            <th>Sex</th>
            <th>Number</th>
            </tr>

                """;

        for (LGA l : lga) {
            html = html + 
            "<tr>" +  
            "<td>" + l.getName16() + "</td>"  + 
            "<td>"  + l.getName10() + "</td>" +
            "<td>" + l.getAge() + "</td>"  +  
            "<td>"  + l.getName2() + "</td>" + 
            "<td>"  + l.getCode2() + "</td>" + 
            "</tr>";
        }

        html = html +   "</table>" + "  </div>";
        // +        "</ul>"

        return html;
    }


    public String outputMovies(String lga_name, String ageGroup, String sex, String status) {
    
        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA> lga = jdbc.getHomelessGroup18ByAge(lga_name, ageGroup, sex, status);

        if (sex == "f") {
            sex = "'Female'";
        }
        else {
            sex = "'Male'";
        }
        if (status == "homeless") {
            status = "'Homeless'";
        }
        else {
            status = "'At risk'";
        }

        String html = "";
        html = html + "<h2>Showing: " + lga_name + " with Age Group " + ageGroup + ", Gender " + sex + " and Status " + status + "</h2>";
        
        html = html + """
            <div class="divScroll">

            <table class="center" style="width:75%">

            <tr>
            <th>LGA Name</th>
            <th>Status</th>
            <th>Sex</th>
            <th>Number</th>
            </tr>

                """;

        for (LGA l : lga) {
            html = html + 
            "<tr>" +  
            "<td>" + l.getName16() + "</td>"  + 
            "<td>"  + l.getName10() + "</td>" + 
            "<td>"  + l.getName2() + "</td>" + 
            "<td>"  + l.getCode2() + "</td>" +  
            "</tr>";
        }

        html = html +   "</table>" + "  </div>";
        // +        "</ul>"

        return html;
    }

    //POPULATION

    public String outputMoviesAndPop(String lga_name, String ageGroup, String sex, String status, String pop) {
    
        // Look up lga from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA> lga = jdbc.getHomelessGroup18ByAgeAndPop(lga_name, ageGroup, sex, status, pop);

        if (status == "homeless") {
            status = "'Homeless'";
        }
        else {
            status = "'At risk'";
        }

        if (sex == "f") {
            sex = "'Female'";
        }
        else {
            sex = "'Male'";
        }

        String html = "";
        html = html + "<h2>Showing: " + lga_name + " with Age Group: " + ageGroup + ", Gender: " + sex + " and Status: " + status + "</h2>";
        
        html = html + """
            <div class="divScroll">

            <table class="center" style="width:75%">

            <tr>
            <th>LGA Name</th>
            <th>Status</th>
            <th>Sex</th>
            <th>Number</th>
            <th>Population</th>
            </tr>

                """;

        for (LGA l : lga) {
            html = html + 
            "<tr>" +  
            "<td>" + l.getName16() + "</td>"  + 
            "<td>"  + l.getName10() + "</td>" + 
            "<td>"  + l.getName2() + "</td>" + 
            "<td>"  + l.getCode2() + "</td>" + 
            "<td>"  + l.getPop() + "</td>" + 
            "</tr>";
        }

        html = html +   "</table>" + "  </div>";
        // +        "</ul>"

        return html;
    }
    
}
