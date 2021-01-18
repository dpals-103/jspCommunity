package jspCommunity.controller.usr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspCommunity.container.Container;
import jspCommunity.dto.Member;
import jspCommunity.service.MemberService;

public class AdmMemberController {
	private static MemberService memberService; 
	
	public AdmMemberController() {
		memberService = Container.memberService;
	}

	public static String showList(HttpServletRequest req, HttpServletResponse resp) {
	
		List<Member> members = MemberService.getMembers();
		int adminLevel = Integer.parseInt(req.getParameter("adminLevel"));
		
		if(adminLevel != 0) {
			req.setAttribute("alertMsg", "관리자만 열람가능합니다");
			req.setAttribute("historyBack", true);

			return "common/redirect";
		}
		
		req.setAttribute("members", members);
		req.setAttribute("adminLevel", adminLevel);
		
		return "adm/member/list"; 
	}


}