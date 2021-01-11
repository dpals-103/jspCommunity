package jspCommunity.container;

import jspCommunity.controller.usr.ArticleController;
import jspCommunity.controller.usr.MemberController;
import jspCommunity.dao.ArticleDao;
import jspCommunity.dao.MemberDao;
import jspCommunity.service.ArticleService;
import jspCommunity.service.MemberService;

public class Container {

	public static ArticleService articleService; 
	public static ArticleDao articleDao;
	public static ArticleController articleController;
	public static MemberDao memberDao;
	public static MemberService memberService;
	public static MemberController memberController; 
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		articleService = new ArticleService();
		memberService = new MemberService(); 
		
		articleController = new ArticleController(); 
		memberController = new MemberController();
		
	}
}
