package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String password = request.getParameter("password");

		if (password.equals("asd"))
		{
			HttpSession session = request.getSession();
			if (!name.equals(session.getAttribute(name)))
			{
				out.print("Welcome " + name + " Have a nice day.");
				session.setAttribute(name, name);
				request.getRequestDispatcher("logined.html").include(request, response);
			} else
			{
				out.print("You are already logined.");
				request.getRequestDispatcher("logined.html").include(request, response);
			}
		} else
		{
			out.print("Bad username or password! Permission denied!");
			request.getRequestDispatcher("index.html").include(request, response);
		}
		out.close();
	}
}