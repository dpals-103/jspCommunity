package jspCommunity.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Like{
	private int id;
	private int articleId;
	private int memberId;
	private int point;
	private String regDate;
	private String relTypeCode;
	
	public Like(Map<String, Object> map) {
		this.id = (int) map.get("id");
		this.memberId = (int) map.get("memberId");
		this.articleId = (int) map.get("articleId");
		this.point = (int) map.get("point");
		this.relTypeCode= (String) map.get("relTypeCode");
		this.regDate = (String) map.get("regDate");
	}

}
