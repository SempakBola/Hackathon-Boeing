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
public class PageMission implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/mission.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Our Mission - Hope For Homes</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
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
                                    <a href='page5.html' title='3.1'>Sub Task 3.1</a>
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
                <a href='page5.html' title='3.1'>Sub Task 3.1</a>
                <a href='page6.html' title='Changes In Homelessness'>Changes In Homelessness Over Time</a>


                <div id="google_translate_element"></div>

                <script type="text/javascript">
                function googleTranslateElementInit() {
                new google.translate.TranslateElement({pageLanguage: 'en', layout: google.translate.TranslateElement.InlineLayout.SIMPLE}, 'google_translate_element');
                }
                </script>

                <script type="text/javascript" src='//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit'></script>
            
                </div>
        """;

//------------------------------------------------------------------------------------------------

        // Add header content block
         html = html + """


        <!-- div class="bg-img" style='background-image: url("1.jpg");' -->
        <!-- div class="container" -->
            <div class='header2'> 
                <h2>Mission Statement</h2>
            </div>
        <!-- /div -->
        <!-- /div -->
        
           
            <div class='breadcrumbs'>
                <i><a href='/' title='Home'>Homepage</a> / <a href='mission.html' title='Mission'>Our Mission</a></i>
            </div>

       
        """;


//------------------------------------------------------------------------------------------------

        // Add Div for page Content
        html = html + "<div class='content'>";
        

        html = html + """
            <div class='row'>
                <div class='column-33' style='padding-right:10px'>
                    <h1 style='font-size:40px'>How It All Started - Our Advocacy</h1>
                </div>

                <div class='column-66' style='padding-left:10px; text-align: right;'>
                    <p>
                    In order to tackle the social challenge of homelessness in Australia, we intended to develop a website
                    which raises awareness and understanding of this issue in an informative and descriptive manner. <br><br>
                    
                    This website aims to target a wide audience, ranging from those completely unaware about this problem, to
                    those wanting to find out what more they can do to stay updated and possibly help out. Potential users
                    should be able to complete a variety of tasks that satisfy their goals and needs. <br><br>
                    
                    For example, users can search up statistics on the Australian LGAs. <br><br>
                    
                    The rising concern of homelessness was chosen due to the impact the Coronavirus pandemic has had on the economy and wellbeing of humanity. More specifically, there were
                    financial struggles as many could not work at home because of fears of inflection. Although Australia is
                    now slowly returning back to normal, there are those who are in need of our assistance and support.
                    </p>

                    <br><br>
                </div> 
            </div>


            <div class='row'>

                <div class='column2' style='padding-left:10px'>
                    <p>
                        Thank you for visiting our website that provides strictly statistical data about homelessness within Australia. <br><br>

                        We are both studemts from RMIT University in Australia, and are striving to provide the best data within Australian LGAs.
                    </p>
                </div> 
                
                <div class='column2' style='padding-right:10px; text-align: right;'>
                    <h1 style='font-size:40px'>Meet Our Team Members</h1>

                    <br><br>

                </div>
            </div>



            <div class='row'>
                <div class='column-33' style='padding-right:10px'>
                    <h1 style='font-size:40px'>Meet People Like - Our Audience</h1><br>
                    <img src='1.png' class='top-image' alt='logo' style='height:100;'>
                    <img src='2.png' class='top-image' alt='logo' style='height:100;'>
                </div>

                <div class='column-66' style='padding-left:10px; text-align: right;'>
                    <p>
                    Meet John Cruz and Misty Brunhild, who are both interesting in helping the homeless. <br><br>
                    
                    John is an international student who pursues nursing and wants to assist in social volunteering. <br><br>
                    
                    Misty wants to use her past struggles with homelessness and assist in future research to provide ways to aid the 
                    homeless. As well as educate her students and children, as she's a Computer Science Teacher and Mother.
                    </p>
                </div> 
            </div>


        """;

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

}
