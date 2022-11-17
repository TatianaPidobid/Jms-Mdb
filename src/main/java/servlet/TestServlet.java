package servlet;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.app.SimpleEJB;

@WebServlet("/TestSerlvet")
public class TestServlet {
	private static final long serialVersionUID = 1L;

	public TestServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hello from Servlet");

		InitialContext ic;
		SimpleEJB bean;

		String message = request.getParameter("printMessage");
//		 response.sendRedirect("http://localhost:8080/BobReynoldsWeb/index.jsp");

		if (message != null) {
			try {
				ic = new InitialContext();
				bean = (SimpleEJB) ic.lookup("java:global/com/mycompany/app/SimpleEJB!" + "com.mycompany.app");
				bean.printMessage(message);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	}
}
