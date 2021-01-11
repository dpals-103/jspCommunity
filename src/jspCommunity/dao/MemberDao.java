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
}
