package jspCommunity.controller.usr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspCommunity.container.Container;
import jspCommunity.controller.Controller;
import jspCommunity.service.ReplyService;
import jspCommunity.util.Util;

public class UsrReplyController extends Controller {
	private ReplyService replyService; 
	
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

		return msgAndReplace(req, "댓글이 삭제되었습니다",String.format("../article/detail?id=%d&boardId=%d", id, boardId));
	}


}
