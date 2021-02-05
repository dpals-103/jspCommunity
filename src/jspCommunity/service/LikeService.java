package jspCommunity.service;

import jspCommunity.container.Container;
import jspCommunity.dao.LikeDao;
import jspCommunity.dto.Like;

public class LikeService {
	private LikeDao likeDao;
	
	public LikeService() {
		likeDao = Container.likeDao; 
	}
	
	public Like getLikedArticle(int memberId, int id) {
		return likeDao.getLikedArticle(memberId, id);
	}

	public Like getDislikedArticle(int memberId, int id) {
		return likeDao.getDislikedArticle(memberId, id);
	}

	
	public Object doLike(int memberId, int id) {
		return likeDao.doLike(memberId,id);
	}

	public int getLikeCount(int id) {
		return likeDao.getLikeCount(id); 
	}

	public Object doCanclelike(int memberId, int id) {
		return likeDao.doCanclelike(memberId,id);
	}

	public Object doCancleDislike(int memberId, int id) {
		return likeDao.doCancleDislike(memberId, id);
		
	}

	public Object doDislike(int memberId, int id) {
		return likeDao.doDislike(memberId,id);
	}

	public int getDislikeCount(int id) {
		return likeDao.getDislikeCount(id); 
	}

}
