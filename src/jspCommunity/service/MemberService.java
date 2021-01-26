package jspCommunity.service;

import java.util.List;
import java.util.Map;

import jspCommunity.container.Container;
import jspCommunity.dao.MemberDao;
import jspCommunity.dto.Member;

public class MemberService {
	private static MemberDao memberDao; 
	
	public MemberService(){
		memberDao = Container.memberDao; 
	}

	public static List<Member> getMembers() {
		return memberDao.getMembers(); 
	}

	public static Member getMember(int memberId) {
		return memberDao.getMember(memberId);
	}

	public String getJoinedIdByLoginId(String loginId) {
		return memberDao.getJoinedIdByLoginId(loginId); 
	}


	public int join(Map<String, Object> args) {
		return memberDao.join(args);
	}

	public Member getMemberByloginId(String loginId) {
		return memberDao.getMemberByloginId(loginId);
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		return memberDao.getMemberByNameAndEmail(name,email); 
	}


}
