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

	public String getMemberByLoginId(String loginId) {
		SecSql sql = new SecSql();

		sql.append("select loginId from member where loginId = ?", loginId);
		String joinedId = MysqlUtil.selectRowStringValue(sql);

		if (joinedId.isBlank()) {
			return null;
		} else if (joinedId.equals(loginId)) {
			return joinedId;
		}
		return null;
	}

	public int join(String loginId, String loginPw, String name, String nickName, String email) {
		
		SecSql sql = new SecSql();

		sql.append("INSERT INTO member set");
		sql.append("loginId=?", loginId);
		sql.append(",loginPw=?", loginPw);
		sql.append(",name=?", name);
		sql.append(",nickName=?", nickName);
		sql.append(",email=?", email);
		sql.append(",regDate=now();");
		
		int memberId= MysqlUtil.insert(sql);
		
		return memberId; 
	}
}
