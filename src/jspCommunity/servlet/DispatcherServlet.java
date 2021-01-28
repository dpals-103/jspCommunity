package jspCommunity.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspCommunity.container.Container;
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

		if (requestUri.length() < 5) {
			resp.getWriter().append("올바른 요청이 아닙니다");
			return null;
		}

		MysqlUtil.setDBInfo("127.0.0.1", "dpals103", "dlgywn0168", "jspCommunity");
		// MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");

		String controllerTypeName = requestUriBits[2]; // usr
		String controllerName = requestUriBits[3]; // article
		String actionMethodsName = requestUriBits[4]; // write,modify...

		String actionUrl = "/" + controllerTypeName + "/" + controllerName + "/" + actionMethodsName;

		// 데이터 추가 인터셉터 시작
		boolean isLogined = false;
		int loginedMemberId = 0;
		Member loginedMember = null;

		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			loginedMember = Container.memberService.getMember(loginedMemberId);
		}

		req.setAttribute("isLogined", isLogined);
		req.setAttribute("loginedMemberId", loginedMemberId);
		req.setAttribute("loginedMember", loginedMember);

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
		needToLogin.add("/usr/article/doModift");
		needToLogin.add("/usr/article/doDelete");
		
		
		if (needToLogin.contains(actionUrl)) {

			if ((boolean) req.getAttribute("isLogined") == false) {
				req.setAttribute("alertMsg", "로그인 후 이용해주세요");
				req.setAttribute("replaceUrl", "../member/login");

				RequestDispatcher rd = req.getRequestDispatcher("/jsp/common/redirect.jsp");
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

				RequestDispatcher rd = req.getRequestDispatcher("/jsp/common/redirect.jsp");
				rd.forward(req, resp);
			}
		} 
		


		// 로그인 필요 필터링 인터셉터 끝
		Map rs = new HashMap<>();

		rs.put("controllerName", controllerName);
		rs.put("actionMethodsName", actionMethodsName);

		return rs;
	}

	// 추상메서드
	protected abstract String doAction(HttpServletRequest req, HttpServletResponse resp, String controllerName,
			String actionMethodsName);

	//
	private void doAfterAction(HttpServletRequest req, HttpServletResponse resp, String jspPath)
			throws ServletException, IOException {

		MysqlUtil.closeConnection();

		RequestDispatcher rd = req.getRequestDispatcher("/jsp/" + jspPath + ".jsp");
		rd.forward(req, resp);

	}

}
