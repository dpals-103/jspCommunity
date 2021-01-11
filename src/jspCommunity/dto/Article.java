package jspCommunity.dto;

import java.util.Map;

public class Article {

	public int id;
	public int boardId;
	public String boardCode;
	public String title;
	public String body;
	public int memberId;
	public int count;
	public String regDate;
	public String updateDate;
	public String extra__writer;
	public String extra__category;
	public String extra__boardCode;

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

	@Override
	public String toString() {
		return "Article [id=" + id + ", boardId=" + boardId + ", boardCode=" + boardCode + ", title=" + title
				+ ", body=" + body + ", memberId=" + memberId + ", count=" + count + ", regDate=" + regDate
				+ ", updateDate=" + updateDate + ", extra__writer=" + extra__writer + ", extra__category="
				+ extra__category + ", extra__boardCode=" + extra__boardCode + "]";
	}

}
