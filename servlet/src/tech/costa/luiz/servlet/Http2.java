package tech.costa.luiz.servlet;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.nonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;
import javax.ws.rs.core.MediaType;

/**
 * Servlet implementation class Http2
 */
@WebServlet("/Http2")
public class Http2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String path = "resources/images/";
	private static final String lugocast = "Lugocast";
	private static final String luizcostatech = "LuizCostaTech";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Http2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType(MediaType.TEXT_HTML_TYPE.withCharset(UTF_8.name()).toString());
        response.setStatus(200);
		PushBuilder pushBuilder = request.newPushBuilder();
		if (nonNull(pushBuilder)) {
			response.addHeader("protocol", "HTTP/2");
            response.getWriter().append("<p>The image below was pushed using HTTP/2.</p>");
            pushImages(pushBuilder);
		} else {
			response.addHeader("protocol", "HTTP 1.1");
            response.getWriter().append("<p>The image below was sent normally using HTTP 1.1.</p>");
		}
		getImages(response);
	}

	private void getImages(HttpServletResponse response) throws IOException {
		for (int index=1; index<=30; index++) {
			File file = Paths.get(path+lugocast+"/"+index+".jpg").toFile();
			response.getWriter().append(String.format("%s%s%s%s%s", "<img src=\"", path, lugocast+"/", file.getName(), "\" />"));
		}
		
		/*try (Stream<Path> paths = Files.walk(Paths.get(path+lugocast))) {
		    paths
		        .filter(Files::isRegularFile)
		        .forEach(currentFile -> {
					try {
						System.out.println(currentFile.toFile().getName());
						response.getWriter().append(String.format("%s%s%s%s%s", "<img src=\"", path, luizcostatech+"/", currentFile.toFile().getName(), "\" />"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        });
		}*/
	}

	private void pushImages(PushBuilder pushBuilder) throws IOException {
		/*try (Stream<Path> paths = Files.walk(Paths.get(path+lugocast))) {
		    paths
		        .filter(Files::isRegularFile)
		        .forEach(file -> {
		        	pushBuilder.path(String.format("%s%s%s", path, lugocast, file.toFile().getName())).push();
		        });
		}*/
		for (int index=1; index<=30; index++) {
			File file = Paths.get(path+lugocast+"/"+index+".jpg").toFile();
			pushBuilder.path(file.getPath()).push();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
