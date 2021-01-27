package jspCommunity.controller.usr;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspCommunity.container.Container;
import jspCommunity.dto.Member;
import jspCommunity.dto.ResultData;
import jspCommunity.service.MemberService;

public class UsrMemberController {
	private static MemberService memberService;

	public UsrMemberController() {
		memberService = Container.memberService;
	}

	public String showJoin(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/join";
	}

	public String doJoin(HttpServletRequest req, HttpServletResponse resp) {

		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");
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

		int memberId = memberService.join(joinArgs);

		req.setAttribute("alertMsg", "가입되었습니다");
		req.setAttribute("replaceUrl", String.format("joinCheck?loginId=%s&memberId=%d", loginId, memberId));

		return "common/redirect";
	}

	public String joinCheck(HttpServletRequest req, HttpServletResponse resp) {
		return "/usr/member/joinCheck";
	}

	public String showLogin(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/login";
	}

	public String doLogin(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession();

		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");

		Member member = memberService.getMemberByloginId(loginId);

		if (member == null) {
			req.setAttribute("alertMsg", "일치하는 회원이 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";

		} else if (member.getLoginPw().equals(loginPw) == false) {
			req.setAttribute("alertMsg", "비밀번호가 일치하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		session.setAttribute("loginedMemberId", member.getId());

		req.setAttribute("alertMsg", String.format("%s님 환영합니다.", member.getLoginId()));

		req.setAttribute("replaceUrl", "../home/main");

		return "common/redirect";
	}

	public String doLogout(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession();

		session.removeAttribute("loginedMemberId");

		req.setAttribute("alertMsg", "로그아웃 되었습니다");
		req.setAttribute("replaceUrl", "../home/main");

		return "common/redirect";
	}

	public String getLoginIdDup(HttpServletRequest req, HttpServletResponse resp) {

		String loginId = req.getParameter("loginId");

		Member member = memberService.getMemberByloginId(loginId);

		String resultCode = null;
		String msg = null;

		String data = "";

		if (member != null) {
			resultCode = "F-1";
			msg = "이미 사용중인 아이디 입니다.";
		} else {
			resultCode = "S-1";
			msg = "사용 가능한 아이디입니다.";
		}

		req.setAttribute("data", new ResultData(resultCode,msg, "loginId", loginId));

		return "common/json";
	}

	public String showFindLoginId(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") != null) {
			req.setAttribute("alertMsg", "로그아웃 해주세요");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		return "usr/member/findLoginId";

	}

	public String doFindLoginId(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") != null) {
			req.setAttribute("alertMsg", "로그아웃 해주세요");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		String name = req.getParameter("name");
		String email = req.getParameter("email");

		Member member = memberService.getMemberByNameAndEmail(name, email);

		if (member == null) {
			req.setAttribute("alertMsg", "일치하는 회원이 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		req.setAttribute("alertMsg", String.format("아이디는 %s입니다", member.getLoginId()));
		req.setAttribute("replaceUrl", "../member/login");
		return "common/redirect";

	}

	public String showFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") != null) {
			req.setAttribute("alertMsg", "로그아웃 해주세요");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		return "usr/member/findLoginPw";

	}

	public String doFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") != null) {
			req.setAttribute("alertMsg", "로그아웃 해주세요");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		String loginId = req.getParameter("loginId");
		String email = req.getParameter("email");

		Member member = memberService.getMemberByloginId(loginId);

		if (member == null) {
			req.setAttribute("alertMsg", "일치하는 회원이 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		
		
		if (member.getEmail().equals(email) == false) {
			req.setAttribute("alertMsg", "등록되지 않은 이메일 입니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		ResultData sendTempLoginPwToEmailRs = memberService.sendTempLoginPwToEmail(member);
		
		if(sendTempLoginPwToEmailRs.isFail()){
			req.setAttribute("alertMsg", sendTempLoginPwToEmailRs.getMsg());
			req.setAttribute("historyBack", true);
			return "common/redirect";	
		}
			
		
		req.setAttribute("alertMsg", sendTempLoginPwToEmailRs.getMsg());
		req.setAttribute("replaceUrl", "../member/login");
		return "common/redirect";

	}

}
