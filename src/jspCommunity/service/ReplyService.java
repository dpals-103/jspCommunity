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
}
