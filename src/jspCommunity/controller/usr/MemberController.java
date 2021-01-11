package jspCommunity.controller.usr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspCommunity.container.Container;
import jspCommunity.dto.Member;
import jspCommunity.service.MemberService;

public class MemberController {
	private MemberService memberService; 
	
	public MemberController() {
		memberService = Container.memberService;
	}

	public static String showList(HttpServletRequest req, HttpServletResponse resp) {
	
		List<Member> members = MemberService.getMembers();
		
		req.setAttribute("members", members);
		
		return "usr/member/list"; 
	}
}
