package jspCommunity.controller.usr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspCommunity.container.Container;
import jspCommunity.dto.Board;
import jspCommunity.service.ArticleService;
import jspCommunity.service.MemberService;

public class UsrHomeController {
	private static MemberService memberService;
	private static ArticleService articleService;

	public UsrHomeController() {
		memberService = Container.memberService;
		articleService = Container.articleService;
	}

	public static String showMain(HttpServletRequest req, HttpServletResponse resp) {
		
		return "usr/home/main";
	}

}