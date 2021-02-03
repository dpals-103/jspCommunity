package jspCommunity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jspCommunity.App;
import jspCommunity.container.Container;
import jspCommunity.dao.MemberDao;
import jspCommunity.dto.Member;
import jspCommunity.dto.ResultData;
import jspCommunity.util.Util;

public class MemberService {
	private static MemberDao memberDao;
	private static MailService mailService;
	private static AttrService attrService;

	public MemberService() {
		memberDao = Container.memberDao;
		mailService = Container.mailService;
		attrService = Container.attrService;
	}

	public static List<Member> getMembers() {
		return memberDao.getMembers();
	}

	public static Member getMember(int memberId) {
		return memberDao.getMember(memberId);
	}

	public String getJoinedIdByLoginId(String loginId) {
		return memberDao.getJoinedIdByLoginId(loginId);
	}

	public int join(Map<String, Object> args) {
		return memberDao.join(args);
	}

	public Member getMemberByloginId(String loginId) {
		return memberDao.getMemberByloginId(loginId);
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		return memberDao.getMemberByNameAndEmail(name, email);
	}

	public ResultData sendTempLoginPwToEmail(Member actor) {

		// 메일제목과 내용 만들기
		String siteName = App.getSite();
		String siteLoginUrl = App.getLoginUrl();

		;
		String title = "[" + siteName + "] 임시패스워드 발송";
		String tempPassword = Util.getTempPassword(6);
		String body = "<h1>임시패스워드 : " + tempPassword + "</h1>";
		body += "<a href=\"" + siteLoginUrl + "\" target=\"_blank\"> 로그인하러가기 </a>";

		Map<String, Object> rs = new HashMap<>();

		// 메일발송
		int sendRs = mailService.sendMail(actor.getEmail(), title, body);

		if (sendRs != 1) {
			return new ResultData("F-1", "발송에 실패하였습니다");
		}

		// 고객의 패스워드를 지금 생성한 임시 패스워드로 변경하기
		setTempPassword(actor, tempPassword);
		String resultMsg = "고객님의 새 임시 패스워드가 메일로 발송되었습니다";
		return new ResultData("S-1", resultMsg, "email", actor.getEmail());
	}

	private void setTempPassword(Member actor, String tempPassword) {

		Map<String, Object> modifyParam = new HashMap<>();

		modifyParam.put("id", actor.getId());
		modifyParam.put("loginPw", Util.sha256(tempPassword));

		modify(modifyParam);
		
		setIsUsingTempPassword(actor.getId(), true); 

	}

	public void setIsUsingTempPassword(int actorId, boolean use) {
		attrService.setValue("member__" + actorId + "__extra__isUsingTempPassword", use, null); 
	}
	
	public boolean getIsUsingTempPassword(int actorId) {
		return attrService.getValueAsBoolean("member__" + actorId + "__extra__isUsingTempPassword"); 
	}

	public void modify(Map<String, Object> param) {
		if(param.get("loginPw") != null) {
			setPasswordModifiedNow((int)param.get("id")); 
		}
		
		
		memberDao.modify(param);
	}

	private void setPasswordModifiedNow(int actorId) {
		attrService.setValue("member__" + actorId + "__extra__modifiedDate", Util.getNowDateStr(), null);
	}

	public int getOldPasswordDays() {
		return 90;
	}
	
	public boolean isNeedToModifyPassword(int actorId) {
		String date = attrService.getValue("member__" + actorId + "__extra__modifiedDate");
		
		if (Util.isEmpty(date)) {
			return true; 
		}
		
		int pass = Util.getPassedSecondsFrom(date);
		
		int OldPasswordDays = getOldPasswordDays();
		
		if (pass > OldPasswordDays * 60 * 60 * 24) {
			return true; 
		}
		
		return false; 
		
	}


}
