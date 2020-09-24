package tech.costa.luiz.servlet;

import static java.util.Objects.nonNull;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;

/**
 * Servlet implementation class ExampleHttp2
 */
@WebServlet("/ExampleHttp2")
public class ExampleHttp2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExampleHttp2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int httpVersion = 1;
		PushBuilder pushBuilder = request.newPushBuilder();
		if (nonNull(pushBuilder)) {
			httpVersion = 2;
			pushBuilder.path("resources/js/example.js").push();
			pushBuilder.path("resources/css/example.css").push();
		}
		request.setAttribute("httpVersion", httpVersion);
		request.getRequestDispatcher("/example.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
