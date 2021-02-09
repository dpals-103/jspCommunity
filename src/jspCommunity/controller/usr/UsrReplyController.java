package jspCommunity.controller.usr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspCommunity.container.Container;
import jspCommunity.controller.Controller;
import jspCommunity.dto.Like;
import jspCommunity.service.LikeService;
import jspCommunity.service.ReplyService;
import jspCommunity.util.Util;

public class UsrReplyController extends Controller {
	private ReplyService replyService;
	private LikeService likeService;

	public UsrReplyController() {
		replyService = Container.replyService;

	}

	public String doReply(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession();

		int memberId = (int) session.getAttribute("loginedMemberId");
		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);
		int id = Util.getAsInt(req.getParameter("id"), 0);

		String body = req.getParameter("body");

		if (Util.isEmpty(body)) {
			return msgAndBack(req, "내용을 입력해주세요");
		}

		int replyId = replyService.write(memberId, id, body);

		return msgAndReplace(req, "작성되었습니다", String.format("../article/detail?id=%d&boardId=%d", id, boardId));
	}

	public String doDelete(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession();

		int memberId = (int) session.getAttribute("loginedMemberId");
		int replyId = Util.getAsInt(req.getParameter("replyId"), 0);
		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);
		int id = Util.getAsInt(req.getParameter("id"), 0);

		replyService.delete(memberId, replyId);

		return msgAndReplace(req, "댓글이 삭제되었습니다", String.format("../article/detail?id=%d&boardId=%d", id, boardId));
	}

	public String doLike(HttpServletRequest req, HttpServletResponse resp) {

		int replyId = Util.getAsInt(req.getParameter("replyId"), 0);
		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);
		int memberId = (int) req.getAttribute("loginedMemberId");
		int id = Util.getAsInt(req.getParameter("id"), 0);

		int likedReplyId = replyService.getLikedReplyId(memberId, replyId);
		int dislikedReplyId = replyService.getDislikedReplyId(memberId, replyId);

		boolean likedReply = false;
		boolean dislikedReply = false;

		if (dislikedReplyId != 0) {
			replyService.doCancleDislike(memberId, replyId);
			replyService.doLike(memberId, replyId);

			likedReply = true;
			dislikedReply = false;

			req.setAttribute("dislikedReply", dislikedReply);
			req.setAttribute("likedReply", likedReply);

			return msgAndReplace(req, "이 댓글을 좋아합니다",
					String.format("../article/detail?id=%d&boardId=%d&memberId=%d", id, boardId, memberId));

		} else if (likedReplyId != 0) {
			replyService.doCanclelike(memberId, replyId);

			likedReply = false;

			req.setAttribute("likedReply", likedReply);
			return msgAndReplace(req, "좋아요를 취소합니다",
					String.format("../article/detail?id=%d&boardId=%d&memberId=%d", id, boardId, memberId));

		} else {
			replyService.doLike(memberId, replyId);
			likedReply = true;
			req.setAttribute("likedReply", likedReply);
			return msgAndReplace(req, "이 댓글을 좋아합니다",
					String.format("../article/detail?id=%d&boardId=%d&memberId=%d", id, boardId, memberId));
		}

	}

	public String doDislike(HttpServletRequest req, HttpServletResponse resp) {
		int replyId = Util.getAsInt(req.getParameter("replyId"), 0);
		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);
		int memberId = (int) req.getAttribute("loginedMemberId");
		int id = Util.getAsInt(req.getParameter("id"), 0);

		int likedReplyId = replyService.getLikedReplyId(memberId, replyId);
		int dislikedReplyId = replyService.getDislikedReplyId(memberId, replyId);

		boolean likedReply = false;
		boolean dislikedReply = false;
		int likeReplyCount = 0;

		if (likedReplyId != 0) {
			replyService.doCanclelike(memberId, replyId);
			replyService.doDislike(memberId, replyId);
			
			likeReplyCount = replyService.getLikeReplyCount(replyId); 
			likedReply = false;
			dislikedReply = true;

			
			req.setAttribute("likeReplyCount", likeReplyCount);
			req.setAttribute("likedReply", likedReply);
			req.setAttribute("dislikedReply", dislikedReply);

			return msgAndReplace(req, "이 댓글을 맘에 들어하지 않습니다.",
					String.format("../article/detail?id=%d&boardId=%d&memberId=%d", id, boardId, memberId));

		} else if (dislikedReplyId != 0) {
			replyService.doCanclelike(memberId, replyId);

			dislikedReply = false;
			req.setAttribute("dislikedReply", dislikedReply);
			
			return msgAndReplace(req, "싫어요를 취소합니다",
					String.format("../article/detail?id=%d&boardId=%d&memberId=%d", id, boardId, memberId));
		} else {
			replyService.doDislike(memberId, replyId);

			dislikedReply = true;
			req.setAttribute("dislikedReply", dislikedReply);

			return msgAndReplace(req, "이 댓글을 맘에 들어하지 않습니다.",
					String.format("../article/detail?id=%d&boardId=%d&memberId=%d", id, boardId, memberId));
		}
	}
	

}
