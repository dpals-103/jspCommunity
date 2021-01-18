package jspCommunity.controller.usr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspCommunity.container.Container;
import jspCommunity.dto.Member;
import jspCommunity.service.MemberService;

public class UsrMemberController {
	private static MemberService memberService;

	public UsrMemberController() {
		memberService = Container.memberService;
	}

	public static String showJoin(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/join";
	}

	public static String doJoin(HttpServletRequest req, HttpServletResponse resp) {

		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPw");
		String name = req.getParameter("name");
		String nickName = req.getParameter("nickName");
		String email = req.getParameter("email");

		int memberId = memberService.join(loginId, loginPw, name, nickName,email);

		req.setAttribute("alertMsg", "가입되었습니다");
		req.setAttribute("replaceUrl", String.format("joinCheck?loginId=%s&memberId=%d",loginId,memberId));

		return "common/redirect";
	}

	public static String doAvailableLoginId(HttpServletRequest req, HttpServletResponse resp) {

		String loginId = req.getParameter("loginId");
		String joinedId = memberService.getMemberByLoginId(loginId);

		if (joinedId == null) {
			req.setAttribute("alertMsg", "사용가능한 아이디입니다.");
			req.setAttribute("historyBack", true);

			return "common/redirect";

		} else if (joinedId.equals(loginId)) {
			req.setAttribute("alertMsg", "이미 사용중인 아이디입니다.");
			req.setAttribute("historyBack", true);

			return "common/redirect";
		}
		return null;
	}

	public static String joinCheck(HttpServletRequest req, HttpServletResponse resp) {
		return "/usr/member/joinCheck";
	}
}
