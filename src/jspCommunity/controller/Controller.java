package jspCommunity.controller;

import javax.servlet.http.HttpServletRequest;

import jspCommunity.dto.ResultData;

public class Controller {
	protected String msgAndBack(HttpServletRequest req, String msg) {
		req.setAttribute("alertMsg", msg);
		req.setAttribute("historyBack", true);
		return "common/redirect";
	}
	
	protected String msgAndReplace(HttpServletRequest req, String msg, String repalceUrl) {
		req.setAttribute("alertMsg", msg);
		req.setAttribute("replaceUrl", repalceUrl);
		return "common/redirect";
	}
	
	protected String json(HttpServletRequest req, ResultData resultData) {
		
		req.setAttribute("data", resultData);
		return "common/json";
	}
}
