package jspCommunity.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Board {

	private int id;
	private int memberId;
	private String category;
	private String code;
	private String regDate;
	private String updateDate;

	public Board(Map<String, Object> map) {
		this.id = (int) map.get("id");
		this.memberId = (int) map.get("memberId");
		this.category = (String) map.get("category");
		this.code = (String) map.get("code");
		this.regDate = (String) map.get("regDate");
		this.updateDate = (String) map.get("updateDate");
	}

	
}
