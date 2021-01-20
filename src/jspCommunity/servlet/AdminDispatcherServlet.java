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


// /adm/member/list
@WebServlet("/adm/*")
public class AdminDispatcherServlet extends DispatcherServlet {

	@Override
	protected String doAction(HttpServletRequest req, HttpServletResponse resp, String controllerName,
			String actionMethodsName) {

		String jspPath = null;

		if (controllerName.equals("member")) {
			AdmMemberController admMemberController = Container.admMemberController;

			if (actionMethodsName.equals("list")) {
				jspPath = admMemberController.showList(req, resp);

			}

		}
		return jspPath;
	}
}
