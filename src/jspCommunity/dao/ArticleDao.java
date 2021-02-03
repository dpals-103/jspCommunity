package jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jspCommunity.dto.Article;
import jspCommunity.dto.ArticleLikes;
import jspCommunity.dto.Board;
import jspCommunity.mysqlUtil.MysqlUtil;
import jspCommunity.mysqlUtil.SecSql;

public class ArticleDao {

	public List<Article> getArticles(int boardId) {

		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();

		sql.append("select A.*");
		sql.append(",M.name as extra__writer");
		sql.append(",B.category as extra__category");
		sql.append("from article as A");
		sql.append("inner join `member` as M");
		sql.append("on A.memberId = M.id");
		sql.append("inner join board as B");
		sql.append("on A.boardId = B.id");

		if (boardId != 0) {
			sql.append("where A.boardId=?", boardId);
		}

		sql.append("order by A.id desc;");

		List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articleMap : articleMapList) {
			articles.add(new Article(articleMap));
		}

		return articles;
	}

	public Article getArticle(int id) {

		SecSql sql = new SecSql();

		sql.append("select A.*");
		sql.append(",M.name as extra__writer");
		sql.append(",B.category as extra__category");
		sql.append(",B.code as extra__boardCode");
		sql.append("from article as A");
		sql.append("inner join `member` as M");
		sql.append("on A.memberId = M.id");
		sql.append("inner join board as B");
		sql.append("on A.boardId = B.id");

		if (id != 0) {
			sql.append("where A.id=?", id);
		}

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return null;
		}

		return new Article(map);
	}

	public Board getBoard(int boardId) {

		SecSql sql = new SecSql();

		sql.append("select * from board where id = ?", boardId);

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return null;
		}

		return new Board(map);

	}

	public int write(int memberId, int boardId, String title, String body) {

		SecSql sql = new SecSql();

		sql.append("INSERT INTO article");
		sql.append("SET boardId=?", boardId);
		sql.append(",title=?", title);
		sql.append(",body=?", body);
		sql.append(",memberId=?", memberId);
		sql.append(",regDate=now()");
		sql.append(",updateDate=now()");

		int articleId = MysqlUtil.insert(sql);

		return articleId;
	}

	public int modify(int memberId, int boardId, int id, String title, String body) {

		SecSql sql = new SecSql();

		sql.append("update article");
		sql.append("set title=?", title);
		sql.append(",body=?", body);
		sql.append(",updateDate=now()");
		sql.append("where memberId=?", memberId);
		sql.append("and boardId=?", boardId);
		sql.append("and id=?", id);

		int m = MysqlUtil.update(sql);

		return m;

	}

	public int delete(int memberId, int boardId, int id) {

		SecSql sql = new SecSql();

		sql.append("delete from article");
		sql.append("where memberId=?", memberId);
		sql.append("and boardId=?", boardId);
		sql.append("and id=?", id);

		int d = MysqlUtil.delete(sql);

		return d;
	}

	public List<Board> getBoards() {

		List<Board> boards = new ArrayList<>();

		SecSql sql = new SecSql();

		sql.append("select * ");
		sql.append("from board");

		List<Map<String, Object>> boardMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> boardMap : boardMapList) {
			boards.add(new Board(boardMap));
		}

		return boards;
	}

	public int getArticlesCountByBoardId(int boardId) {

		SecSql sql = new SecSql();

		sql.append("select count(*)");
		sql.append("from article");
		sql.append("where boardId=?", boardId);

		return MysqlUtil.selectRowIntValue(sql);
	}

	public int getArticlesCountBySearch(int boardId, String searchKeyword, String searchKeywordType) {

		SecSql sql = new SecSql();

		sql.append("select count(*)");
		sql.append("from article");
		sql.append("where 1");

		if (boardId != 0) {
			sql.append("and boardId =?", boardId);
		}

		if (searchKeyword != null) {
			if (searchKeywordType == null || searchKeywordType.equals("title")) {
				sql.append("and title like concat('%', ? '%')", searchKeyword);
			} else if (searchKeywordType == null || searchKeywordType.equals("body")) {
				sql.append("and body like concat('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("titleAndBody")) {
				sql.append("and (title like concat('%', ? '%') or body like concat('%', ? '%'))", searchKeyword,
						searchKeywordType);
			}
		}

		return MysqlUtil.selectRowIntValue(sql);
	}

	public List<Article> getArticlesBySearch(int boardId, int limitStart, int limitCount, String searchKeyword,
			String searchKeywordType) {

		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();

		sql.append("select A.*");
		sql.append(",M.name as extra__writer");
		sql.append(",B.category as extra__category");
		sql.append("from article as A");
		sql.append("inner join `member` as M");
		sql.append("on A.memberId = M.id");
		sql.append("inner join board as B");
		sql.append("on A.boardId = B.id");

		if (boardId != 0) {
			sql.append("where A.boardId=?", boardId);
		}

		if (searchKeyword != null) {
			if (searchKeywordType == null || searchKeywordType.equals("title")) {
				sql.append("and title like concat('%', ? '%')", searchKeyword);
			} else if (searchKeywordType == null || searchKeywordType.equals("body")) {
				sql.append("and body like concat('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("titleAndBody")) {
				sql.append("and (title like concat('%', ? '%') or body like concat('%', ? '%'))", searchKeyword,
						searchKeywordType);
			}
		}

		sql.append("order by A.id desc");

		if (limitCount != -1) {
			sql.append("limit ?, ?", limitStart, limitCount);
		}

		List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articleMap : articleMapList) {
			articles.add(new Article(articleMap));
		}

		return articles;
	}

	public Object increaseCount(int boardId, int id) {
		SecSql sql = new SecSql();

		sql.append("update article");
		sql.append("set count = count + 1");
		sql.append("where boardId = ?", boardId);
		sql.append("and id = ?", id);

		return MysqlUtil.update(sql);
	}

	public ArticleLikes getLikedArticle(int memberId, int id) {
		SecSql sql = new SecSql();

		sql.append("select *");
		sql.append("from articleLikes");
		sql.append("where id = ?", id);
		sql.append("and memberId= ?", memberId);
		sql.append("and point= 1");

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return null;
		}

		return new ArticleLikes(map);

	}

	public ArticleLikes getDislikedArticle(int memberId, int id) {

		SecSql sql = new SecSql();

		sql.append("select *");
		sql.append("from articleLikes");
		sql.append("where id = ?", id);
		sql.append("and memberId= ?", memberId);
		sql.append("and point= -1");

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return null;
		}

		return new ArticleLikes(map);
	}

	public Object cancelDislike(int id, int memberId) {

		SecSql sql = new SecSql();

		sql.append("delete");
		sql.append("from articleLikes");
		sql.append("where id = ?", id);
		sql.append("and memberId= ?", memberId);
		sql.append("and point= -1");

		return MysqlUtil.delete(sql);
	}

	public Object doLike(int memberId, int id) {

		SecSql sql = new SecSql();

		sql.append("INSERT INTO articleLikes");
		sql.append("set articleId = ?", id);
		sql.append(",memberId= ?", memberId);
		sql.append(",point= 1");
		sql.append(",regDate=now()");

		return MysqlUtil.insert(sql);
	}

	public int getLikeCount(int id) {

		SecSql sql = new SecSql();

		sql.append("select *");
		sql.append("from articleLikes");
		sql.append("where articleId = ?", id);
		sql.append("and point=1");

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return 0;
		} else {

			sql.append("select sum(point)");
			sql.append("from articleLikes");
			sql.append("where articleId = ?", id);
			sql.append("where point=1");

			int likeCount = MysqlUtil.selectRowIntValue(sql);

			if (likeCount == -1) {
				return likeCount = 0;
			}

			return likeCount;
		}
	}
}
