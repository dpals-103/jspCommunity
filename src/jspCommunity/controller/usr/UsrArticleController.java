package jspCommunity.controller.usr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspCommunity.container.Container;
import jspCommunity.controller.Controller;
import jspCommunity.dto.Article;
import jspCommunity.dto.Board;
import jspCommunity.dto.Like;
import jspCommunity.dto.Member;
import jspCommunity.service.ArticleService;
import jspCommunity.service.LikeService;
import jspCommunity.service.MemberService;
import jspCommunity.util.Util;

public class UsrArticleController extends Controller {
	private ArticleService articleService;
	private LikeService likeService;

	public UsrArticleController() {
		articleService = Container.articleService;
		likeService = Container.likeService;
	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {

		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);
		String searchKeyword = req.getParameter("searchKeyword");
		String searchKeywordType = req.getParameter("searchKeywordType");

		int totalCount = articleService.getArticlesCountBySearch(boardId, searchKeyword, searchKeywordType);

		int itemsInAPage = 15;
		// page prameter의 정수값 또는 문자형일시 정수로 변화해서 리턴, null이면 1을 리턴
		int page = Util.getAsInt(req.getParameter("page"), 1);
		int limitStart = (page - 1) * itemsInAPage;
		int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);

		// 페이지 박스 계산하기
		int pageBoxSize = 10;
		int prevPageBoxCount = (page - 1) / pageBoxSize;
		int pageBoxStartPage = pageBoxSize * prevPageBoxCount + 1;
		int pageBoxEndPage = pageBoxStartPage + pageBoxSize - 1;

		if (pageBoxEndPage > totalPage) {
			pageBoxEndPage = totalPage;
		}

		// 이전버튼 페이지 계산
		int prevPage = pageBoxStartPage - 1;
		if (prevPage < 1) {
			prevPage = 1;
		}

		// 이전버튼 노출여부
		boolean needToShowPrevPageBox = page > 1;

		// 다음버튼 페이지 계산
		int nextPage = pageBoxEndPage + 1;
		if (nextPage < totalPage) {
			prevPage = totalPage;
		}

		// 다음버튼 노출여부
		boolean needToShowNextPageBox = page < totalPage;

		List<Article> articles = articleService.getArticlesBySearch(boardId, limitStart, itemsInAPage, searchKeyword,
				searchKeywordType);
		// List<Article> articles = articleService.getArticles(boardId);

		Board board = articleService.getBoard(boardId);

		if (board == null) {
			return msgAndBack(req, "해당 게시판은 존재하지 않습니다");
		}

		req.setAttribute("board", board);
		req.setAttribute("articles", articles);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("page", page);

		req.setAttribute("needToShowPrevPageBox", needToShowPrevPageBox);
		req.setAttribute("needToShowNextPageBox", needToShowNextPageBox);

		req.setAttribute("pageBoxStartPage", pageBoxStartPage);
		req.setAttribute("pageBoxEndPage", pageBoxEndPage);

		req.setAttribute("prevPage", prevPage);
		req.setAttribute("nextPage", nextPage);

		return "/usr/article/list";
	}

	public String showDetail(HttpServletRequest req, HttpServletResponse resp) {

		int id = Util.getAsInt(req.getParameter("id"), 0);
		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);
		Board board = articleService.getBoard(boardId);

		Article article = articleService.getArticle(id);	
		int like = likeService.getLikeCount(id);
		int dislike = likeService.getDislikeCount(id);

		HttpSession session = req.getSession();
		boolean liked = false;
		boolean disliked = false;
		
		if( session.getAttribute("loginedMemberId") != null) {
			int memberId = (int) session.getAttribute("loginedMemberId");

			Like likedArticle = likeService.getLikedArticle(memberId, boardId);

			if (likedArticle != null) {
				liked = true;
				req.setAttribute("liked", liked);
			}

			Like DislikedArticle = likeService.getDislikedArticle(memberId, boardId);


			if (DislikedArticle != null) {
				disliked = true;
				req.setAttribute("disliked", disliked);
			}

		}
		
		
		if (article == null) {
			return msgAndBack(req, id + "번 게시물은 존재하지 않습니다.");
		}

		/* 조회수증가 */
		articleService.increaseCount(boardId, id);

		req.setAttribute("article", article);
		req.setAttribute("boardId", boardId);
		req.setAttribute("board", board);
		req.setAttribute("like", like);
		req.setAttribute("dislike", dislike);
		req.setAttribute("liked", liked);
		req.setAttribute("disliked", disliked);
		
		return "/usr/article/detail";
	}

	public String showWrite(HttpServletRequest req, HttpServletResponse resp) {

		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);

		Board board = articleService.getBoard(boardId);

		req.setAttribute("boardId", boardId);
		req.setAttribute("board", board);

		return "/usr/article/write";
	}

	public String doWrite(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession();

		int memberId = (int) session.getAttribute("loginedMemberId");
		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);

		String title = req.getParameter("title");

		if (Util.isEmpty(title)) {
			return msgAndBack(req, "제목을 입력해주세요");
		}

		String body = req.getParameter("body");

		if (Util.isEmpty(body)) {
			return msgAndBack(req, "내용을 입력해주세요");
		}

		int aritlceId = articleService.write(memberId, boardId, title, body);

		return msgAndReplace(req, "작성되었습니다", String.format("detail?id=%d&boardId=%d", aritlceId, boardId));
	}

	public String showModify(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession();

		int memberId = (int) session.getAttribute("loginedMemberId");
		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);
		int id = Integer.parseInt(req.getParameter("id"));

		Article article = articleService.getArticle(id);
		Member member = MemberService.getMember(memberId);
		Board board = articleService.getBoard(boardId);

		if (memberId != article.getMemberId()) {
			return msgAndBack(req, "해당 글 작성자만 수정이 가능합니다");
		}

		if (article == null) {
			return msgAndBack(req, id + "번 게시물은 존재하지 않습니다.");
		}

		req.setAttribute("memberId", memberId);
		req.setAttribute("boardId", boardId);
		req.setAttribute("board", board);
		req.setAttribute("id", id);
		req.setAttribute("article", article);

		return "/usr/article/modify";
	}

	public String doModify(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession();

		int memberId = (int) session.getAttribute("loginedMemberId");
		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);
		int id = Util.getAsInt(req.getParameter("id"), 0);

		String title = req.getParameter("title");

		if (Util.isEmpty(title)) {
			return msgAndBack(req, "제목을 입력해주세요");
		}

		String body = req.getParameter("body");

		if (Util.isEmpty(body)) {
			return msgAndBack(req, "내용을 입력해주세요");
		}

		Article article = articleService.getArticle(id);

		if (memberId != article.getMemberId()) {
			return msgAndBack(req, "해당 글 작성자만 수정이 가능합니다");
		}

		articleService.modify(memberId, boardId, id, title, body);

		return msgAndReplace(req, "수정되었습니다", String.format("detail?id=%d&boardId=%d", id, boardId));

	}

	public String doDelete(HttpServletRequest req, HttpServletResponse resp) {

		int memberId = Integer.parseInt(req.getParameter("memberId"));
		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);
		int id = Util.getAsInt(req.getParameter("id"), 0);

		articleService.delete(memberId, boardId, id);

		return msgAndReplace(req, "삭제되었습니다", String.format("list?boardId=%d", boardId));
	}

}
