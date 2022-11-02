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
public class PageMission implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/homepage.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Homepage</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <div class='topnav'>
                <a href='/homepage.html'>Homepage</a>
                <a href='/findparts.html'>Find Parts</a>
                <a href='/partsinfo.html'>Parts Infomation</a>
                <a href='/upload.html'>Upload Parts File</a>
                <a href='/'>Log Out</a>
            </div>
        """;

        // Add header content block
        html = html + """
            <div class='header'>
                <h1>
                Beoing Parts Stack Resolution
                </h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";
        html = html + """
            <p>add text here</p>   
            """;



        // Close Content div
        html = html + "</div>";

        // Footer
        html = html + """
            <div class='footer'>
                <p>Group 50</p>
                <p>Haoxiang Li - s3953757</p>
            </div>
        """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
