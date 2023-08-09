package com.example.demo4;

import java.io.*;
import java.util.Properties;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private Properties properties;

    public void init() {
        // Fetch properties from the local URL during initialization
        properties = fetchPropertiesFromLocalURL("/config.properties");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Access properties and generate HTML response
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Properties:</h1>");

        // Loop through properties and print key-value pairs
        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            out.println("<p><strong>" + key + ":</strong> " + value + "</p>");
        }

        out.println("</body></html>");
    }

    public void destroy() {
    }

    private Properties fetchPropertiesFromLocalURL(String url) {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getResourceAsStream(url)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("File not found: " + url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
