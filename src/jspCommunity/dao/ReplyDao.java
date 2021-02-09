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
		sql.append(",memberId=?", memberId);
		sql.append(",articleId=?", id);
		sql.append(",body=?", body);
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

	public int getReplyCount(int articleId) {
		SecSql sql = new SecSql();

		sql.append("select count(*)");
		sql.append("from reply");
		sql.append("where articleId=?", articleId);

		return MysqlUtil.selectRowIntValue(sql);
	}

	public int getLikedReplyId(int memberId, int id) {

		SecSql sql = new SecSql();

		sql.append("select id");
		sql.append("from reply");
		sql.append("where memberId=?", memberId);
		sql.append("and relId=?", id);
		sql.append("and relTypeCode='replyLike'");

		return MysqlUtil.selectRowIntValue(sql);

	}

	public int getDislikedReplyId(int memberId, int id) {
		SecSql sql = new SecSql();

		sql.append("select id");
		sql.append("from reply");
		sql.append("where memberId=?", memberId);
		sql.append("and relId=?", id);
		sql.append("and relTypeCode='replyDislike'");

		return MysqlUtil.selectRowIntValue(sql);
	}

	public Object doLike(int memberId, int replyId) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `like`");
		sql.append("set relId=?", replyId);
		sql.append(",relTypeCode= 'replyLike' ");
		sql.append(",memberId=?", memberId);
		sql.append(",`point`=1");
		sql.append(",regDate=now()");

		return MysqlUtil.insert(sql);

	}

	public Object doCancleDislike(int memberId, int replyId) {

		SecSql sql = new SecSql();

		sql.append("delete from `like`");
		sql.append("where relId = ?", replyId);
		sql.append("and memberId= ?", memberId);
		sql.append("and relTypeCode='replyDislike'");

		return MysqlUtil.delete(sql);
	}

	public Object doCancleLike(int memberId, int replyId) {
		SecSql sql = new SecSql();

		sql.append("delete from `like`");
		sql.append("where relId = ?", replyId);
		sql.append("and memberId= ?", memberId);
		sql.append("and relTypeCode='replyLike'");

		return MysqlUtil.delete(sql);
	}

	public Object doDisLike(int memberId, int replyId) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `like`");
		sql.append("set relId=?", replyId);
		sql.append(",relTypeCode=replyDislike");
		sql.append(",memberId=?", memberId);
		sql.append(",`point`=1");
		sql.append(",regDate=now()");

		return MysqlUtil.insert(sql);
	}

	public int getLikeReplyCount(int replyId) {
		SecSql sql = new SecSql();

		sql.append("select sum(`point`)");
		sql.append("from `like`");
		sql.append("where relId=?", replyId);
		
		return MysqlUtil.selectRowIntValue(sql);
		
	}

}
