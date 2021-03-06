package jspCommunity.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspCommunity.container.Container;
import jspCommunity.controller.usr.UsrArticleController;
import jspCommunity.controller.usr.UsrHomeController;
import jspCommunity.controller.usr.UsrLikeController;
import jspCommunity.controller.usr.UsrMemberController;
import jspCommunity.controller.usr.UsrReplyController;

/**
 * Servlet implementation class ArticleListServlet
 */
@WebServlet("/usr/*")
public class UsrDispatcherServlet extends DispatcherServlet {

	@Override
	protected String doAction(HttpServletRequest req, HttpServletResponse resp, String controllerName,
			String actionMethodsName) {

		String jspPath = null;

		if (controllerName.equals("home")) {

			UsrHomeController homeController = Container.homeController;

			if (actionMethodsName.equals("main")) {
				jspPath = homeController.showMain(req, resp);
			}
		}

		if (controllerName.equals("article")) {
			UsrArticleController articleController = Container.articleController;

			if (actionMethodsName.equals("list")) {
				jspPath = articleController.showList(req, resp);
			} else if (actionMethodsName.equals("detail")) {
				jspPath = articleController.showDetail(req, resp);
			} else if (actionMethodsName.equals("write")) {
				jspPath = articleController.showWrite(req, resp);
			} else if (actionMethodsName.equals("doWrite")) {
				jspPath = articleController.doWrite(req, resp);
			} else if (actionMethodsName.equals("modify")) {
				jspPath = articleController.showModify(req, resp);
			} else if (actionMethodsName.equals("doModify")) {
				jspPath = articleController.doModify(req, resp);
			} else if (actionMethodsName.equals("doDelete")) {
				jspPath = articleController.doDelete(req, resp);
			}
		}

		else if (controllerName.equals("like")) {
			UsrLikeController likeController = Container.likeController;
			if (actionMethodsName.equals("doLike")) {
				jspPath = likeController.doLike(req, resp);
			} else if (actionMethodsName.equals("doDislike")) {
				jspPath = likeController.doDislike(req, resp);
			}
		}
		
		
		else if (controllerName.equals("reply")) {
			UsrReplyController replyController = Container.replyController;
			if (actionMethodsName.equals("doReply")) {
				jspPath = replyController.doReply(req, resp);
			} else if (actionMethodsName.equals("doDelete")) {
				jspPath = replyController.doDelete(req, resp);
			} else if (actionMethodsName.equals("doLike")) {
				jspPath = replyController.doLike(req, resp);
			} else if (actionMethodsName.equals("doDislike")) {
				jspPath = replyController.doDislike(req, resp);
			} 
		}

		
		
		else if (controllerName.equals("member")) {
			UsrMemberController memberController = Container.memberController;

			if (actionMethodsName.equals("join")) {
				jspPath = memberController.showJoin(req, resp);
			} else if (actionMethodsName.equals("doJoin")) {
				jspPath = memberController.doJoin(req, resp);
			} else if (actionMethodsName.equals("joinCheck")) {
				jspPath = memberController.joinCheck(req, resp);
			} else if (actionMethodsName.equals("login")) {
				jspPath = memberController.showLogin(req, resp);
			} else if (actionMethodsName.equals("findLoginId")) {
				jspPath = memberController.showFindLoginId(req, resp);
			} else if (actionMethodsName.equals("doFindLoginId")) {
				jspPath = memberController.doFindLoginId(req, resp);
			} else if (actionMethodsName.equals("findLoginPw")) {
				jspPath = memberController.showFindLoginPw(req, resp);
			} else if (actionMethodsName.equals("doFindLoginPw")) {
				jspPath = memberController.doFindLoginPw(req, resp);
			} else if (actionMethodsName.equals("doLogin")) {
				jspPath = memberController.doLogin(req, resp);
			} else if (actionMethodsName.equals("logout")) {
				jspPath = memberController.doLogout(req, resp);
			} else if (actionMethodsName.equals("modify")) {
				jspPath = memberController.showModify(req, resp);
			} else if (actionMethodsName.equals("doModify")) {
				jspPath = memberController.doModify(req, resp);
			} else if (actionMethodsName.equals("getLoginIdDup")) {
				jspPath = memberController.getLoginIdDup(req, resp);
			}
		}

		return jspPath;
	}
}