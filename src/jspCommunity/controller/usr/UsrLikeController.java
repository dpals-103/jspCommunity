package jspCommunity.controller.usr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspCommunity.container.Container;
import jspCommunity.controller.Controller;
import jspCommunity.dto.Like;
import jspCommunity.service.LikeService;
import jspCommunity.util.Util;

public class UsrLikeController extends Controller {
	private LikeService likeService;

	public UsrLikeController() {
		likeService = Container.likeService;
	}

	public String doLike(HttpServletRequest req, HttpServletResponse resp) {

		int id = Util.getAsInt(req.getParameter("id"), 0);
		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);
		int memberId = (int) req.getAttribute("loginedMemberId");

		/*
		if (memberId == 0) {
			return msgAndReplace(req, "로그인 후 이용해주세요", "../member/login");
		}
		*/

		Like likedArticle = likeService.getLikedArticle(memberId, id);
		Like dislikedArticle = likeService.getDislikedArticle(memberId, id);

		if (dislikedArticle != null ) {
			// 싫어요를 선택했던 상황이라면, 싫어요 취소하고 좋아요 누르기
			likeService.doCancleDislike(memberId, id);
			likeService.doLike(memberId, id);
			return msgAndReplace(req, "이 게시글을 좋아합니다",
					String.format("../article/detail?id=%d&boardId=%d&memberId=%d", id, boardId, memberId));
		} else if (likedArticle != null) {
			likeService.doCanclelike(memberId, id);
			return msgAndReplace(req, "좋아요를 취소합니다",
					String.format("../article/detail?id=%d&boardId=%d&memberId=%d", id, boardId, memberId));
		} else {
			likeService.doLike(memberId, id);
			return msgAndReplace(req, "이 게시글을 좋아합니다",
					String.format("../article/detail?id=%d&boardId=%d&memberId=%d", id, boardId, memberId));
		}

	}

	public String doDislike(HttpServletRequest req, HttpServletResponse resp) {

		int id = Util.getAsInt(req.getParameter("id"), 0);
		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);
		int memberId = (int) req.getAttribute("loginedMemberId");
		
		/*
		if (memberId == 0) {
			return msgAndReplace(req, "로그인 후 이용해주세요", "../member/login");
		}
		*/
		
		Like dislikedArticle = likeService.getDislikedArticle(memberId, id);
		
		if (dislikedArticle != null) {
			likeService.doCancleDislike(memberId, id);
			return msgAndReplace(req, "싫어요를 취소합니다",
					String.format("../article/detail?id=%d&boardId=%d&memberId=%d", id, boardId, memberId));
		}

		Like likedArticle = likeService.getLikedArticle(memberId, id);
		
		if (likedArticle != null) {
			likeService.doCanclelike(memberId, id);
			likeService.doDislike(memberId, id);
			return msgAndReplace(req, "이 게시글이 마음에 들지 않습니다.",
					String.format("../article/detail?id=%d&boardId=%d&memberId=%d", id, boardId, memberId));
		}

		likeService.doDislike(memberId, id);
		return msgAndReplace(req, "이 게시글이 마음에 들지 않습니다.",
				String.format("../article/detail?id=%d&boardId=%d&memberId=%d", id, boardId, memberId));

	}

}
