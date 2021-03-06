package jspCommunity.service;

import java.util.List;

import jspCommunity.container.Container;
import jspCommunity.dao.ArticleDao;
import jspCommunity.dto.Article;
import jspCommunity.dto.Board;
import jspCommunity.dto.Like;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public List<Article> getArticles(int boardId) {
		return articleDao.getArticles(boardId);
	}

	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}

	public Board getBoard(int boardId) {
		return articleDao.getBoard(boardId);
	}

	public int write(int memberId, int boardId, String title, String body) {
		return articleDao.write(memberId, boardId, title, body);
	}

	public int modify(int memberId, int boardId, int id, String title, String body) {
		return articleDao.modify(memberId, boardId, id, title, body);
	}

	public int delete(int memberId, int boardId, int id) {
		return articleDao.delete(memberId, boardId, id);
	}

	public List<Board> getBoards() {
		return articleDao.getBoards(); 
	}

	public int getArticlesCountByBoardId(int boardId) {
		return articleDao.getArticlesCountByBoardId(boardId); 
	}

	public int getArticlesCountBySearch(int boardId, String searchKeyword, String searchKeywordType) {
		return articleDao.getArticlesCountBySearch(boardId, searchKeyword, searchKeywordType); 
	}

	public List<Article> getArticlesBySearch(int boardId,int limitStart, int limitCount , String searchKeyword, String searchKeywordType) {
		return articleDao.getArticlesBySearch(boardId, limitStart, limitCount, searchKeyword, searchKeywordType);
	}

	public Object increaseCount(int boardId, int id) {
		return articleDao.increaseCount(boardId, id); 
	}

	
}
