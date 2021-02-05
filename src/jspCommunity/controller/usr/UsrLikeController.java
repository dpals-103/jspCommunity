package jspCommunity.controller.usr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspCommunity.container.Container;
import jspCommunity.controller.Controller;
import jspCommunity.dto.Like;
import jspCommunity.service.LikeService;
import jspCommunity.util.Util;

public class UsrLikeController extends Controller{
	private LikeService likeService;  
	
	public UsrLikeController() {
		likeService = Container.likeService;
	}
	
	
	public String doLike(HttpServletRequest req, HttpServletResponse resp) {

		int id = Util.getAsInt(req.getParameter("id"), 0);
		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);
		int memberId = (int) req.getAttribute("loginedMemberId");

		Like likedArticle = likeService.getLikedArticle(memberId, id);

		if (likedArticle != null) {
			likeService.doCanclelike(memberId, id);
			return msgAndReplace(req, "좋아요를 취소합니다", String.format("../article/detail?id=%d&boardId=%d&memberId=%d", id, boardId , memberId));
		} else {
			likeService.doLike(memberId, id);
			return msgAndReplace(req, "이 게시글을 좋아합니다", String.format("../article/detail?id=%d&boardId=%d&memberId=%d", id, boardId,memberId));
		}

	}

	public String doDislike(HttpServletRequest req, HttpServletResponse resp) {
		
		int id = Util.getAsInt(req.getParameter("id"), 0);
		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);
		int memberId = (int) req.getAttribute("loginedMemberId");

		Like dislikedArticle = likeService.getDislikedArticle(memberId, id);

		if (dislikedArticle != null) {
			likeService.doCancleDislike(memberId, id);
			return msgAndReplace(req, "싫어요를 취소합니다", String.format("../article/detail?id=%d&boardId=%d&memberId=%d", id, boardId,memberId));
		} else {
			likeService.doDislike(memberId, id);
			return msgAndReplace(req, "이 게시글을 싫어합니다", String.format("../article/detail?id=%d&boardId=%d&memberId=%d", id, boardId,memberId));
		}


	}

}
