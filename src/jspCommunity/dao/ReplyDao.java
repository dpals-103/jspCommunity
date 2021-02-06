package jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jspCommunity.dto.Article;
import jspCommunity.dto.Reply;
import jspCommunity.mysqlUtil.MysqlUtil;
import jspCommunity.mysqlUtil.SecSql;

public class ReplyDao {

	public int write(int memberId, int id, String body) {
		
		SecSql sql = new SecSql(); 
		
		sql.append("INSERT INTO reply");
		sql.append("set relTypeCode = 'reply' ");
		sql.append(",relId=1");
		sql.append(",memberId=?",memberId);
		sql.append(",articleId=?",id);
		sql.append(",body=?",body);
		sql.append(",regDate=now()");
		sql.append(",updateDate=now()");
		
		return MysqlUtil.insert(sql);
	}

	public List<Reply> getReplies(int id) {
		
		List<Reply> replies = new ArrayList<>();

		SecSql sql = new SecSql();

		sql.append("select R.*");
		sql.append(",M.nickName as extra__nickName");
		sql.append("from reply as R");
		sql.append("inner join `member` as M");
		sql.append("on R.memberId = M.id");

		if (id != 0) {
			sql.append("where R.articleId=?", id);
		}

		sql.append("order by R.id desc;");

		List<Map<String, Object>> replyMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> replyMap : replyMapList) {
			replies.add(new Reply(replyMap));
		}

		return replies;
		
	}

	public int delete(int memberId, int replyId) {
		
		SecSql sql = new SecSql();

		sql.append("delete from reply");
		sql.append("where memberId=?", memberId);
		sql.append("and id=?", replyId);
		
		return MysqlUtil.delete(sql);
	}

}
