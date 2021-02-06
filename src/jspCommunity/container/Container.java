package jspCommunity.container;

import jspCommunity.controller.usr.AdmMemberController;
import jspCommunity.controller.usr.UsrArticleController;
import jspCommunity.controller.usr.UsrHomeController;
import jspCommunity.controller.usr.UsrLikeController;
import jspCommunity.controller.usr.UsrMemberController;
import jspCommunity.controller.usr.UsrReplyController;
import jspCommunity.dao.ArticleDao;
import jspCommunity.dao.AttrDao;
import jspCommunity.dao.LikeDao;
import jspCommunity.dao.MemberDao;
import jspCommunity.dao.ReplyDao;
import jspCommunity.service.ArticleService;
import jspCommunity.service.AttrService;
import jspCommunity.service.LikeService;
import jspCommunity.service.MailService;
import jspCommunity.service.MemberService;
import jspCommunity.service.ReplyService;

public class Container {

	
	
	public static UsrLikeController likeController;
	public static LikeService likeService;
	public static LikeDao likeDao;
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
	public static ReplyDao replyDao; 
	public static ReplyService replyService; 
	public static UsrReplyController replyController; 
	
	static {
	
		attrDao = new AttrDao(); 
		articleDao = new ArticleDao();
		likeDao = new LikeDao(); 
		memberDao = new MemberDao();
		replyDao = new ReplyDao();
		
		attrService = new AttrService(); 
		mailService = new MailService(); 
		articleService = new ArticleService();
		likeService = new LikeService();
		memberService = new MemberService();
		replyService = new ReplyService();
	
		
		likeController = new UsrLikeController();
		articleController = new UsrArticleController(); 
		memberController = new UsrMemberController();
		admMemberController = new AdmMemberController(); 
		homeController = new UsrHomeController();
		replyController = new UsrReplyController();
		
	}
}
