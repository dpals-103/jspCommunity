package jspCommunity.service;

import java.util.List;
import java.util.Map;

import jspCommunity.container.Container;
import jspCommunity.dao.MemberDao;
import jspCommunity.dto.Member;
import jspCommunity.util.Util;

public class MailService {
	private String gmailId;
	private String gmailPw;
	private String from;
	private String fromName;
	
	public void init(String gmailId, String gmailPw, String from, String fromName) {
		this.gmailId = gmailId; 
		this.gmailPw = gmailPw; 
		this.from = from; 
		this.fromName= fromName; 
	}

	public int sendMail(String to, String title, String body) {
		return Util.sendMail(gmailId, gmailPw, from, fromName, to, title, body); 
	}

	


}
