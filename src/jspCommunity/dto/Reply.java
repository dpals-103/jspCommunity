package jspCommunity.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Reply {
	private int id;
	private int articleId;
	private int memberId;
	private String body;
	private String regDate;
	private String updateDate;
	private int relId;
	private String relTypeCode;
	private String extra__nickName;
	
	public Reply(Map<String, Object> map) {
		this.id = (int) map.get("id");
		this.memberId = (int) map.get("memberId");
		this.articleId = (int) map.get("articleId");
		this.body = (String) map.get("body");
		this.relTypeCode= (String) map.get("relTypeCode");
		this.relId= (int) map.get("relId");
		this.regDate = (String) map.get("regDate");
		this.updateDate = (String) map.get("updateDate");
		
		
		if (map.containsKey("extra__nickName")) {
			this.extra__nickName = (String) map.get("extra__nickName");
		}
	}
}
