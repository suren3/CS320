package lab2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageDownloader
 */
@WebServlet("/Lab2/ImageDownloader")
public class ImageDownloader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if (request.getParameter("image") != null) {
			String image_name = null;
		    byte[] buffer = new byte[2048];
	        int numOfBytesRead = -1;
	        
			image_name = request.getParameter("image") + ".png";
			String realPath = getServletContext().getRealPath("Images/"+image_name);

		    OutputStream outputStream  = response.getOutputStream();
		    
		    File downloadFile = new File(realPath);
			FileInputStream inputStream = new FileInputStream(downloadFile);

		    response.setContentType("image/png");
		    response.setHeader("Content-Disposition", "attachment; filename="+image_name);

	        while ((numOfBytesRead = inputStream.read(buffer)) != -1) {
	            outputStream.write(buffer, 0, numOfBytesRead);
	        }

		    inputStream.close();
		    outputStream.flush();
		    outputStream.close();
		    
		} else {
		
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<head>");
			out.println("	<meta charset=\"UTF-8\">");
			out.println("	<title>ImageDonloader</title>");
			out.println("	<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css\">");
			out.println("</head>");
			out.println("<body>");
			out.println("	<form class=\"form-horizontal\" action=\"ImageDownloader\" method=\"get\"> ");
			out.println("		<a href=\"ImageDownloader?image=bean\">Bean</a>");
			out.println("		</br>");
			out.println("		</br>");
			out.println("		<a href=\"ImageDownloader?image=clint\">Clint</a>");
			out.println("		</br>");
			out.println("		</br>");
			out.println("		<a href=\"ImageDownloader?image=gaga\">Gaga</a>");
			out.println("		</br>");
			out.println("		</br>");
			out.println("		<a href=\"ImageDownloader?image=hermoine\">Hermoine</a>");
			out.println("		</br>");
			out.println("		</br>");
			out.println("		<a href=\"ImageDownloader?image=joker\">Joker</a>");
			out.println("		</br>");
			out.println("		</br>");
			out.println("		<a href=\"ImageDownloader?image=mj\">MJ</a>");
			out.println("		</br>");
			out.println("		</br>");
			out.println("		<a href=\"ImageDownloader?image=penny\">Penny</a>");
			out.println("		</br>");
			out.println("		</br>");
			out.println("		<a href=\"ImageDownloader?image=sheldon\">Sheldon</a>");
			out.println("		</br>");
			out.println("		</br>");
			out.println("		<a href=\"ImageDownloader?image=steve\">Steve</a>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
