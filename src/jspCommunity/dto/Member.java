package jspCommunity.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Member {
	
	private int id;
	private String loginId;
	private String loginPw;
	private String name;
	private String nickName;
	private String email;
	private String regDate;
	private int adminLevel; 

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

}
