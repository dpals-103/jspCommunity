package jspCommunity.dto;

import java.util.Map;

public class Board {

	public int id;
	public int memberId;
	public String category;
	public String code;
	public String regDate;
	public String updateDate;
	
	public Board(Map<String, Object> map) {
		this.id = (int)map.get("id");
		this.memberId = (int)map.get("memberId");
		this.category= (String)map.get("category");
		this.code= (String)map.get("code");
		this.regDate= (String)map.get("regDate");
		this.updateDate= (String)map.get("updateDate");
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", memberId=" + memberId + ", category=" + category + ", code=" + code + ", regDate="
				+ regDate + ", updateDate=" + updateDate + "]";
	}
}
