package jspCommunity.service;

import java.util.List;

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

	public String getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId); 
	}

	public int join(String loginId, String loginPw, String name, String nickName, String email) {
		return memberDao.join( loginId,  loginPw,  name,  nickName,  email);
	}
}
