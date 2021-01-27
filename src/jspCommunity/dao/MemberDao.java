package jspCommunity.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jspCommunity.App;
import jspCommunity.container.Container;
import jspCommunity.dto.Member;
import jspCommunity.mysqlUtil.MysqlUtil;
import jspCommunity.mysqlUtil.SecSql;
import jspCommunity.service.MailService;
import jspCommunity.util.Util;

public class MemberDao {
	private static MemberDao memberDao;

	public MemberDao() {

		memberDao = Container.memberDao;
	}

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
		sql.append(",updateDate=now()");
		sql.append(",regDate=now();");

		int memberId = MysqlUtil.insert(sql);

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

	public Member getMemberByNameAndEmail(String name, String email) {

		SecSql sql = new SecSql();

		sql.append("select * from member");
		sql.append("where name = ?", name);
		sql.append("and email=?", email);
		sql.append("order by id desc");
		sql.append("limit 1");

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return null;
		}

		return new Member(map);
	}

	public int modify(Map<String, Object> args) {
		SecSql sql = new SecSql();

		sql.append("update member set");
		sql.append("updateDate = now()");
		
		boolean needToUpdate = false; 
		
		if(args.get("loginPw") != null) {
			needToUpdate = true;
			sql.append(",loginPw=?", args.get("loginPw"));
		}
		
		if(args.get("name") != null) {
			needToUpdate = true;
			sql.append(",name=?", args.get("name"));
		}
		
		if(args.get("nickName") != null) {
			needToUpdate = true;
			sql.append(",nickName=?", args.get("nickName"));
		}
		
		if(args.get("email") != null) {
			needToUpdate = true;
			sql.append(",email=?", args.get("email"));
		}
		
		if(args.get("cellPhone") != null) {
			needToUpdate = true;
			sql.append(",cellPhone=?", args.get("cellPhone"));
		}
		
		if(args.get("adminLevel") != null) {
			needToUpdate = true;
			sql.append(",adminLevel=?", args.get("adminLevel"));
		}
		
		if (needToUpdate == false) {
			return 0; 
		}
		
		sql.append("where id = ?", args.get("id")); 
		
		return MysqlUtil.update(sql); 
		
	}

}
