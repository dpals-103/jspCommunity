package jspCommunity.dto;

import java.util.Map;

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

	@Override
	public String toString() {
		return "Board [id=" + id + ", memberId=" + memberId + ", category=" + category + ", code=" + code + ", regDate="
				+ regDate + ", updateDate=" + updateDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
}
