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
public class Backup_Page_Template implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page5.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        html = html + """
            <div class='header'>
                <h1>
                    <a href='/'>
                    <img src='logo.png' class='top-image' alt='logo' style='height:75;'><i> HOPE FOR HOMES</i></a>
                    </h1>
            </div>
        """;

        // Add some Head information
        html = html + "<head>" + 
               "<title>Subtask 3.1</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <div class='topnav'>
                <a href='/'>Homepage</a>
                <a href='mission.html'>Our Mission</a>
                <a href='page3.html'>Local Government Area Information</a>
                <a href='page4.html'>Focused View On A LGA</a>
                <a href='page5.html'>Sub Task 3.1</a>
                <a href='page6.html'>Changes In Homelessness Over Time</a>
            </div>
        """;

        // Add header content block
        html = html + """
            <div class='header'>
                <h1>Subtask 3.1</h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Look up some information from JDBC
        // First we need to use your JDBCConnection class
        JDBCConnection jdbc = new JDBCConnection();

        // Next we will ask this *class* for the LGAs
        ArrayList<LGA> lgas = jdbc.getLGAs();

        // Add HTML for the LGA list
        html = html + "<h1>All LGAs</h1>" + "<ul>";

        // Finally we can print out all of the LGAs
        for (LGA lga : lgas) {
            html = html + "<li>" + lga.getName16()
            + " - " + lga.getName10() + " - " + lga.getName2() + " - " + lga.getCode2() + "</li>";
        }

        // Finish the List HTML
        html = html + "</ul>";

        // Close Content div
        html = html + "</div>";


         // Footer
        html = html + """
            <div class='footer'>
    
                <div class='footerlink'>
                    <a href='/'>Homepage</a>&emsp;|&emsp;
                    <a href='mission.html'>Our Mission</a>&emsp;|&emsp;
                    <a href='page3.html'>Local Government Area Information</a>&emsp;|&emsp;
                    <a href='page4.html'>Focused View On A LGA</a>&emsp;|&emsp;
                    <a href='page5.html'>Sub Task 3.1</a>&emsp;|&emsp;
                    <a href='page6.html'>Changes In Homelessness Over Time</a>
                </div>

                <div class='footerlink2'>
                    <div class='row2'>
                    <div class='column2'>
                        <h2>
                            <a href='/'><img src='logo_footer.png' class='top-image' alt='logo' style=height:25;'>
                                <i><b> HOPE FOR HOMES</i></b>
                            </a>
                        </h2>
                    </div>

                    <div class='column2' style='text-align:right;'>
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
