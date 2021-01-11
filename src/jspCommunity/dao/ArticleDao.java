package jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jspCommunity.dto.Article;
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
		
		if(map.isEmpty()) {
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

}
