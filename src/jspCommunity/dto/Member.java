package jspCommunity.dto;

import java.util.Map;

public class Member {
	
	public int id;
	public String loginId;
	public String loginPw;
	public String name;
	public String nickName;
	public String email;
	public String regDate;
	public int adminLevel; 

	public Member(Map<String, Object> map) {
		this.id = (int)map.get("id");
		this.loginId = (String)map.get("loginId");
		this.loginPw = (String)map.get("loginPw");
		this.name = (String)map.get("name");
		this.nickName = (String)map.get("nickName");
		this.email = (String)map.get("email");
		this.regDate = (String)map.get("regDate");
		this.adminLevel = (int)map.get("adminLevel");
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", loginId=" + loginId + ", loginPw=" + loginPw + ", name=" + name + ", nickName="
				+ nickName + ", email=" + email + ", regDate=" + regDate + ", adminLevel=" + adminLevel + "]";
	}

}
