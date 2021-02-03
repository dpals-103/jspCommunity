package jspCommunity.controller.usr;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspCommunity.container.Container;
import jspCommunity.controller.Controller;
import jspCommunity.dto.Article;
import jspCommunity.dto.Member;
import jspCommunity.dto.ResultData;
import jspCommunity.service.MemberService;
import jspCommunity.util.Util;

public class UsrMemberController extends Controller {
	private static MemberService memberService;

	public UsrMemberController() {
		memberService = Container.memberService;
	}

	public String showJoin(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/join";
	}

	public String doJoin(HttpServletRequest req, HttpServletResponse resp) {

		String loginId = req.getParameter("loginId");

		if (Util.isEmpty(loginId)) {
			return msgAndBack(req, "아이디를 입력해주세요");
		}

		String loginPw = req.getParameter("loginPwReal");

		if (Util.isEmpty(loginPw)) {
			return msgAndBack(req, "비밀번호를 입력해주세요");
		}

		String name = req.getParameter("name");

		if (Util.isEmpty(name)) {
			return msgAndBack(req, "이름를 입력해주세요");
		}

		String nickName = req.getParameter("nickName");

		if (Util.isEmpty(nickName)) {
			return msgAndBack(req, "별명를 입력해주세요");
		}

		String email = req.getParameter("email");

		if (Util.isEmpty(email)) {
			return msgAndBack(req, "이메일을 입력해주세요");
		}

		String cellPhone = req.getParameter("cellPhone");

		if (Util.isEmpty(cellPhone)) {
			return msgAndBack(req, "연락처를 입력해주세요");
		}

		Map<String, Object> joinArgs = new HashMap<>();
		joinArgs.put("loginId", loginId);
		joinArgs.put("loginPw", loginPw);
		joinArgs.put("name", name);
		joinArgs.put("nickName", nickName);
		joinArgs.put("email", email);
		joinArgs.put("cellPhone", cellPhone);

		int memberId = memberService.join(joinArgs);

		return msgAndReplace(req, "가입되었습니다.", String.format("joinCheck?loginId=%s&memberId=%d", loginId, memberId));

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
		
		if (Util.isEmpty(loginId)) {
			return msgAndBack(req, "아이디를 입력해주세요");
		}
		
		String loginPw = req.getParameter("loginPwReal");
		
		if (Util.isEmpty(loginPw)) {
			return msgAndBack(req, "비밀번호를 입력해주세요");
		}

		Member member = memberService.getMemberByloginId(loginId);

		if (member == null) {
			return msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");

		} else if (member.getLoginPw().equals(loginPw) == false) {
			return msgAndBack(req, "비밀번호가 일치하지 않습니다");
		}

		session.setAttribute("loginedMemberId", member.getId());
		
		String replaceUrl = "../home/main"; 
		String alertMsg =  String.format("%s님 환영합니다.", member.getLoginId());
		
		
		/*90일 이상 사용한 비밀번호 변경 권유*/
		boolean isNeedToModifyPassword = memberService.isNeedToModifyPassword(member.getId()); 
		
		if(isNeedToModifyPassword) {
			alertMsg = String.format("%s님! 비밀번호 변경일로부터 90일이 지났습니다. 새로운 비밀번호로 변경해주세요", member.getNickName());
			replaceUrl = "../member/modify"; 
		}
		
	
		if(Util.isEmpty(req.getParameter("afterLoginUrl")) == false) {
			replaceUrl = req.getParameter("afterLoginUrl");
		}

		return msgAndReplace(req, alertMsg, replaceUrl);
	}

	public String doLogout(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession();

		session.removeAttribute("loginedMemberId");

		return msgAndReplace(req, "로그아웃 되었습니다", "../home/main");
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
		
		return json(req,new ResultData(resultCode, msg, "loginId", loginId));
	}
		

	public String showFindLoginId(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/findLoginId";
	}

	public String doFindLoginId(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession();

		String name = req.getParameter("name");
		
		if (Util.isEmpty(name)) {
			return msgAndBack(req, "이름을 입력해주세요");
		}
		
		String email = req.getParameter("email");
		
		if (Util.isEmpty(email)) {
			return msgAndBack(req, "이메일을 입력해주세요");
		}

		Member member = memberService.getMemberByNameAndEmail(name, email);

		if (member == null) {
			return msgAndBack(req, "일치하는 회원이 없습니다");
		}

		return msgAndReplace(req, String.format("아이디는 %s입니다", member.getLoginId()), "../member/login");

	}

	public String showFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/findLoginPw";
	}

	public String doFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		String loginId = req.getParameter("loginId");
		
		if (Util.isEmpty(loginId)) {
			return msgAndBack(req, "아이디를 입력해주세요");
		}
		
		String email = req.getParameter("email");
		
		if (Util.isEmpty(email)) {
			return msgAndBack(req, "이메일을 입력해주세요");
		}

		Member member = memberService.getMemberByloginId(loginId);

		if (member == null) {
			return msgAndBack(req, "일치하는 회원이 없습니다");
		}

		if (member.getEmail().equals(email) == false) {
			return msgAndBack(req, "등록되지 않은 이메일입니다.");
		}

		ResultData sendTempLoginPwToEmailRs = memberService.sendTempLoginPwToEmail(member);

		if (sendTempLoginPwToEmailRs.isFail()) {
			return msgAndBack(req, sendTempLoginPwToEmailRs.getMsg());
		}

		return msgAndReplace(req, sendTempLoginPwToEmailRs.getMsg(), "../member/login");

	}

	public String showModify(HttpServletRequest req, HttpServletResponse resp) {

		return "usr/member/modify";
	}

	public String doModify(HttpServletRequest req, HttpServletResponse resp) {

		String loginPw = req.getParameter("loginPwReal");

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		if (loginPw != null && loginPw.length() == 0) {
			loginPw = null;
		}

		String name = (String) req.getParameter("name");
		String nickName = (String) req.getParameter("nickName");
		
		if (Util.isEmpty(nickName)) {
			return msgAndBack(req, "활동명을 입력해주세요");
		}
		
		String email = (String) req.getParameter("email");
		
		if (Util.isEmpty(nickName)) {
			return msgAndBack(req, "이메일을 입력해주세요");
		}
		
		String cellPhone = (String) req.getParameter("cellPhone");
		
		if (Util.isEmpty(cellPhone)) {
			return msgAndBack(req, "연락처를 입력해주세요");
		}

		Map<String, Object> modifyParam = new HashMap<>();
		modifyParam.put("loginPw", loginPw);
		modifyParam.put("name", name);
		modifyParam.put("nickName", nickName);
		modifyParam.put("email", email);
		modifyParam.put("cellPhone", cellPhone);
		modifyParam.put("id", loginedMemberId);

		memberService.modify(modifyParam);

		if (loginPw != null) {
			memberService.setIsUsingTempPassword(loginedMemberId, false);
		}

		return msgAndReplace(req, "회원정보가 수정되었습니다", "../home/main");
	}

}
