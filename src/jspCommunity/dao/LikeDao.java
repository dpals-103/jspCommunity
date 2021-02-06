package jspCommunity.dao;

import java.util.List;
import java.util.Map;

import jspCommunity.dto.Like;
import jspCommunity.mysqlUtil.MysqlUtil;
import jspCommunity.mysqlUtil.SecSql;

public class LikeDao {
	
	
	public Like getLikedArticle(int memberId, int id) {
		SecSql sql = new SecSql();

		sql.append("select *");
		sql.append("from `like`");
		sql.append("where articleId = ?", id);
		sql.append("and memberId= ?", memberId);
		sql.append("and relTypeCode='like'");

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return null;
			
		}

		return new Like(map);

	}

	public Like getDislikedArticle(int memberId, int id) {

		SecSql sql = new SecSql();

		sql.append("select *");
		sql.append("from `like`");
		sql.append("where articleId = ?", id);
		sql.append("and memberId= ?", memberId);
		sql.append("and relTypeCode='dislike'");

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return null;
		}

		return new Like(map);
	}


	public Object doLike(int memberId, int id) {

		SecSql sql = new SecSql();

		sql.append("INSERT INTO `like`");
		sql.append("set articleId = ?", id);
		sql.append(",memberId= ?", memberId);
		sql.append(",relTypeCode='like'");
		sql.append(",`point` = 1");
		sql.append(",regDate=now()");

		return MysqlUtil.insert(sql);
	}

	public int getLikeCount(int id) {

		SecSql sql = new SecSql();

		sql.append("select sum(point)");
		sql.append("from `like`");
		sql.append("where articleId = ?", id);
		sql.append("and relTypeCode='like' ");
		sql.append("and point=1");

		List<Map<String, Object>> map = MysqlUtil.selectRows(sql);

		if (map.isEmpty()) {
			return 0;
		}

		SecSql count = new SecSql();

		count.append("select sum(point)");
		count.append("from `like`");
		count.append("where articleId = ?", id);
		count.append("and relTypeCode= 'like'");
		count.append("and `point`=1");

		int likeCount = MysqlUtil.selectRowIntValue(count);
		
		
		if (likeCount == 0) {
			return 0; 
		}
		else if (likeCount < 0) {
			return 0;
		}
		
		return likeCount;
	}

	public Object doCanclelike(int memberId, int id) {
		
		SecSql sql = new SecSql();

		sql.append("delete from `like`");
		sql.append("where articleId = ?", id);
		sql.append("and memberId= ?", memberId);
		sql.append("and relTypeCode='like'");

		return MysqlUtil.delete(sql);
		
	}

	public Object doCancleDislike(int memberId, int id) {
		SecSql sql = new SecSql();

		sql.append("delete from `like`");
		sql.append("where articleId = ?", id);
		sql.append("and memberId= ?", memberId);
		sql.append("and relTypeCode='dislike'");

		return MysqlUtil.delete(sql);
	}

	public Object doDislike(int memberId, int id) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `like`");
		sql.append("set articleId = ?", id);
		sql.append(",memberId= ?", memberId);
		sql.append(",relTypeCode='dislike'");
		sql.append(",`point` = 1");
		sql.append(",regDate=now()");

		return MysqlUtil.insert(sql);
	}

	public int getDislikeCount(int id) {
		
		SecSql sql = new SecSql();

		sql.append("select sum(point)");
		sql.append("from `like`");
		sql.append("where articleId = ?", id);
		sql.append("and relTypeCode='dislike' ");
		sql.append("and point=1");

		List<Map<String, Object>> map = MysqlUtil.selectRows(sql);

		if (map.isEmpty()) {
			return 0;
		}

		SecSql count = new SecSql();

		count.append("select sum(point)");
		count.append("from `like`");
		count.append("where articleId = ?", id);
		count.append("and relTypeCode= 'dislike'");
		count.append("and `point`=1");

		int dislikeCount = MysqlUtil.selectRowIntValue(count);
		
		
		if (dislikeCount == 0) {
			return 0; 
		}
		else if (dislikeCount < 0) {
			return 0;
		}
		
		return dislikeCount;
	}
}
