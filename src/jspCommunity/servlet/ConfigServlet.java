package jspCommunity.servlet;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import jspCommunity.container.Container;
import jspCommunity.service.MailService;
import jspCommunity.util.Util;

@WebServlet(name="loadAppConfig", urlPatterns= {"/loadConfig"}, loadOnStartup = 1)
public class ConfigServlet extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ServletContext context = getServletContext(); 
		InputStream inStream = context.getResourceAsStream("/META-INF/config.json");
		
		Map<String, Object> configMap = Util.getJsonMapFromFile(inStream);  
		
		String gmailId = (String)configMap.get("gmailId");
		String gmailPw  = (String)configMap.get("gmailPw");
		
		MailService mailService = Container.mailService;
		mailService.init(gmailId, gmailPw, "jspCommunity", "jspCommunity"); 
		
		/*메일발송 테스트*/
		//mailService.sendMail("dpals103@gmail.com", "메일테스트", "메일테스트"); 

	}
}
