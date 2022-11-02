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
public class PageIndex implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Hope for Homes</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

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
                                    <a href='page5.html' title='3.1'>Sub Task 3.1</a>
                                    <a href='page6.html' title='Changes In Homelessness'>Changes In Homelessness Over Time</a>
                                </div>
                        </div>

                    </div>
                </div>
            </div>            
        """;

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <div class='topnav'>
                <a href='/' title='Home'>Homepage</a>
                <a href='mission.html' title='Mission'>Our Mission</a>
                <a href='page3.html' title='LGA Information'>Local Government Area Information</a>
                <a href='page4.html' title='Focused LGA'>Focused View On A LGA</a>
                <a href='page5.html' title='3.1'>Sub Task 3.1</a>
                <a href='page6.html' title='Changes In Homelessness'>Changes In Homelessness Over Time</a>
                <!-- input type="text" placeholder="Search.." -->
            </div>
        """;

        html = html + "<div class='content'>";

        html = html + """
                <div class='HPtitle'>
                <h2>What is Homelessness</h2>
                <p>The experience of homelessness can be defined in many different ways and stretches across many cohorts of people,
                 including people living in crisis accommodation, rough sleeping or those in temporary housing. 
                 Being homeless can mean more than not having a roof over your head and is not just one typical experience. Every Australian deserves to have a home.</p>
                 </div>
                """;
        html = html + "</div>";

        // Add Div for page Content - style='height:60%' before to make it full screen
        html = html + "<div class='content'>";
        
        html = html + """
        <form method="post" enctype="multipart/form-data">
        <div>
          <label for="file">Choose file to upload</label>
          <input type="file" id="file" name="file" multiple />
        </div>
        <div>
          <button>Submit</button>
        </div>
      </form>
      """;

        // Add HTML for the list of pages (as well as topnav)
        html = html + """
            <div class='row'>
            <div class='column1'>
            <div class='box effect8'>
            <h2> Fact 1 </h2>
            <p>Unexpectedly the LGA with the lowest median household weekly income in 2018, 
            <b>Peterborough</b>, only has reports of 4 people being homeless and at risk of homelessness respectively.</p></div></div> 
            <div class='column1'>
            <div class='box effect8'>
            <h2>Fact 2</h2
            <p><b>98,850</b> and <b>151, 273</b> of Australians were homeless and at risk of homelessness respectively in 2016. 
            Which changed to 99,528 and 150,087 in 2018.</p></div></div> 
            <div class='column1'>
            <div class='box effect8'>
            <h2>Fact 3</h2>
            <p>With children from ages 0 to 9 years, <b>16,827</b> and <b>26,727</b> in 2016,
            and <b>16,635</b> and <b>26,794</b> in 2018, faced homelessness and were at risk of homelessness respectively.
            </p> </div>  </div>  
            </div>       
            """;

        // Close Content div
        html = html + "</div>";

        html = html + "<div class='content'>";

        // Add HTML for the list of pages (as well as topnav)
        html = html + """
            
            <div class='row'>
            <div class='column'>
            <div class='box effect8'>
            <h3>Total Number of Local Government Areas</h3>
            <h4>536</h4></div>
            </div>
            <div class='row'>
            <div class='column'>
            
            <h2><img src='AdobeStock_81776411.jpeg' class='centre' alt='logo' style='height:200;'> </h2
            <p></p></div>                
            
            <div class='row'>
            <div class='column'>
            <div class='box effect8'>
            <h3>Total Number of Homeless People in 2018</h3>
            <h4>536</h4></div>   
            </div>
            """;

        // Close Content div
        html = html + "</div>";

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
        
        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
