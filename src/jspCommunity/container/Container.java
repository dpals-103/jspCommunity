package jspCommunity.container;

import jspCommunity.controller.usr.AdmMemberController;
import jspCommunity.controller.usr.UsrArticleController;
import jspCommunity.controller.usr.UsrHomeController;
import jspCommunity.controller.usr.UsrMemberController;
import jspCommunity.dao.ArticleDao;
import jspCommunity.dao.AttrDao;
import jspCommunity.dao.MemberDao;
import jspCommunity.service.ArticleService;
import jspCommunity.service.AttrService;
import jspCommunity.service.MailService;
import jspCommunity.service.MemberService;

public class Container {

	
	public static MailService mailService;
	public static UsrHomeController homeController;
	public static ArticleService articleService; 
	public static ArticleDao articleDao;
	public static UsrArticleController articleController;
	public static MemberDao memberDao;
	public static MemberService memberService;
	public static UsrMemberController memberController;
	public static AdmMemberController admMemberController;
	public static AttrDao attrDao;
	public static AttrService attrService; 
	
	static {
	
		attrDao = new AttrDao(); 
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		
		attrService = new AttrService(); 
		mailService = new MailService(); 
		articleService = new ArticleService();
		memberService = new MemberService();
	
		
		articleController = new UsrArticleController(); 
		memberController = new UsrMemberController();
		admMemberController = new AdmMemberController(); 
		homeController = new UsrHomeController();
		
	}
}
