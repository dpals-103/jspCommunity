package jspCommunity.controller.usr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspCommunity.container.Container;
import jspCommunity.dto.Article;
import jspCommunity.dto.Board;
import jspCommunity.dto.Member;
import jspCommunity.service.ArticleService;
import jspCommunity.service.MemberService;

public class ArticleController {
	private ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService;
	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		
		int boardId = Integer.parseInt(req.getParameter("boardId"));

		List<Article> articles = articleService.getArticles(boardId);
		
		Board board = articleService.getBoard(boardId); 

		if (board == null) {
			req.setAttribute("alertMsg", "해당 게시판은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);

			return "common/redirect";
		}

		req.setAttribute("articles", articles);

		return "/usr/article/list";
	}

	public String showDetail(HttpServletRequest req, HttpServletResponse resp) {

		int id = Integer.parseInt(req.getParameter("id"));

		Article article = articleService.getArticle(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);

			return "common/redirect";
		}

		req.setAttribute("article", article);

		return "/usr/article/detail";
	}

	public String write(HttpServletRequest req, HttpServletResponse resp) {
		
		int memberId = Integer.parseInt(req.getParameter("memberId"));
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		
		Member member = MemberService.getMember(memberId); 
		Board board = articleService.getBoard(boardId); 
		
		if(member == null) {
			req.setAttribute("alertMsg", "가입되지 않은 회원입니다");
			req.setAttribute("historyBack", true);
			
			return "common/redirect";
		}
		
		if (board == null) {
			req.setAttribute("alertMsg", "해당 게시판은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);

			return "common/redirect";
		}
		
		req.setAttribute("memberId",memberId);
		req.setAttribute("boardId",boardId);
		
		return "/usr/article/write";
	}

	public String doWrite(HttpServletRequest req, HttpServletResponse resp) {
		
		int memberId = Integer.parseInt(req.getParameter("memberId"));
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		
		int aritlceId = articleService.write(memberId,boardId,title,body); 
		Article article = articleService.getArticle(aritlceId); 
		
		if(article != null) {
			
			req.setAttribute("alertMsg", "작성되었습니다");
			req.setAttribute("historyBack", true);

			return "common/redirect";
			
		}
		
		req.setAttribute("article", article);
		req.setAttribute("memberId", memberId);
		req.setAttribute("boardId", boardId);
		req.setAttribute("title", title);
		req.setAttribute("body", body);
		
		return "/usr/article/doWrite";
	}
}
