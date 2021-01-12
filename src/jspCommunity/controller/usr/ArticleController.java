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
		req.setAttribute("boardId", boardId);
		req.setAttribute("board", board);

		return "/usr/article/list";
	}

	public String showDetail(HttpServletRequest req, HttpServletResponse resp) {

		int id = Integer.parseInt(req.getParameter("id"));
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		Board board = articleService.getBoard(boardId); 

		Article article = articleService.getArticle(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);

			return "common/redirect";
		}
		
		if (board == null) {
			req.setAttribute("alertMsg", "해당 게시판은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);

			return "common/redirect";
		}

		req.setAttribute("article", article);
		req.setAttribute("boardId", boardId);
		req.setAttribute("board", board);

		return "/usr/article/detail";
	}

	public String showWrite(HttpServletRequest req, HttpServletResponse resp) {
		
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
		req.setAttribute("board",board);
		
		return "/usr/article/write";
	}

	public String doWrite(HttpServletRequest req, HttpServletResponse resp) {
		
		int memberId = Integer.parseInt(req.getParameter("memberId"));
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		
		int aritlceId = articleService.write(memberId,boardId,title,body); 
		Article article = articleService.getArticle(aritlceId); 
	
		
		req.setAttribute("article", article);
		req.setAttribute("memberId", memberId);
		req.setAttribute("boardId", boardId);
		req.setAttribute("title", title);
		req.setAttribute("body", body);
		
		req.setAttribute("alertMsg", "작성되었습니다");
		req.setAttribute("replaceUrl",String.format("detail?id=%d&boardId=%d",aritlceId,boardId));

		return "common/redirect";
	}

	public String showModify(HttpServletRequest req, HttpServletResponse resp) {
		
		int memberId = Integer.parseInt(req.getParameter("memberId"));
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		int id = Integer.parseInt(req.getParameter("id"));
		
		Article article = articleService.getArticle(id); 
		Member member = MemberService.getMember(memberId); 
		Board board = articleService.getBoard(boardId); 
		
		if(id != article.id) {
			req.setAttribute("alertMsg", "해당 글 작성자만 가능합니다");
			req.setAttribute("historyBack", true);
			
			return "common/redirect";
		}
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
		req.setAttribute("board",board);
		req.setAttribute("id",id);
		req.setAttribute("article",article);
		
		return "/usr/article/modify";
	}

	public String doModify(HttpServletRequest req, HttpServletResponse resp) {
		
		int memberId = Integer.parseInt(req.getParameter("memberId"));
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		int id =  Integer.parseInt(req.getParameter("id"));
		
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		
		articleService.modify(memberId,boardId,id,title,body);
		
		Article article = articleService.getArticle(id); 

		
		req.setAttribute("alertMsg", "수정되었습니다");
		req.setAttribute("replaceUrl",String.format("detail?id=%d&boardId=%d",id,boardId));

		return "common/redirect";
	}

	public String doDelete(HttpServletRequest req, HttpServletResponse resp) {
		
		int memberId = Integer.parseInt(req.getParameter("memberId"));
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		int id =  Integer.parseInt(req.getParameter("id"));
		
		articleService.delete(memberId,boardId,id);
		
		req.setAttribute("alertMsg", "삭제되었습니다");
		req.setAttribute("replaceUrl",String.format("list?boardId=%d",boardId));

		return "common/redirect";
	}
}
