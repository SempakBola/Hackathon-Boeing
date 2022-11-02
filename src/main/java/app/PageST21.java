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
    public static final String URL = "/findparts.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Find Parts</title>";

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
                    <a href = 'homepage.html'>Beoing Parts Stack Resolution</a>
                </h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";
        html = html + "<h3>add text here<h3>";
    
        html = html + "<div class = 'form selection'>";
        html = html + """
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
            <form action = 'partsinfo.html' method = 'post'>
                <input list = "File Name" name = "File Name" placeholder = "File Name">
                <input list = "File Path" name = "File Path" placeholder = "File Path">
                <input list = "Load Number" name = "Load Number" placeholder = "Load Number">
                <input list = "Equipment" name = "Equipment" placeholder = "Equipment">
                <input list = "Run Recipe" name = "Run Recipe" placeholder = "Run Recipe">
                <input list = "Run Start" name = "Run Start" placeholder = "Run Start">
                <input list = "Run End" name = "Run End" placeholder = "Run End">
                <input list = "Run Duration" name = "Run Duration" placeholder = "Run Duration">
                <input list = "File Length" name = "File Length" placeholder = "File Length">
                <input list = "Operator Name" name = "Operator Name" placeholder = "Operator Name">
                <input list = "Export Control" name = "Export Control" placeholder = "Export Control">
                <input list = "IP" name = "IP" placeholder = "IP">

                <input list = "Work Order" name = "Work Order" placeholder = "Work Order">
                <input list = "Part Number" name = "Part Number" placeholder = "Part Number">
                <input list = "Part Description" name = "Part Description" placeholder = "Part Description">
                <input list = "Tool Location" name = "Tool Location" placeholder = "Tool Location">
                <input list = "Comment1" name = "Comment1" placeholder = "Comment1">
                <input list = "Comment2" name = "Comment2" placeholder = "Comment2">
                <input list = "Comment3" name = "Comment3" placeholder = "Comment3">
                <input list = "Part TCs" name = "Part TCs" placeholder = "Part TCs">
                <input list = "Part Probes" name = "Part Probes" placeholder = "Part Probes">
                <input list = "Other Sensors" name = "Other Sensors" placeholder = "Other Sensors">
                <button type="submit">Search</button>
            </form>
        """;
        html = html + "</div>";
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
