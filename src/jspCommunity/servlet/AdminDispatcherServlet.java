package jspCommunity.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspCommunity.container.Container;
import jspCommunity.controller.usr.AdmMemberController;
import jspCommunity.controller.usr.UsrArticleController;
import jspCommunity.mysqlUtil.MysqlUtil;

/**
 * Servlet implementation class ArticleListServlet
 */
@WebServlet("/adm/*")
public class AdminDispatcherServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String requestUri = req.getRequestURI();
		String[] requestUriBits = requestUri.split("/");

		if (requestUri.length() < 5) {
			resp.getWriter().append("올바른 요청이 아닙니다");
			return;
		}

		String controllerName = requestUriBits[3];
		String actionMethodsName = requestUriBits[4];

		MysqlUtil.setDBInfo("127.0.0.1", "dpals103", "dlgywn0168", "jspCommunity");
		// MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");

		String jspPath = null;
		
		if (controllerName.equals("member")) {
			AdmMemberController admMemberController = Container.admMemberController;

			if (actionMethodsName.equals("list")) {
				jspPath = admMemberController.showList(req, resp);
			}

		}

		MysqlUtil.closeConnection();

		RequestDispatcher rd = req.getRequestDispatcher("/jsp/" + jspPath + ".jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
