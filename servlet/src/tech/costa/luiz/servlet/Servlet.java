package tech.costa.luiz.servlet;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;
import javax.ws.rs.core.MediaType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

    private static final long serialVersionUID = -3439982021784932020L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType(MediaType.TEXT_HTML_TYPE.withCharset(UTF_8.name()).toString());
        response.setStatus(200);

        PushBuilder builder = request.newPushBuilder();

        // If server push isn't supported, return that in the result.
        if (builder == null) {
            response.addHeader("protocol", "HTTP 1.1");
            response.getWriter().append("<p>The image below was sent normally using HTTP 1.1.</p>");
        } else {
            response.addHeader("protocol", "HTTP/2");
            response.getWriter().append("<p>The image below was pushed using HTTP/2.</p>");
            builder.path("resources/images/payara-logo.jpg").push();
            builder.path("resources/images/Lugocast.png").push();
        }
        response.getWriter().append("<img src=\"resources/images/payara-logo.jpg\" />");
        response.sendRedirect("test.jsp");
    }

}
