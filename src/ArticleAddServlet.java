
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ArticleListServlet
 */
@WebServlet("/usr/article/doWrite")
public class ArticleAddServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		MysqlUtil.setDBInfo("127.0.0.1", "dpals103", "dlgywn0168", "jspCommunity");
		// MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");

		 
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		
		
		MysqlUtil.closeConnection();

		req.setAttribute("title", title);
		req.setAttribute("body", body);
		
		req.getRequestDispatcher("/jsp/usr/article/doWrite.jsp").forward(req, resp);
	}

}
