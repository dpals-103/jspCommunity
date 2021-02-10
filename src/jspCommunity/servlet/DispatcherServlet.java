package jspCommunity.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspCommunity.App;
import jspCommunity.container.Container;
import jspCommunity.dto.Board;
import jspCommunity.dto.Like;
import jspCommunity.dto.Member;
import jspCommunity.mysqlUtil.MysqlUtil;
import jspCommunity.util.Util;

public abstract class DispatcherServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		run(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	public void run(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Map<String, Object> doBeforeActionRs = doBeforeAction(req, resp);

		if (doBeforeActionRs == null) {
			return;
		}

		String jspPath = doAction(req, resp, (String) doBeforeActionRs.get("controllerName"),
				(String) doBeforeActionRs.get("actionMethodsName"));

		if (jspPath == null) {
			resp.getWriter().append("jsp 정보가 없습니다");
			return;
		}

		doAfterAction(req, resp, jspPath);

	}

	private Map<String, Object> doBeforeAction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String requestUri = req.getRequestURI();
		String[] requestUriBits = requestUri.split("/");

		int minBitsCount = 5;
		int controllerTypeNameIndex = 2;
		int controllerNameIndex = 3;
		int actionMethodsNameIndex = 4;
		
		if (App.isProductMode()) {
			minBitsCount = 4;
			
			controllerTypeNameIndex = 1;
			controllerNameIndex = 2;
			actionMethodsNameIndex = 3;
		}
	
		
		if (requestUri.length() < minBitsCount) {
			resp.getWriter().append("올바른 요청이 아닙니다");
			return null;
		}

		if (App.isProductMode()) {
			MysqlUtil.setDBInfo("127.0.0.1", "sbsstLocal", "sbs123414", "jspCommunity");
		} else {
			MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");
			//MysqlUtil.setDBInfo("127.0.0.1", "dpals103", "dlgywn0168", "jspCommunity");
			MysqlUtil.setDevMode(true);
		}

	

		String controllerTypeName = requestUriBits[controllerTypeNameIndex]; // usr
		String controllerName = requestUriBits[controllerNameIndex]; // article
		String actionMethodsName = requestUriBits[actionMethodsNameIndex]; // write,modify...

		String actionUrl = "/" + controllerTypeName + "/" + controllerName + "/" + actionMethodsName;

		// 데이터 추가 인터셉터 시작
		boolean isLogined = false;
		int loginedMemberId = 0;
		Member loginedMember = null;
		boolean isTempPassword = false;
		boolean liked = false;
		boolean disliked = false;
		boolean likedReply = false;
		boolean dislikedReply = false;

		int id = Util.getAsInt(req.getParameter("id"), 0);
		int replyId = Util.getAsInt(req.getParameter("replyId"), 0);

		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") != null) {

			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			loginedMember = Container.memberService.getMember(loginedMemberId);
			isTempPassword = Container.memberService.getIsUsingTempPassword(loginedMemberId);

			if ((int) session.getAttribute("loginedMemberId") > 0) {
				Like likedArticle = Container.likeService.getLikedArticle(loginedMemberId, id);
				Like DislikedArticle = Container.likeService.getDislikedArticle(loginedMemberId, id);

				if (likedArticle != null) {
					liked = true;
				}

				if (DislikedArticle != null) {
					disliked = true;
				}

			}
		}

		req.setAttribute("isLogined", isLogined);
		req.setAttribute("loginedMemberId", loginedMemberId);
		req.setAttribute("loginedMember", loginedMember);
		req.setAttribute("isTempPassword", isTempPassword);
		req.setAttribute("liked", liked);
		req.setAttribute("disliked", disliked);
		req.setAttribute("likedReply", likedReply);
		req.setAttribute("dislikedReply", dislikedReply);

		String currentUrl = req.getRequestURI();

		if (req.getQueryString() != null) {
			currentUrl += "?" + req.getQueryString();
		}

		String encodedCurrentUrl = Util.getUrlEcoded(currentUrl);

		req.setAttribute("currentUrl", currentUrl);
		req.setAttribute("encodedCurrentUrl", encodedCurrentUrl);
		// 데이터 추가 인터셉터 끝

		// 로그인 필요 필터링 인터셉터 시작

		List<String> needToLogin = new ArrayList();
		List<String> needToLogout = new ArrayList();

		needToLogin.add("/usr/member/doLogout");
		needToLogin.add("/usr/member/modify");
		needToLogin.add("/usr/member/doModify");
		needToLogin.add("/usr/article/write");
		needToLogin.add("/usr/article/doWrite");
		needToLogin.add("/usr/article/modify");
		needToLogin.add("/usr/article/doModify");
		needToLogin.add("/usr/article/doDelete");
		needToLogin.add("/usr/like/doLike");
		needToLogin.add("/usr/like/doDisLike");
		needToLogin.add("/usr/reply/doReply");
		needToLogin.add("/usr/reply/doDelete");
		needToLogin.add("/usr/reply/doLike");
		needToLogin.add("/usr/reply/doDisLike");

		if (needToLogin.contains(actionUrl)) {

			if ((int) req.getAttribute("loginedMemberId") == 0) {
				req.setAttribute("alertMsg", "로그인 후 이용해주세요");
				req.setAttribute("replaceUrl", "../member/login?afterLoginUrl=" + encodedCurrentUrl);

				RequestDispatcher rd = req.getRequestDispatcher(getJsonDirPath() + "/common/redirect.jsp");
				rd.forward(req, resp);
			}
		}

		needToLogout.add("/usr/member/doLogin");
		needToLogout.add("/usr/member/join");
		needToLogout.add("/usr/member/doJoin");
		needToLogout.add("/usr/member/findLoginId");
		needToLogout.add("/usr/member/doFindLoginId");

		if (needToLogout.contains(actionUrl)) {

			if ((boolean) req.getAttribute("isLogined") == true) {
				req.setAttribute("alertMsg", "로그아웃 후 이용해주세요");
				req.setAttribute("replaceUrl", "../home/main");

				RequestDispatcher rd = req.getRequestDispatcher(getJsonDirPath() + "/common/redirect.jsp");
				rd.forward(req, resp);
			}
		}

		// 로그인 필요 필터링 인터셉터 끝
		Map rs = new HashMap<>();

		rs.put("controllerName", controllerName);
		rs.put("actionMethodsName", actionMethodsName);

		List<Board> boards = Container.articleService.getBoards();

		req.setAttribute("boards", boards);

		return rs;
	}

	private String getJsonDirPath() {
		return "/WEB-INF/jsp";
	}

	// 추상메서드
	protected abstract String doAction(HttpServletRequest req, HttpServletResponse resp, String controllerName,
			String actionMethodsName);

	//
	private void doAfterAction(HttpServletRequest req, HttpServletResponse resp, String jspPath)
			throws ServletException, IOException {

		MysqlUtil.closeConnection();

		RequestDispatcher rd = req.getRequestDispatcher(getJsonDirPath() + "/" + jspPath + ".jsp");
		rd.forward(req, resp);

	}

}
