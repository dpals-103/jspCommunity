package jspCommunity.controller.usr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String cellPhone = req.getParameter("cellPhone");

		Map<String, Object> joinArgs = new HashMap<>();
		joinArgs.put("loginId", loginId);
		joinArgs.put("loginPw", loginPw);
		joinArgs.put("name", name);
		joinArgs.put("nickName", nickName);
		joinArgs.put("email", email);
		joinArgs.put("cellPhone", cellPhone);

		String joinedId = memberService.getJoinedIdByLoginId(loginId);

		if (joinedId != null) {
			req.setAttribute("alertMsg", "이미 사용중인 아이디입니다.");
			req.setAttribute("historyBack", true);

			return "common/redirect";
		}
		

		int memberId = memberService.join(joinArgs);

		req.setAttribute("alertMsg", "가입되었습니다");
		req.setAttribute("replaceUrl", String.format("joinCheck?loginId=%s&memberId=%d", loginId, memberId));

		return "common/redirect";
	}

	
	public static String joinCheck(HttpServletRequest req, HttpServletResponse resp) {
		return "/usr/member/joinCheck";
	}

	public static String showLogin(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/login";
	}

	public static String doLogin(HttpServletRequest req, HttpServletResponse resp) {
		
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPw");
		
		Member member = memberService.getMemberByloginId(loginId); 
		
		if(member == null) {
			req.setAttribute("alertMsg", "일치하는 회원이 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
		} else if(member.getLoginPw() != loginPw) {
			req.setAttribute("alertMsg", "비밀번호가 일치하지 않습니다.");
			req.setAttribute("historyBack", true);
		}
		
		
		HttpSession session = req.getSession(); 
		session.setAttribute("loginedMemberId", member.getId());
		
		req.setAttribute("alertMsg", String.format("%s님 환영합니다.",member.getLoginId()));
		req.setAttribute("replaceUrl", "../home/main");
		
		return "common/redirect"; 
		
	}
}
