package jspCommunity.dto;

import java.util.Map;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getExtra__writer() {
		return extra__writer;
	}

	public void setExtra__writer(String extra__writer) {
		this.extra__writer = extra__writer;
	}

	public String getExtra__category() {
		return extra__category;
	}

	public void setExtra__category(String extra__category) {
		this.extra__category = extra__category;
	}

	public String getExtra__boardCode() {
		return extra__boardCode;
	}

	public void setExtra__boardCode(String extra__boardCode) {
		this.extra__boardCode = extra__boardCode;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", boardId=" + boardId + ", boardCode=" + boardCode + ", title=" + title
				+ ", body=" + body + ", memberId=" + memberId + ", count=" + count + ", regDate=" + regDate
				+ ", updateDate=" + updateDate + ", extra__writer=" + extra__writer + ", extra__category="
				+ extra__category + ", extra__boardCode=" + extra__boardCode + "]";
	}

}
