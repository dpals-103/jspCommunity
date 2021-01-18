package jspCommunity.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Article {

	private int id;
	private int boardId;
	private String boardCode;
	private String title;
	private String body;
	private int memberId;
	private int count;
	private String regDate;
	private String updateDate;
	private String extra__writer;
	private String extra__category;
	private String extra__boardCode;

	public Article(Map<String, Object> map) {
		this.id = (int) map.get("id");
		this.boardId = (int) map.get("boardId");
		this.boardCode = (String) map.get("boardCode");
		this.title = (String) map.get("title");
		this.body = (String) map.get("body");
		this.memberId = (int) map.get("memberId");
		this.count = (int) map.get("count");
		this.regDate = (String) map.get("regDate");
		this.updateDate = (String) map.get("updateDate");

		if (map.containsKey("extra__writer")) {
			this.extra__writer = (String) map.get("extra__writer");
		}

		if (map.containsKey("extra__category")) {
			this.extra__category = (String) map.get("extra__category");
		}

		if (map.containsKey("extra__boardCode")) {
			this.extra__category = (String) map.get("extra__boardCode");
		}

	}


}
