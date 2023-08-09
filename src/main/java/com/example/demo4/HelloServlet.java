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

//package com.example.demo4;
//
//import java.io.*;
//import java.util.Properties;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//
//@WebServlet(name = "helloServlet", value = "/hello-servlet")
//public class HelloServlet extends HttpServlet {
//    private String message;
//
//    public void init() {
//        // Fetch properties from the local URL during initialization
//        message = fetchPropertiesFromLocalURL("/config.properties");
//    }
//
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html");
//
//        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + message + "</h1>");
//        out.println("</body></html>");
//    }
//
//    public void destroy() {
//    }
//
//    private String fetchPropertiesFromLocalURL(String url) {
//        try {
//            InputStream inputStream = getClass().getResourceAsStream(url);
//            Properties properties = new Properties();
//            properties.load(inputStream);
//            inputStream.close();
//
//            // Get the value of the 'message' property from the fetched properties
//            return properties.getProperty("username");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error occurred: " + e.getMessage();
//        }
//    }
//}
