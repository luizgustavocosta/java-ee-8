package tech.costa.luiz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.IntStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/HelloWorld")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PushBuilder pushBuilder = request.newPushBuilder();
		if (pushBuilder != null) {
			pushBuilder.path("/images/*.png");
			pushBuilder.addHeader("content-type", "image/png");
			pushBuilder.push();
			try (PrintWriter printWriter = response.getWriter()) {
				IntStream.iterate(0, n -> n +1)
				.limit(5)
				.forEach(index -> printWriter.append(writeImage(index)));
			}
		} else {
			try (PrintWriter printWriter = response.getWriter()) {
				IntStream.iterate(0, n -> n +1)
				.limit(5)
				.forEach(index -> printWriter.append(writeImage(index)));
			}
			response.getWriter().append("Working without http2\n");
		}
		
	}
	
	private String writeImage(Integer index) {
		return "<img src='resources/images/Lugocast.png'/><img src='resources/images/LuizCostaTech.png'>";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
