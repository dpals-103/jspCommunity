package jspCommunity.service;

import java.util.List;

import jspCommunity.container.Container;
import jspCommunity.dao.ReplyDao;
import jspCommunity.dto.Reply;

public class ReplyService {
	private static ReplyDao replyDao;

	public ReplyService() {
		replyDao = Container.replyDao;
	}

	public int write(int memberId, int id, String body) {
		return replyDao.write(memberId, id, body);
	}

	public static List<Reply> getReplies(int id) {
		return replyDao.getReplies(id);
	}

	public int delete(int memberId, int replyId) {
		return replyDao.delete(memberId, replyId);

	}

	public int getReplyCount(int articleId) {
		return replyDao.getReplyCount(articleId);
	}

	public Object doLike(int memberId, int replyId) {
		return replyDao.doLike(memberId, replyId);
	}

	public int getLikedReplyId(int memberId, int replyId) {
		return replyDao.getLikedReplyId(memberId, replyId);
	}

	public int getDislikedReplyId(int memberId, int replyId) {
		return replyDao.getDislikedReplyId(memberId, replyId);
	}

	public Object doCancleDislike(int memberId, int replyId) {
		return replyDao.doCancleDislike(memberId, replyId);
	}

	public Object doCanclelike(int memberId, int replyId) {
		return replyDao.doCancleLike(memberId, replyId);
	}

	public Object doDislike(int memberId, int replyId) {
		return replyDao.doDisLike(memberId, replyId);
	}

	public int getLikeReplyCount(int replyId) {
		return replyDao.getLikeReplyCount(replyId);
	}
}
