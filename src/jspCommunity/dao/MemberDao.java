package jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jspCommunity.dto.Article;
import jspCommunity.dto.Board;
import jspCommunity.dto.Member;
import jspCommunity.mysqlUtil.MysqlUtil;
import jspCommunity.mysqlUtil.SecSql;

public class MemberDao {

	public List<Member> getMembers() {

		List<Member> members = new ArrayList<>();

		SecSql sql = new SecSql();

		sql.append("select *");
		sql.append("from member");

		List<Map<String, Object>> memberMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> memberMap : memberMapList) {
			members.add(new Member(memberMap));
		}

		return members;
	}

	public Member getMember(int memberId) {

		SecSql sql = new SecSql();

		sql.append("select * from member where id = ?", memberId);

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return null;
		}

		return new Member(map);
	}

	public String getJoinedIdByLoginId(String loginId) {
		SecSql sql = new SecSql();

		sql.append("select loginId from member where loginId = ?", loginId);
		String joinedId = MysqlUtil.selectRowStringValue(sql);

		if (joinedId.isEmpty()) {
			return null;
		} else if (joinedId.equals(loginId)) {
			return joinedId;
		}
		return null;
	}
	

	public int join(Map<String, Object> args) {
		
		SecSql sql = new SecSql();

		sql.append("INSERT INTO member set");
		sql.append("loginId=?", args.get("loginId"));
		sql.append(",loginPw=?", args.get("loginPw"));
		sql.append(",name=?", args.get("name"));
		sql.append(",nickName=?", args.get("nickName"));
		sql.append(",email=?", args.get("email"));
		sql.append(",cellPhone=?", args.get("cellPhone"));
		sql.append(",regDate=now();");
		
		int memberId= MysqlUtil.insert(sql);
		
		return memberId; 
	}

	public Member getMemberByloginId(String loginId) {
		
		SecSql sql = new SecSql();

		sql.append("select * from member where loginId = ?", loginId);

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return null;
		}

		return new Member(map);
	}


}
